package com.example.dealfinder_pc.viewmodel.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dealfinder_pc.viewmodel.uistate.LoginUIState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

class LoginViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUIState())
    val uiState: StateFlow<LoginUIState> = _uiState.asStateFlow()

    fun actualizarCorreo(correo: String) {
        _uiState.update { it.copy(correo = correo, errorMessage = null) }
    }

    fun actualizarContrasenha(contrasenha: String) {
        _uiState.update { it.copy(contrasenha = contrasenha, errorMessage = null) }
    }

    fun toggleKeepLogged(keepLogged: Boolean) {
        _uiState.update { it.copy(keepLogged = keepLogged) }
    }

    fun iniciarSesion(onSuccess: () -> Unit) {
        val actualState = _uiState.value

        if (actualState.correo.isBlank() || actualState.contrasenha.isBlank()) {
            _uiState.update { it.copy(errorMessage = "Por favor, completa todos los campos.") }
            return
        }
        
        // Validación de formato de correo electrónico
        val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$".toRegex()
        if (!actualState.correo.matches(emailRegex)) {
            _uiState.update { it.copy(errorMessage = "Formato de correo electrónico inválido") }
            return
        }

        _uiState.update { it.copy(isLoading = true, errorMessage = null) }

        viewModelScope.launch {
            // Simulamos petición de red (API/Retrofit)
            delay(1500.milliseconds)

            // OPCIÓN A (TEMPORAL): Dejamos pasar cualquier cosa mientras esperamos a implementar Retrofit y la API REST.
            _uiState.update { it.copy(isLoading = false) }
            onSuccess()
            
            /* TODO: Lógica de autenticación real (Ejemplo estático comentado)
            if (actualState.correo == "test@test.com" && actualState.contrasenha == "123456") {
                _uiState.update { it.copy(isLoading = false) }
                onSuccess()
            } else {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = "Correo o contraseña incorrectos."
                    )
                }
            }
            */
        }
    }
}
