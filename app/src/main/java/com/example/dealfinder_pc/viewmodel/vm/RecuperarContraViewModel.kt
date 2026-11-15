package com.example.dealfinder_pc.viewmodel.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dealfinder_pc.viewmodel.uistate.RecuperarContraUIState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

class RecuperarContraViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(RecuperarContraUIState())
    val uiState: StateFlow<RecuperarContraUIState> = _uiState.asStateFlow()

    fun actualizarCorreo(correo: String) {
        _uiState.update { it.copy(correo = correo, errorMessage = null) }
    }

    fun enviarCorreoRecuperacion(onSuccess: () -> Unit) {
        val state = _uiState.value
        if (state.correo.isBlank()) {
            _uiState.update { it.copy(errorMessage = "Introduce un correo electrónico válido") }
            return
        }

        _uiState.update { it.copy(isLoading = true, errorMessage = null) }

        viewModelScope.launch {
            // Simulamos llamada a la API
            delay(1500.milliseconds)
            _uiState.update { it.copy(isLoading = false, isSuccess = true) }
            onSuccess()
        }
    }
}
