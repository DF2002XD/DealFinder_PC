package com.example.dealfinder_pc.vista.misComponentes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dealfinder_pc.R
import com.example.dealfinder_pc.ui.theme.DealFinder_PCTheme
import com.example.dealfinder_pc.viewmodel.uistate.DeseoUIState

/**
 * Componente que representa la vista completa de la Lista de Deseos,
 * sin incluir la barra de navegación inferior.
 */
@Composable
fun TarjetaDeseos(
    juegosEnDeseos: List<DeseoUIState>,
    resumenTexto: String,
    onDeseoClick: (String) -> Unit,
    onBotonComprarClick: (String) -> Unit,
    onEliminarClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        // Cabecera con título y resumen
        Column(modifier = Modifier.padding(16.dp)) {
            TituloPantalla(textoRes = R.string.lista_titulo)
            Spacer(modifier = Modifier.height(12.dp))
            TextoResumen(texto = resumenTexto)
        }

        // Lista desplazable de deseos (ocupa el espacio disponible)
        ListaDeseos(
            juegosEnDeseos = juegosEnDeseos,
            onDeseoClick = onDeseoClick,
            onBotonComprarClick = onBotonComprarClick,
            onEliminarClick = onEliminarClick,
            modifier = Modifier.weight(1f)
        )

        // Nota explicativa al pie
        TextoNota(
            textoRes = R.string.lista_nota,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF191636)
@Composable
fun TarjetaDeseosPreview() {
    val deseosMock = listOf(
        DeseoUIState("1", "Silver Pines", "$22.26", "SP", Color(0xFF33FFFF)),
        DeseoUIState("3", "Frosthaven", "$23.19", "FH", Color(0xFF55FF88)),
        DeseoUIState("4", "EA SPORTS PGA TOUR", "$6.99", "PG", Color(0xFFFFCC33))
    )

    DealFinder_PCTheme {
        TarjetaDeseos(
            juegosEnDeseos = deseosMock,
            resumenTexto = "3 juegos · aprox. $52.44",
            onDeseoClick = {},
            onBotonComprarClick = {},
            onEliminarClick = {}
        )
    }
}
