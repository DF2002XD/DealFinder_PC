package com.example.dealfinder_pc.viewmodel.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dealfinder_pc.data.repository.IDeseosRepository
import com.example.dealfinder_pc.data.repository.DeseosRepository
import com.example.dealfinder_pc.data.repository.IOfertasRepository
import com.example.dealfinder_pc.data.repository.OfertasRepository
import com.example.dealfinder_pc.modelo.DeseoRequestDTO
import com.example.dealfinder_pc.viewmodel.uistate.DetallesUIState
import com.example.dealfinder_pc.viewmodel.uistate.OfertaTiendaUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DetallesViewModel(
    private val repository: IOfertasRepository = OfertasRepository(),
    private val deseosRepo: IDeseosRepository = DeseosRepository()
) : ViewModel() {
    private val _uiState = MutableStateFlow(DetallesUIState())
    val uiState: StateFlow<DetallesUIState> = _uiState.asStateFlow()

    fun cargarDetalles(idOferta: String) {
        _uiState.update { it.copy(isLoading = true, idOferta = idOferta, errorMessage = null) }

        viewModelScope.launch {
            val result = repository.obtenerDetallesJuego(idOferta)
            
            if (result.isSuccess) {
                val detalles = result.getOrNull()
                if (detalles != null) {
                    
                    // Formateamos la fecha del histórico de milisegundos a fecha corta (Ej: 10/10/2016)
                    val fechaFormateada = if (detalles.minimoHistoricoFecha > 0L) {
                        val format = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                        format.format(Date(detalles.minimoHistoricoFecha * 1000L)) // Cheapshark manda segundos
                    } else {
                        "--/--/----"
                    }
                    
                    // Buscamos cuál es el precio normal en la primera oferta (todos tienen el mismo precio normal del juego base)
                    val pNormal = detalles.ofertasTiendas.firstOrNull()?.precioNormal ?: 0.0

                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            tituloJuego = detalles.titulo,
                            urlImagen = detalles.urlImagen,
                            minimoHistoricoInfo = "Mínimo histórico · $fechaFormateada",
                            precioMinimoHistorico = "$${detalles.minimoHistoricoPrecio}",
                            precioNormal = "$$pNormal",
                            ofertasEnTiendas = detalles.ofertasTiendas.mapIndexed { index, tienda ->
                                OfertaTiendaUIState(
                                    nombreTienda = tienda.nombreTienda,
                                    precio = "$${tienda.precioActual}",
                                    descuentoTexto = if (tienda.descuentoPorcentaje > 0.0) "-${tienda.descuentoPorcentaje.toInt()}%" else null,
                                    // Consideramos la mejor oferta la primera de la lista (Cheapshark suele ordenarlas por precio)
                                    esLaMejor = index == 0,
                                    // URL generica para la tienda
                                    urlLogo = "https://www.cheapshark.com/img/stores/banners/${tienda.idTienda}.png"
                                )
                            },
                            isEnDeseos = detalles.estaEnDeseos 
                        )
                    }
                }
            } else {
                _uiState.update { it.copy(isLoading = false, errorMessage = "Error al cargar detalles") }
            }
        }
    }

    fun toggleDeseos() {
        val currentState = _uiState.value
        val gameId = currentState.idOferta
        if (gameId.isBlank()) return
        
        // Invertimos el estado (si estaba en deseos lo quitamos, si no estaba lo añadimos) visualmente de inmediato
        val nuevoEstado = !currentState.isEnDeseos 
        _uiState.update { it.copy(isEnDeseos = nuevoEstado) }
        
        viewModelScope.launch {
            val request = DeseoRequestDTO(
                gameId = gameId,
                titulo = currentState.tituloJuego,
                thumbUrl = currentState.urlImagen,
                // Le guardamos el mejor precio como referencia, quitándole el símbolo del dólar
                precioReferencia = currentState.ofertasEnTiendas.firstOrNull()?.precio?.replace("$", "")?.toDoubleOrNull() ?: 0.0
            )
            
            val result = deseosRepo.modificarDeseo(request)
            
            // Si hubo un error en la API, devolvemos el estado visual a como estaba antes
            if (result.isFailure) {
                _uiState.update { it.copy(isEnDeseos = currentState.isEnDeseos) }
            }
        }
    }
}
