package com.example.dealfinder_pc.viewmodel.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dealfinder_pc.data.repository.IOfertasRepository
import com.example.dealfinder_pc.data.repository.OfertasRepository
import com.example.dealfinder_pc.data.repository.cargarTodasLasPaginas
import com.example.dealfinder_pc.viewmodel.uistate.OfertaUIState
import com.example.dealfinder_pc.viewmodel.uistate.OfertasUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

class OfertasViewModel(
    private val repository: IOfertasRepository = OfertasRepository()
) : ViewModel() {
    private val _uiState = MutableStateFlow(OfertasUIState())
    val uiState: StateFlow<OfertasUIState> = _uiState.asStateFlow()

    private var todasLasOfertas: MutableList<OfertaUIState> = mutableListOf()
    private val idsOfertasCargadas = mutableSetOf<String>()
    private var paginaActual = 0
    private var hayMasPaginas = true
    private var queryActual: String? = null
    private var maxPriceActual: Double? = null
    private var onSaleActual: Boolean? = null
    private var sortByActual: String? = null
    private var storeIdsActual: String? = null
    private var isCargandoPagina = false
    private var searchJob: kotlinx.coroutines.Job? = null
    
    private var storeIdMap: Map<String, String> = emptyMap()

    init {
        cargarOfertasYTiendas()
    }

    private fun cargarOfertasYTiendas() {
        viewModelScope.launch {
            val result = repository.obtenerTiendas()
            if (result.isSuccess) {
                val tiendas = result.getOrNull() ?: emptyList()
                storeIdMap = tiendas.associate { it.nombre to it.id }
            }
            cargarOfertas()
        }
    }
    
    fun recargarDatos() {
        if (todasLasOfertas.isEmpty()) {
            cargarOfertas()
        }
    }

    private var cargarJob: kotlinx.coroutines.Job? = null

    fun cargarMasOfertas() {
        if (!isCargandoPagina && hayMasPaginas) {
            cargarOfertas(isLoadMore = true)
        }
    }

    private fun cargarOfertas(isLoadMore: Boolean = false) {
        if (isCargandoPagina) return
        if (isLoadMore && !hayMasPaginas) return

        isCargandoPagina = true
        _uiState.update { it.copy(isLoading = true, errorMessage = null) }
        
        cargarJob = viewModelScope.launch {
            try {
                val result = cargarTodasLasPaginas(
                    paginaInicial = paginaActual,
                    obtenerPagina = { pagina -> repository.obtenerOfertas(query = queryActual, maxPrice = maxPriceActual, onSale = onSaleActual, sortBy = sortByActual, storeIds = storeIdsActual, page = pagina) },
                    alCargarPagina = { paginaOferta ->
                        val nuevasOfertas = paginaOferta.ofertas.map { oferta ->
                            OfertaUIState(
                                id = oferta.id,
                                titulo = oferta.titulo,
                                tiendaYMeta = "${oferta.nombreTienda} · Meta ${oferta.puntuacionMetacritic ?: "--"}",
                                precioOriginal = "$${oferta.precioOriginal}",
                                precioRebajado = if (oferta.precioRebajado <= 0.0) "GRATIS" else "$${oferta.precioRebajado}",
                                descuento = "-${oferta.descuentoPorcentaje.toInt()}%",
                                urlImagen = oferta.urlImagen
                            )
                        }

                        todasLasOfertas.addAll(nuevasOfertas.filter { idsOfertasCargadas.add(it.id) })
                        paginaActual++
                        hayMasPaginas = paginaOferta.hayMas

                        _uiState.update { it.copy(ofertas = todasLasOfertas.toList()) }
                    }
                )

                if (result.isFailure) {
                    _uiState.update {
                        it.copy(errorMessage = result.exceptionOrNull()?.message ?: "Error al cargar ofertas")
                    }
                }
            } finally {
                isCargandoPagina = false
                _uiState.update { it.copy(isLoading = false) }
            }
        }
    }

    fun buscarJuego(texto: String) {
        _uiState.update { it.copy(busquedaActual = texto) }

        searchJob?.cancel()
        cargarJob?.cancel()
        
        searchJob = viewModelScope.launch {
            kotlinx.coroutines.delay(500.milliseconds)
            cargarJob?.join()
            queryActual = texto.trim().ifEmpty { null }
            
            reiniciarYCargar()
        }
    }

    fun aplicarFiltros(precioMaximoStr: String, soloGratis: Boolean, soloEnOferta: Boolean, ordenSeleccionado: String, tiendasSeleccionadas: List<String>) {
        val maxPrice = if (soloGratis) {
            0.0
        } else {
            precioMaximoStr.toDoubleOrNull()
        }
        
        val onSale = if (soloEnOferta) true else null

        // Mapear el orden al formato que espera la API
        val sortBy = when (ordenSeleccionado) {
            "MAYOR PRECIO" -> "MAYOR_PRECIO"
            "MENOR PRECIO" -> "MENOR_PRECIO"
            "MAYOR DESCUENTO" -> "MAYOR_DESCUENTO"
            "RECIENTES" -> "RECIENTES"
            else -> "MEJOR_OFERTA"
        }

        // Mapear los nombres de tiendas a sus IDs obtenidos del backend
        val storeIds = if (tiendasSeleccionadas.isNotEmpty()) {
            tiendasSeleccionadas.mapNotNull { storeIdMap[it] }.joinToString(",")
        } else {
            null
        }

        // Actualizar UI state con los filtros actuales
        _uiState.update { it.copy(
            precioMaximoActual = precioMaximoStr,
            soloGratisActual = soloGratis,
            soloEnOfertaActual = soloEnOferta,
            ordenSeleccionadoActual = ordenSeleccionado,
            tiendasSeleccionadasActuales = tiendasSeleccionadas
        ) }

        // Solo recargamos si algo ha cambiado
        if (maxPriceActual != maxPrice || onSaleActual != onSale || sortByActual != sortBy || storeIdsActual != storeIds) {
            maxPriceActual = maxPrice
            onSaleActual = onSale
            sortByActual = sortBy
            storeIdsActual = storeIds
            
            // Actualizar texto de filtro activo
            val filterTexts = mutableListOf<String>()
            if (soloGratis) filterTexts.add("Gratis")
            else if (maxPrice != null) filterTexts.add("Máx $${maxPrice}")
            if (soloEnOferta) filterTexts.add("En Oferta")
            
            val activeFilterText = if (filterTexts.isNotEmpty()) "Filtros: ${filterTexts.joinToString(", ")}" else ""
            
            _uiState.update { it.copy(textoFiltroActivo = activeFilterText) }

            searchJob?.cancel()
            cargarJob?.cancel()
            
            viewModelScope.launch {
                cargarJob?.join()
                reiniciarYCargar()
            }
        }
    }

    private fun reiniciarYCargar() {
        paginaActual = 0
        hayMasPaginas = true
        isCargandoPagina = false
        
        todasLasOfertas.clear()
        idsOfertasCargadas.clear()
        _uiState.update { it.copy(ofertas = emptyList()) }
        
        cargarOfertas()
    }
}
