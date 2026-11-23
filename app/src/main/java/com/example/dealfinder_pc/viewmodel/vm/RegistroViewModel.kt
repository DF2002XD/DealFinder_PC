package com.example.dealfinder_pc.viewmodel.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dealfinder_pc.data.repository.IAuthRepository
import com.example.dealfinder_pc.data.repository.AuthRepository
import com.example.dealfinder_pc.modelo.RegistroRequestDTO
import com.example.dealfinder_pc.viewmodel.uistate.RegistroUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RegistroViewModel(
    private val repository: IAuthRepository = AuthRepository()
) : ViewModel() {
    private val _uiState = MutableStateFlow(RegistroUIState())
    val uiState: StateFlow<RegistroUIState> = _uiState.asStateFlow()

    fun actualizarUsuario(usuario: String) { _uiState.update { it.copy(nombreUsuario = usuario, errorMessage = null) } }
    fun actualizarCorreo(correo: String) { _uiState.update { it.copy(correo = correo, errorMessage = null) } }
    fun actualizarContrasenha(contra: String) { _uiState.update { it.copy(contrasenha = contra, errorMessage = null) } }
    fun actualizarRepeticion(rep: String) { _uiState.update { it.copy(confirmarContrasenha = rep, errorMessage = null) } }

    fun registrarUsuario(onSuccess: () -> Unit) {
        val state = _uiState.value
        if (state.nombreUsuario.isBlank() || state.correo.isBlank() || state.contrasenha.isBlank()) {
            _uiState.update { it.copy(errorMessage = "Completa todos los campos") }
            return
        }

        // Validación de correo electrónico
        val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$".toRegex()
        if (!state.correo.matches(emailRegex)) {
            _uiState.update { it.copy(errorMessage = "Formato de correo electrónico inválido") }
            return
        }

        // Validación estricta de contraseña
        val tieneMayuscula = state.contrasenha.any { it.isUpperCase() }
        val tieneMinuscula = state.contrasenha.any { it.isLowerCase() }
        val tieneNumero = state.contrasenha.any { it.isDigit() }
        val tieneEspecial = state.contrasenha.any { !it.isLetterOrDigit() }

        if (state.contrasenha.length < 8 || !tieneMayuscula || !tieneMinuscula || !tieneNumero || !tieneEspecial) {
            _uiState.update { 
                it.copy(errorMessage = "La contraseña debe tener al menos 8 caracteres, incluyendo una mayúscula, minúscula, número y carácter especial") 
            }
            return
        }

        if (state.contrasenha != state.confirmarContrasenha) {
            _uiState.update { it.copy(errorMessage = "Las contraseñas no coinciden") }
            return
        }
        
        _uiState.update { it.copy(isLoading = true, errorMessage = null) }

        viewModelScope.launch {
            val request = RegistroRequestDTO(
                username = state.nombreUsuario,
                email = state.correo,
                passwordHash = state.contrasenha
            )
            val result = repository.registrarUsuario(request)
            
            if (result.isSuccess) {
                _uiState.update { it.copy(isLoading = false) }
                onSuccess()
            } else {
                _uiState.update { it.copy(isLoading = false, errorMessage = "Error en el registro") }
            }
        }
    }
}
