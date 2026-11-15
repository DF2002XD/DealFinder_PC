package com.example.dealfinder_pc.viewmodel.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dealfinder_pc.viewmodel.uistate.PerfilUIState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

class PerfilViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(PerfilUIState())
    val uiState: StateFlow<PerfilUIState> = _uiState.asStateFlow()

    init {
        cargarDatosUsuario()
    }

    private fun cargarDatosUsuario() {
        _uiState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            delay(500.milliseconds)
            _uiState.update {
                it.copy(
                    nombreUsuario = "JUGADOR_01",
                    correoUsuario = "jugador@correo.com",
                    presupuestoTexto = "$45.00 / $100.00 USD",
                    monedaActual = "USD",
                    tiendasTexto = "6 tiendas",
                    avisosActivados = true,
                    isLoading = false
                )
            }
        }
    }

    fun toggleAvisos(activo: Boolean) {
        // Guardaría la preferencia en Firebase/Base de datos
        _uiState.update { it.copy(avisosActivados = activo) }
    }
}
