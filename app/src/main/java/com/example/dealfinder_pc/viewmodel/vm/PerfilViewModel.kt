package com.example.dealfinder_pc.viewmodel.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dealfinder_pc.data.repository.IUsuarioRepository
import com.example.dealfinder_pc.data.repository.UsuarioRepository
import com.example.dealfinder_pc.modelo.ActualizarPerfilRequestDTO
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

    fun recargarDatos() {
        cargarDatosUsuario()
    }

    private fun cargarDatosUsuario() {
        println("PerfilViewModel: cargarDatosUsuario() INICIADO")
        _uiState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            val result = repository.obtenerPerfil()
            
            if (result.isSuccess) {
                val dto = result.getOrThrow()
                println("PerfilViewModel: Perfil obtenido correctamente -> $dto")
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
                println("PerfilViewModel: _uiState actualizado -> ${_uiState.value}")
            } else {
                println("PerfilViewModel: Error al obtener perfil -> ${result.exceptionOrNull()}")
                _uiState.update { it.copy(isLoading = false) }
            }
        }
    }

    fun toggleAvisos(activo: Boolean) {
        println("PerfilViewModel: toggleAvisos($activo)")
        _uiState.update { it.copy(avisosActivados = activo) }
        viewModelScope.launch {
            val request = ActualizarPerfilRequestDTO(
                correo = null,
                moneda = null,
                avisosOfertas = activo
            )
            val result = repository.actualizarPreferencias(request)
            println("PerfilViewModel: resultado de actualizarPreferencias -> $result")
        }
    }

    fun cerrarSesionLocal() {
        // Al cerrar sesión, limpiamos el token de la memoria RAM y de persistencia local
        com.example.dealfinder_pc.data.retrofit.network.NetworkModule.authToken = null
        com.example.dealfinder_pc.TokenManager.clearToken()
    }
}
