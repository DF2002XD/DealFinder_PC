package com.example.dealfinder_pc.viewmodel.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dealfinder_pc.viewmodel.uistate.DetallesUIState
import com.example.dealfinder_pc.viewmodel.uistate.OfertaTiendaUIState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

class DetallesViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(DetallesUIState())
    val uiState: StateFlow<DetallesUIState> = _uiState.asStateFlow()

    fun cargarDetalles(idOferta: String) {
        _uiState.update { it.copy(isLoading = true, idOferta = idOferta, errorMessage = null) }

        viewModelScope.launch {
            // Simulamos petición a la API
            delay(1000.milliseconds)

            _uiState.update {
                it.copy(
                    isLoading = false,
                    tituloJuego = "Prison Tycoon 3: Lockdown",
                    urlImagen = "https://images.igdb.com/igdb/image/upload/t_cover_big/co1q3k.jpg",
                    minimoHistoricoInfo = "Mínimo histórico · 10/10/2016",
                    precioMinimoHistorico = "$1.99",
                    precioNormal = "$9.99",
                    ofertasEnTiendas = listOf(
                        OfertaTiendaUIState(
                            nombreTienda = "Steam",
                            precio = "$3.49",
                            descuentoTexto = "-65%",
                            esLaMejor = true,
                            urlLogo = "https://upload.wikimedia.org/wikipedia/commons/thumb/8/83/Steam_icon_logo.svg/512px-Steam_icon_logo.svg.png"
                        ),
                        OfertaTiendaUIState(
                            nombreTienda = "Fanatical",
                            precio = "$7.48",
                            descuentoTexto = "-25%",
                            esLaMejor = false
                        ),
                        OfertaTiendaUIState(
                            nombreTienda = "IndieGala",
                            precio = "$9.99",
                            descuentoTexto = null,
                            esLaMejor = false
                        )
                    ),
                    isEnDeseos = false // Simulamos que inicialmente NO está en deseos
                )
            }
        }
    }

    fun toggleDeseos() {
        _uiState.update { 
            // Invertimos el estado (si estaba en deseos lo quitamos, si no estaba lo añadimos)
            val nuevoEstado = !it.isEnDeseos 
            // Aquí el ViewModel haría la llamada a la base de datos o API para guardar este cambio
            it.copy(isEnDeseos = nuevoEstado) 
        }
    }
}
