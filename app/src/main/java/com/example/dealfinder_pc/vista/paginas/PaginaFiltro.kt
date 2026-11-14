package com.example.dealfinder_pc.vista.paginas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dealfinder_pc.ui.theme.DealFinder_PCTheme
import com.example.dealfinder_pc.viewmodel.vm.FiltrosViewModel
import com.example.dealfinder_pc.vista.misComponentes.PanelFiltros

@Composable
fun PaginaFiltro(
    onNavigateBack: () -> Unit, // Para volver a Ofertas al darle a Aplicar
    modifier: Modifier = Modifier,
    viewModel: FiltrosViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF191636)) // Tu fondo retro
    ) {
        PanelFiltros(
            ordenSeleccionado = uiState.ordenSeleccionado,
            onOrdenSeleccionado = { viewModel.actualizarOrden(it) },
            tiendasDisponibles = uiState.tiendasDisponibles,
            tiendasSeleccionadas = uiState.tiendasSeleccionadas,
            onTiendaToggle = { viewModel.toggleTienda(it) },
            precioMaximo = uiState.precioMaximo,
            onPrecioMaximoChange = { viewModel.actualizarPrecioMaximo(it) },
            monedaTexto = "USD",
            soloGratis = uiState.soloGratis,
            onSoloGratisChange = { viewModel.toggleSoloGratis(it) },
            soloEnOferta = uiState.soloEnOferta,
            onSoloEnOfertaChange = { viewModel.toggleSoloEnOferta(it) },
            onLimpiarClick = { viewModel.limpiarFiltros() },
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

