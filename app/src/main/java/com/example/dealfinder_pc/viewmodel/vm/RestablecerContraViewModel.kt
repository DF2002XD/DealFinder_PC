package com.example.dealfinder_pc.viewmodel.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dealfinder_pc.data.repository.AuthRepository
import com.example.dealfinder_pc.data.repository.IAuthRepository
import com.example.dealfinder_pc.modelo.RestablecerRequestDTO
import com.example.dealfinder_pc.viewmodel.uistate.RestablecerContraUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RestablecerContraViewModel(
    private val authRepository: IAuthRepository = AuthRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow(RestablecerContraUIState())
    val uiState: StateFlow<RestablecerContraUIState> = _uiState.asStateFlow()

    fun actualizarCodigo(codigo: String) {
        _uiState.update { it.copy(codigo = codigo, errorMessage = null) }
    }

    fun actualizarNuevaContrasena(contrasena: String) {
        _uiState.update { it.copy(nuevaContrasena = contrasena, errorMessage = null) }
    }

    fun actualizarRepetirContrasena(contrasena: String) {
        _uiState.update { it.copy(repetirContrasena = contrasena, errorMessage = null) }
    }

    fun restablecerContrasena(correoAsociado: String, onSuccess: () -> Unit) {
        val state = _uiState.value

        if (state.codigo.isBlank()) {
            _uiState.update { it.copy(errorMessage = "El código no puede estar vacío") }
            return
        }

        if (state.nuevaContrasena.length < 6) {
            _uiState.update { it.copy(errorMessage = "La contraseña debe tener al menos 6 caracteres") }
            return
        }

        if (state.nuevaContrasena != state.repetirContrasena) {
            _uiState.update { it.copy(errorMessage = "Las contraseñas no coinciden") }
            return
        }

        _uiState.update { it.copy(isLoading = true, errorMessage = null) }

        viewModelScope.launch {
            val result = authRepository.restablecerContrasenha(
                RestablecerRequestDTO(
                    correo = correoAsociado,
                    token = state.codigo,
                    nuevaContrasena = state.nuevaContrasena
                )
            )

            if (result.isSuccess) {
                _uiState.update { it.copy(isLoading = false, isSuccess = true) }
                onSuccess()
            } else {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = result.exceptionOrNull()?.message ?: "Error desconocido"
                    )
                }
            }
        }
    }
}
