package com.example.dealfinder_pc.viewmodel.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dealfinder_pc.data.repository.IUsuarioRepository
import com.example.dealfinder_pc.data.repository.UsuarioRepository
import com.example.dealfinder_pc.data.repository.IOfertasRepository
import com.example.dealfinder_pc.data.repository.OfertasRepository
import com.example.dealfinder_pc.modelo.ActualizarPerfilRequestDTO
import com.example.dealfinder_pc.viewmodel.uistate.FiltrosUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FiltrosViewModel(
    private val usuarioRepository: IUsuarioRepository = UsuarioRepository(),
    private val ofertasRepository: IOfertasRepository = OfertasRepository()
) : ViewModel() {
    private val _uiState = MutableStateFlow(
        FiltrosUIState(
            tiendasDisponibles = emptyList(),
            tiendasSeleccionadas = emptyList() // Hacemos que ninguna esté marcada por defecto hasta que cargue
        )
    )
    val uiState: StateFlow<FiltrosUIState> = _uiState.asStateFlow()

    init {
        cargarTiendasGuardadas()
        cargarTiendasDisponibles()
    }

    private fun cargarTiendasDisponibles() {
        viewModelScope.launch {
            val result = ofertasRepository.obtenerTiendas()
            if (result.isSuccess) {
                val tiendas = result.getOrNull() ?: emptyList()
                _uiState.update { it.copy(tiendasDisponibles = tiendas.map { t -> t.nombre }) }
            }
        }
    }

    private fun cargarTiendasGuardadas() {
        viewModelScope.launch {
            val result = usuarioRepository.obtenerPerfil()
            if (result.isSuccess) {
                val usuario = result.getOrNull()
                if (usuario != null) {
                    _uiState.update { it.copy(tiendasSeleccionadas = usuario.tiendasPreferidas) }
                }
            }
        }
    }

    fun actualizarOrden(orden: String) {
        _uiState.update { it.copy(ordenSeleccionado = orden) }
    }

    fun toggleTienda(tienda: String) {
        _uiState.update { state ->
            val nuevasTiendas = if (state.tiendasSeleccionadas.contains(tienda)) {
                state.tiendasSeleccionadas - tienda
            } else {
                state.tiendasSeleccionadas + tienda
            }
            state.copy(tiendasSeleccionadas = nuevasTiendas)
        }
    }

    fun inicializarFiltros(precioMaximo: String, soloGratis: Boolean, soloEnOferta: Boolean, ordenSeleccionado: String, tiendasSeleccionadas: List<String>) {
        _uiState.update {
            it.copy(
                precioMaximo = precioMaximo,
                soloGratis = soloGratis,
                soloEnOferta = soloEnOferta,
                ordenSeleccionado = ordenSeleccionado,
                tiendasSeleccionadas = tiendasSeleccionadas.ifEmpty { it.tiendasSeleccionadas } // Conservar las del repo si vienen vacías
            )
        }
    }

    fun actualizarPrecioMaximo(precio: String) {
        _uiState.update { it.copy(precioMaximo = precio) }
    }

    fun toggleSoloGratis(activo: Boolean) {
        _uiState.update { it.copy(soloGratis = activo) }
    }

    fun toggleSoloEnOferta(activo: Boolean) {
        _uiState.update { it.copy(soloEnOferta = activo) }
    }

    fun limpiarFiltros() {
        _uiState.update {
            it.copy(
                ordenSeleccionado = "",
                precioMaximo = "",
                soloGratis = false,
                soloEnOferta = false
                // No limpiamos las tiendas seleccionadas porque suelen ser preferencias globales
            )
        }
    }

    fun guardarTiendasFavoritas(onSuccess: () -> Unit) {
        _uiState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            val requestDto = ActualizarPerfilRequestDTO(
                correo = null,
                moneda = null,
                avisosOfertas = null,
                tiendasPreferidas = _uiState.value.tiendasSeleccionadas // Mandamos las que hemos marcado
            )
            val result = usuarioRepository.actualizarPreferencias(requestDto)
            
            if (result.isSuccess) {
                _uiState.update { it.copy(isLoading = false) }
                onSuccess()
            } else {
                _uiState.update { it.copy(isLoading = false) } // Opcionalmente mostrar un mensaje de error
            }
        }
    }
}
