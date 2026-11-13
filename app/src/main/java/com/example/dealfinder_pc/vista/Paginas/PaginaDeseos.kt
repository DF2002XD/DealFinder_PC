package com.example.dealfinder_pc.vista.Paginas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.dealfinder_pc.ui.theme.DealFinder_PCTheme
import com.example.dealfinder_pc.viewmodel.uistate.DeseoUIState
import com.example.dealfinder_pc.vista.misComponentes.TarjetaDeseos

@Composable
fun PaginaDeseos(
    onDeseoClick: (String) -> Unit, // Por si quieres ver los detalles del juego
    modifier: Modifier = Modifier
) {
    // ---------------------------------------------------------
    // ESTADOS SIMULADOS PARA DEMOSTRACIÓN (Lógica del ViewModel)
    // ---------------------------------------------------------
    val deseosMock = listOf(
        DeseoUIState("1", "Silver Pines", "$22.26", "SP", Color(0xFF33FFFF)),
        DeseoUIState("3", "Frosthaven", "$23.19", "FH", Color(0xFF55FF88)),
        DeseoUIState("4", "EA SPORTS PGA TOUR", "$6.99", "PG", Color(0xFFFFCC33))
    )
    val precioTotalEstimado = "3 juegos · aprox. $52.44"

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF191636)) // Fondo global retro
    ) {
        TarjetaDeseos(
            juegosEnDeseos = deseosMock,
            resumenTexto = precioTotalEstimado,
            onDeseoClick = onDeseoClick,
            onBotonComprarClick = { _ ->
                // El ViewModel descontaría este precio del presupuesto mensual
            },
            onEliminarClick = { _ ->
                // El ViewModel borraría el juego de la lista
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

