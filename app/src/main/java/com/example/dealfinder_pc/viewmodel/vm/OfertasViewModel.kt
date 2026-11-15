package com.example.dealfinder_pc.viewmodel.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
            todasLasOfertas = listOf(
                OfertaUIState("1", "Silver Pines", "Steam · Meta 85", "$35.90", "$22.26", "-38%", urlMock),
                OfertaUIState("2", "Suicide Squad: Kill the Justice Le...", "Epic Games Store · Meta 63", "$69.99", "$3.49", "-95%", urlMock),
                OfertaUIState("3", "Frosthaven", "GameBillet · Steam 70%", "$39.99", "$23.19", "-42%", urlMock),
                OfertaUIState("4", "EA SPORTS PGA TOUR", "Epic Games Store · Meta 72", "$69.99", "$6.99", "-90%", urlMock),
                OfertaUIState("5", "Bad Cheese", "Epic Games Store · Meta 63", "$12.99", "GRATIS", "-100%", urlMock)
            )
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
