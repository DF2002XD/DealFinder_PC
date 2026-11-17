package com.example.dealfinder_pc.viewmodel.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dealfinder_pc.data.repositorio.IAuthRepo
import com.example.dealfinder_pc.data.repositorio.AuthRepoImpl
import com.example.dealfinder_pc.viewmodel.uistate.RecuperarContraUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RecuperarContraViewModel(
    private val repository: IAuthRepo = AuthRepoImpl()
) : ViewModel() {
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
            val result = repository.recuperarContrasenha(state.correo)
            
            if (result.isSuccess) {
                _uiState.update { it.copy(isLoading = false, isSuccess = true) }
                onSuccess()
            } else {
                _uiState.update { it.copy(isLoading = false, errorMessage = "Error enviando correo") }
            }
        }
    }
}
