package com.example.dealfinder_pc.vista.paginas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.dealfinder_pc.ui.theme.DealFinder_PCTheme
import com.example.dealfinder_pc.viewmodel.uistate.FiltrosUIState
import com.example.dealfinder_pc.vista.misComponentes.PanelFiltros

@Composable
fun PaginaFiltro(
    onNavigateBack: () -> Unit, // Para volver a Ofertas al darle a Aplicar
    modifier: Modifier = Modifier
) {
    var uiState by remember { mutableStateOf(FiltrosUIState(
        tiendasDisponibles = listOf("STEAM", "EPIC", "GOG", "HUMBLE", "FANATICAL"),
        tiendasSeleccionadas = listOf("STEAM")
    )) }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF191636)) // Tu fondo retro
    ) {
        PanelFiltros(
            ordenSeleccionado = uiState.ordenSeleccionado,
            onOrdenSeleccionado = { uiState = uiState.copy(ordenSeleccionado = it) },
            tiendasDisponibles = uiState.tiendasDisponibles,
            tiendasSeleccionadas = uiState.tiendasSeleccionadas,
            onTiendaToggle = { tienda ->
                val nuevasTiendas = if (uiState.tiendasSeleccionadas.contains(tienda)) {
                    uiState.tiendasSeleccionadas - tienda
                } else {
                    uiState.tiendasSeleccionadas + tienda
                }
                uiState = uiState.copy(tiendasSeleccionadas = nuevasTiendas)
            },
            precioMaximo = uiState.precioMaximo,
            onPrecioMaximoChange = { uiState = uiState.copy(precioMaximo = it) },
            monedaTexto = "USD",
            soloGratis = uiState.soloGratis,
            onSoloGratisChange = { uiState = uiState.copy(soloGratis = it) },
            soloEnOferta = uiState.soloEnOferta,
            onSoloEnOfertaChange = { uiState = uiState.copy(soloEnOferta = it) },
            onLimpiarClick = {
                uiState = uiState.copy(
                    ordenSeleccionado = "Mejor Oferta Primero",
                    tiendasSeleccionadas = emptyList(),
                    precioMaximo = "",
                    soloGratis = false,
                    soloEnOferta = false
                )
            },
            onAplicarClick = {
                // Guarda cambios (el ViewModel lo haría aquí) y vuelve a la lista
                onNavigateBack()
            }
        )
    }
}

@Preview
@Composable
fun PaginaFiltroPreview() {
    DealFinder_PCTheme {
        PaginaFiltro(
            onNavigateBack = {}
        )
    }
}

