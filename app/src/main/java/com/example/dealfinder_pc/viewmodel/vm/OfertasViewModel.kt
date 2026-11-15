package com.example.dealfinder_pc.viewmodel.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dealfinder_pc.modelo.OfertaDTO
import com.example.dealfinder_pc.viewmodel.uistate.OfertaUIState
import com.example.dealfinder_pc.viewmodel.uistate.OfertasUIState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

class OfertasViewModel : ViewModel() {
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
            delay(1000.milliseconds)
            val urlMock = "https://images.igdb.com/igdb/image/upload/t_cover_big/co1q3k.jpg"
            
            // 1. Simulamos que recibimos una lista de OfertaDTO desde el Repositorio / API
            val mockDtos = listOf(
                OfertaDTO("1", "Silver Pines", 22.26, 35.90, 38.0, urlMock, "Steam", 85),
                OfertaDTO("2", "Suicide Squad: Kill the Justice Le...", 3.49, 69.99, 95.0, urlMock, "Epic Games Store", 63),
                OfertaDTO("3", "Frosthaven", 23.19, 39.99, 42.0, urlMock, "GameBillet", 70),
                OfertaDTO("4", "EA SPORTS PGA TOUR", 6.99, 69.99, 90.0, urlMock, "Epic Games Store", 72),
                OfertaDTO("5", "Bad Cheese", 0.0, 12.99, 100.0, urlMock, "Epic Games Store", 63)
            )

            // 2. Mapeamos los DTOs al UIState que necesita nuestra vista
            todasLasOfertas = mockDtos.map { dto ->
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
