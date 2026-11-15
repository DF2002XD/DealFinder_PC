package com.example.dealfinder_pc.viewmodel.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dealfinder_pc.viewmodel.uistate.CuentaUIState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

class CuentaViewModel : ViewModel() {
    // Simulamos que el usuario ya está logueado y tiene un correo
    private val _uiState = MutableStateFlow(CuentaUIState(nombreUsuario = "JUGADOR_01", correo = "jugador@correo.com"))
    val uiState: StateFlow<CuentaUIState> = _uiState.asStateFlow()

    fun cambiarCorreo(nuevoCorreo: String, onSuccess: () -> Unit) {
        if (nuevoCorreo.isBlank()) {
            _uiState.update { it.copy(errorMessage = "El correo no puede estar vacío") }
            return
        }
        if (nuevoCorreo == _uiState.value.correo) {
            _uiState.update { it.copy(errorMessage = "Tiene que ser un correo distinto") }
            return
        }

        // Simulamos actualización
        _uiState.update { it.copy(correo = nuevoCorreo, errorMessage = null) }
        onSuccess()
    }

    fun cambiarContrasenha(actual: String, nueva: String, repeticion: String, onSuccess: () -> Unit) {
        if (actual.isBlank() || nueva.isBlank() || repeticion.isBlank()) {
            _uiState.update { it.copy(errorMessage = "Rellena todos los campos de contraseña") }
            return
        }
        if (nueva != repeticion) {
            _uiState.update { it.copy(errorMessage = "Las contraseñas nuevas no coinciden") }
            return
        }

        _uiState.update { it.copy(isCambiandoContrasenha = true, errorMessage = null) }

        viewModelScope.launch {
            delay(1500.milliseconds)
            _uiState.update { it.copy(isCambiandoContrasenha = false) }
            onSuccess()
        }
    }
}
