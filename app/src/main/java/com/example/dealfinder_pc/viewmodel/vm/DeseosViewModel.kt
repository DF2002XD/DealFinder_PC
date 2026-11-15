package com.example.dealfinder_pc.viewmodel.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dealfinder_pc.viewmodel.uistate.DeseoUIState
import com.example.dealfinder_pc.viewmodel.uistate.DeseosUIState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

class DeseosViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(DeseosUIState())
    val uiState: StateFlow<DeseosUIState> = _uiState.asStateFlow()

    init {
        cargarDeseos()
    }

    private fun cargarDeseos() {
        _uiState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            delay(800.milliseconds)
            val urlMock = "https://images.igdb.com/igdb/image/upload/t_cover_big/co1q3k.jpg"
            val lista = listOf(
                DeseoUIState("1", "Silver Pines", "$22.26", urlMock),
                DeseoUIState("3", "Frosthaven", "$23.19", urlMock),
                DeseoUIState("4", "EA SPORTS PGA TOUR", "$6.99", urlMock)
            )
            _uiState.update {
                it.copy(
                    juegosEnDeseos = lista,
                    precioTotalEstimado = "${lista.size} juegos · aprox. $52.44",
                    isLoading = false
                )
            }
        }
    }

    fun marcarComoComprado(idJuego: String) {
        // En la realidad, esto actualizaría el presupuesto mensual y borraría o marcaría el juego en DB.
        eliminarJuego(idJuego)
    }

    fun eliminarJuego(idJuego: String) {
        val listaActualizada = _uiState.value.juegosEnDeseos.filter { it.id != idJuego }
        _uiState.update {
            it.copy(
                juegosEnDeseos = listaActualizada,
                precioTotalEstimado = "${listaActualizada.size} juegos en lista"
            )
        }
    }
}
