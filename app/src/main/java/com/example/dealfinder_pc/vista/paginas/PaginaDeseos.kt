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
import com.example.dealfinder_pc.viewmodel.vm.DeseosViewModel
import com.example.dealfinder_pc.vista.misComponentes.TarjetaDeseos

@Composable
fun PaginaDeseos(
    onDeseoClick: (String) -> Unit, // Por si quieres ver los detalles del juego
    modifier: Modifier = Modifier,
    viewModel: DeseosViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF191636)) // Fondo global retro
    ) {
        TarjetaDeseos(
            juegosEnDeseos = uiState.juegosEnDeseos,
            resumenTexto = uiState.precioTotalEstimado,
            onDeseoClick = onDeseoClick,
            onBotonComprarClick = { id ->
                viewModel.marcarComoComprado(id)
            },
            onEliminarClick = { id ->
                viewModel.eliminarJuego(id)
            }
        )
    }
}

@Preview
@Composable
fun PaginaDeseosPreview() {
    DealFinder_PCTheme {
        PaginaDeseos(
            onDeseoClick = {}
        )
    }
}

