package com.example.dealfinder_pc.viewmodel.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dealfinder_pc.data.repository.IAuthRepository
import com.example.dealfinder_pc.data.repository.AuthRepository
import com.example.dealfinder_pc.modelo.LoginRequestDTO
import com.example.dealfinder_pc.viewmodel.uistate.LoginUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val repository: IAuthRepository = AuthRepository()
) : ViewModel() {

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
            val request = LoginRequestDTO(
                correo = actualState.correo,
                contrasena = actualState.contrasenha,
                mantenerSesion = actualState.keepLogged
            )
            val result = repository.iniciarSesion(request)
            
            if (result.isSuccess) {
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
        }
    }
}
