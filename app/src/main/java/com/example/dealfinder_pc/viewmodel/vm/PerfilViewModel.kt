package com.example.dealfinder_pc.viewmodel.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dealfinder_pc.data.repository.IUsuarioRepository
import com.example.dealfinder_pc.data.repository.UsuarioRepository
import com.example.dealfinder_pc.modelo.PreferenciaUpdateRequestDTO
import com.example.dealfinder_pc.viewmodel.uistate.PerfilUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PerfilViewModel(
    private val repository: IUsuarioRepository = UsuarioRepository()
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
                        nombreUsuario = dto.nombreUsuario,
                        correoUsuario = dto.correo,
                        presupuestoTexto = "$45.00 / $${dto.presupuestoMensual ?: 0.0} ${dto.monedaPreferida}", 
                        monedaActual = dto.monedaPreferida,
                        tiendasTexto = "${dto.tiendasPreferidas.size} tiendas",
                        avisosActivados = dto.alertasActivadas,
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
