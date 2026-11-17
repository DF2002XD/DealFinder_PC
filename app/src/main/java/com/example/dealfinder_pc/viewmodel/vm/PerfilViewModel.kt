package com.example.dealfinder_pc.viewmodel.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dealfinder_pc.data.repositorio.IUsuarioRepo
import com.example.dealfinder_pc.data.repositorio.UsuarioRepoImpl
import com.example.dealfinder_pc.modelo.PreferenciaUpdateRequestDTO
import com.example.dealfinder_pc.viewmodel.uistate.PerfilUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PerfilViewModel(
    private val repository: IUsuarioRepo = UsuarioRepoImpl()
) : ViewModel() {
    private val _uiState = MutableStateFlow(PerfilUIState())
    val uiState: StateFlow<PerfilUIState> = _uiState.asStateFlow()

    init {
        cargarDatosUsuario()
    }

    private fun cargarDatosUsuario() {
        _uiState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            val result = repository.obtenerPerfil()
            
            if (result.isSuccess) {
                val dto = result.getOrThrow()
                _uiState.update {
                    it.copy(
                        nombreUsuario = dto.username,
                        correoUsuario = dto.email,
                        presupuestoTexto = "$45.00 / $${dto.monthlyBudget ?: 0.0} ${dto.preferredCurrency}", 
                        monedaActual = dto.preferredCurrency,
                        tiendasTexto = "${dto.preferredStores.size} tiendas",
                        avisosActivados = dto.dealsAlertsEnabled,
                        isLoading = false
                    )
                }
            } else {
                _uiState.update { it.copy(isLoading = false) }
            }
        }
    }

    fun toggleAvisos(activo: Boolean) {
        _uiState.update { it.copy(avisosActivados = activo) }
        viewModelScope.launch {
            val request = PreferenciaUpdateRequestDTO(
                userId = "uuid-1234", 
                dealsAlertsEnabled = activo
            )
            repository.actualizarPreferencias(request)
        }
    }
}
