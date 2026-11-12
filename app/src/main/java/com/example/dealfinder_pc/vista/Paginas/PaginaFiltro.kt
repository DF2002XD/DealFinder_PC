package com.example.dealfinder_pc.vista.Paginas

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
import com.example.dealfinder_pc.vista.misComponentes.PanelFiltros

@Composable
fun PaginaFiltro(
    onNavigateBack: () -> Unit, // Cuando le dé a Aplicar o Limpiar para volver a Ofertas
    modifier: Modifier = Modifier
) {
    // ---------------------------------------------------------
    // ESTADOS SIMULADOS PARA DEMOSTRACIÓN (Lógica del ViewModel)
    // ---------------------------------------------------------
    var ordenSeleccionado by remember { mutableStateOf("Mejor Oferta Primero") }
    val tiendasMock = listOf("STEAM", "EPIC", "GOG", "HUMBLE", "FANATICAL")
    var tiendasSeleccionadas by remember { mutableStateOf(listOf("STEAM")) }
    var precioMaximo by remember { mutableStateOf("") }
    var soloGratis by remember { mutableStateOf(false) }
    var soloEnOferta by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF191636)) // Tu fondo retro
    ) {
        PanelFiltros(
            ordenSeleccionado = ordenSeleccionado,
            onOrdenSeleccionado = { ordenSeleccionado = it },
            tiendasDisponibles = tiendasMock,
            tiendasSeleccionadas = tiendasSeleccionadas,
            onTiendaToggle = { tienda ->
                // Lógica de toggle: si está la quita, si no está la añade
                tiendasSeleccionadas = if (tiendasSeleccionadas.contains(tienda)) {
                    tiendasSeleccionadas - tienda
                } else {
                    tiendasSeleccionadas + tienda
                }
            },
            precioMaximo = precioMaximo,
            onPrecioMaximoChange = { precioMaximo = it },
            monedaTexto = "USD",
            soloGratis = soloGratis,
            onSoloGratisChange = { soloGratis = it },
            soloEnOferta = soloEnOferta,
            onSoloEnOfertaChange = { soloEnOferta = it },
            onLimpiarClick = {
                // Borra todos y vuelve
                onNavigateBack()
            },
            onAplicarClick = {
                // Guarda cambios (VM) y vuelve
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

