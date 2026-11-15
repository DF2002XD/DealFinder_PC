package com.example.dealfinder_pc.viewmodel.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dealfinder_pc.modelo.PreferenciaUpdateRequestDTO
import com.example.dealfinder_pc.viewmodel.uistate.FiltrosUIState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

class FiltrosViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(
        FiltrosUIState(
            tiendasDisponibles = listOf("Steam", "Epic Games Store", "GOG", "Humble Store", "Fanatical", "IndieGala"),
            tiendasSeleccionadas = listOf("Steam", "Epic Games Store")
        )
    )
    val uiState: StateFlow<FiltrosUIState> = _uiState.asStateFlow()

    fun actualizarOrden(orden: String) {
        _uiState.update { it.copy(ordenSeleccionado = orden) }
    }

    fun toggleTienda(tienda: String) {
        _uiState.update { state ->
            val nuevasTiendas = if (state.tiendasSeleccionadas.contains(tienda)) {
                state.tiendasSeleccionadas - tienda
            } else {
                state.tiendasSeleccionadas + tienda
            }
            state.copy(tiendasSeleccionadas = nuevasTiendas)
        }
    }

    fun actualizarPrecioMaximo(precio: String) {
        _uiState.update { it.copy(precioMaximo = precio) }
    }

    fun toggleSoloGratis(activo: Boolean) {
        _uiState.update { it.copy(soloGratis = activo) }
    }

    fun toggleSoloEnOferta(activo: Boolean) {
        _uiState.update { it.copy(soloEnOferta = activo) }
    }

    fun limpiarFiltros() {
        _uiState.update {
            it.copy(
                ordenSeleccionado = "Mejor Oferta Primero",
                precioMaximo = "",
                soloGratis = false,
                soloEnOferta = false
                // No limpiamos las tiendas seleccionadas porque suelen ser preferencias globales
            )
        }
    }

    fun guardarTiendasFavoritas(onSuccess: () -> Unit) {
        _uiState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            delay(1000.milliseconds)
            
            // Simulación: DTO que enviaríamos a tu Spring Boot
            val requestDto = PreferenciaUpdateRequestDTO(
                userId = "uuid-1234",
                preferredStores = _uiState.value.tiendasSeleccionadas
            )
            // apiService.updatePreferences(requestDto)
            
            _uiState.update { it.copy(isLoading = false) }
            onSuccess()
        }
    }
}
