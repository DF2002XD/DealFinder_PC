package com.example.dealfinder_pc.viewmodel.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dealfinder_pc.data.repository.IUsuarioRepository
import com.example.dealfinder_pc.data.repository.UsuarioRepository
import com.example.dealfinder_pc.modelo.ActualizarPerfilRequestDTO
import com.example.dealfinder_pc.viewmodel.uistate.FiltrosUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FiltrosViewModel(
    private val repository: IUsuarioRepository = UsuarioRepository()
) : ViewModel() {
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
            val requestDto = ActualizarPerfilRequestDTO(
                correo = null,
                moneda = null,
                avisosOfertas = null
            )
            val result = repository.actualizarPreferencias(requestDto)
            
            if (result.isSuccess) {
                _uiState.update { it.copy(isLoading = false) }
                onSuccess()
            } else {
                _uiState.update { it.copy(isLoading = false) } // Opcionalmente mostrar un mensaje de error
            }
        }
    }
}
