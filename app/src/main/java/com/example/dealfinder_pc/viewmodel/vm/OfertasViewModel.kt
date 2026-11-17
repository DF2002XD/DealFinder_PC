package com.example.dealfinder_pc.viewmodel.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dealfinder_pc.data.repositorio.IOfertasRepo
import com.example.dealfinder_pc.data.repositorio.OfertasRepoImpl
import com.example.dealfinder_pc.viewmodel.uistate.OfertaUIState
import com.example.dealfinder_pc.viewmodel.uistate.OfertasUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class OfertasViewModel(
    private val repository: IOfertasRepo = OfertasRepoImpl()
) : ViewModel() {
    private val _uiState = MutableStateFlow(OfertasUIState())
    val uiState: StateFlow<OfertasUIState> = _uiState.asStateFlow()

    // Lista original sin filtrar
    private var todasLasOfertas: List<OfertaUIState> = emptyList()

    init {
        cargarOfertas()
    }

    private fun cargarOfertas() {
        _uiState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            val result = repository.obtenerOfertas()
            
            if (result.isSuccess) {
                val dtoList = result.getOrDefault(emptyList())
                
                // Mapeamos los DTOs al UIState que necesita nuestra vista
                todasLasOfertas = dtoList.map { dto ->
                    OfertaUIState(
                        id = dto.gameID,
                        titulo = dto.title,
                        tiendaYMeta = "${dto.storeID} · Meta ${dto.metacriticScore ?: "--"}",
                        precioOriginal = "$${dto.normalPrice}",
                        precioRebajado = if (dto.salePrice <= 0.0) "GRATIS" else "$${dto.salePrice}",
                        descuento = "-${dto.savings.toInt()}%",
                        urlImagen = dto.thumbUrl
                    )
                }
                _uiState.update { it.copy(ofertas = todasLasOfertas, isLoading = false) }
            } else {
                _uiState.update { it.copy(isLoading = false, errorMessage = "Error al cargar ofertas") }
            }
        }
    }

    fun buscarJuego(texto: String) {
        _uiState.update { state ->
            val filtradas = if (texto.isBlank()) {
                todasLasOfertas
            } else {
                todasLasOfertas.filter { it.titulo.contains(texto, ignoreCase = true) }
            }
            state.copy(busquedaActual = texto, ofertas = filtradas)
        }
    }
}
