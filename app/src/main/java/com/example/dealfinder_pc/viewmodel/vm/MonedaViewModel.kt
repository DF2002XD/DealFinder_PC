package com.example.dealfinder_pc.viewmodel.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dealfinder_pc.modelo.PreferenciaUpdateRequestDTO
import com.example.dealfinder_pc.viewmodel.uistate.MonedaUIState
import com.example.dealfinder_pc.viewmodel.uistate.MonedasUIState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

class MonedaViewModel : ViewModel() {
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
            delay(1000.milliseconds)
            
            // Simulación: Creamos el DTO de actualización que enviaríamos al backend
            val requestDto = PreferenciaUpdateRequestDTO(
                userId = "uuid-1234", // Lo sacaríamos del estado de sesión global
                preferredCurrency = _uiState.value.monedaActual
            )
            // Aquí iría: apiService.updatePreferences(requestDto)
            
            _uiState.update { it.copy(isSaving = false) }
            onSuccess()
        }
    }
}
