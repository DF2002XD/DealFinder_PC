package com.example.dealfinder_pc.viewmodel.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dealfinder_pc.data.repository.IOfertasRepository
import com.example.dealfinder_pc.data.repository.OfertasRepository
import com.example.dealfinder_pc.viewmodel.uistate.OfertaUIState
import com.example.dealfinder_pc.viewmodel.uistate.OfertasUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class OfertasViewModel(
    private val repository: IOfertasRepository = OfertasRepository()
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
                
                // Mapeamos Modelo de Dominio -> UIState
                todasLasOfertas = dtoList.map { oferta ->
                    OfertaUIState(
                        id = oferta.id,
                        titulo = oferta.titulo,
                        tiendaYMeta = "${oferta.nombreTienda} · Meta ${oferta.puntuacionMetacritic ?: "--"}",
                        precioOriginal = "$${oferta.precioOriginal}",
                        precioRebajado = if (oferta.precioRebajado <= 0.0) "GRATIS" else "$${oferta.precioRebajado}",
                        descuento = "-${oferta.descuentoPorcentaje.toInt()}%",
                        urlImagen = oferta.urlImagen
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
