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
    private var isCargandoPagina = false
    private var searchJob: kotlinx.coroutines.Job? = null

    init {
        cargarOfertas()
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
                    obtenerPagina = { pagina -> repository.obtenerOfertas(query = queryActual, page = pagina) },
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
            kotlinx.coroutines.delay(500)
            cargarJob?.join()
            queryActual = if (texto.trim().isNotEmpty()) texto.trim() else null
            
            paginaActual = 0
            hayMasPaginas = true
            isCargandoPagina = false
            
            todasLasOfertas.clear()
            idsOfertasCargadas.clear()
            _uiState.update { it.copy(ofertas = emptyList()) }
            
            cargarOfertas()
        }
    }
}
