package com.example.dealfinder_pc.viewmodel.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dealfinder_pc.data.repository.IUsuarioRepository
import com.example.dealfinder_pc.data.repository.UsuarioRepository
import com.example.dealfinder_pc.modelo.PreferenciaUpdateRequestDTO
import com.example.dealfinder_pc.viewmodel.uistate.MonedaUIState
import com.example.dealfinder_pc.viewmodel.uistate.MonedasUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MonedaViewModel(
    private val repository: IUsuarioRepository = UsuarioRepository()
) : ViewModel() {
    private val _uiState = MutableStateFlow(
        MonedasUIState(
            monedasDisponibles = listOf(
                MonedaUIState("Dólar estadounidense", "USD"),
                MonedaUIState("Euro", "EUR"),
                MonedaUIState("Libra esterlina", "GBP"),
                MonedaUIState("Peso mexicano", "MXN"),
                MonedaUIState("Peso argentino", "ARS")
            ),
            monedaActual = "USD"
        )
    )
    val uiState: StateFlow<MonedasUIState> = _uiState.asStateFlow()

    fun seleccionarMoneda(codigoMoneda: String) {
        _uiState.update { it.copy(monedaActual = codigoMoneda) }
    }

    fun guardarMoneda(onSuccess: () -> Unit) {
        _uiState.update { it.copy(isSaving = true) }
        viewModelScope.launch {
            val requestDto = PreferenciaUpdateRequestDTO(
                userId = "uuid-1234",
                preferredCurrency = _uiState.value.monedaActual
            )
            
            val result = repository.actualizarPreferencias(requestDto)
            if (result.isSuccess) {
                _uiState.update { it.copy(isSaving = false) }
                onSuccess()
            } else {
                _uiState.update { it.copy(isSaving = false, errorMessage = "Error guardando moneda") }
            }
        }
    }
}
