package com.example.dealfinder_pc.vista.misComponentes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dealfinder_pc.R
import com.example.dealfinder_pc.ui.theme.DealFinder_PCTheme

@Composable
fun PanelTiendasFavoritas(
    tiendasDisponibles: List<String>, // Nombres de la API (Steam, GOG...)
    tiendasSeleccionadas: List<String>, // Las que el usuario tiene marcadas
    onTiendaToggle: (String) -> Unit,
    onGuardarClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
    ) {
        Spacer(modifier = Modifier.height(32.dp))

        TituloPantalla(textoRes = R.string.tiendas_titulo)

        Spacer(modifier = Modifier.height(16.dp))

        TextoNota(textoRes = R.string.tiendas_descripcion)

        Spacer(modifier = Modifier.height(24.dp))

        // Lista delegada al componente puro en ListasTarjeta.kt
        ListaTiendas(
            tiendasDisponibles = tiendasDisponibles,
            tiendasSeleccionadas = tiendasSeleccionadas,
            onTiendaToggle = onTiendaToggle,
            modifier = Modifier.weight(1f) // Ocupa todos el centro de la pantalla
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Botón Guardar anclado al fondo
        Boton(
            nombre = R.string.tiendas_guardar,
            onClick = onGuardarClick,
            isPrimary = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(40.dp))
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF191636)
@Composable
fun PanelTiendasFavoritasPreview() {
    val tiendasMock = listOf(
        "Steam", "GOG", "Epic Games Store", "Humble Store", 
        "Fanatical", "GreenManGaming", "Gamesplanet", "IndieGala"
    )
    
    val seleccionadasMock = listOf("Steam", "GOG")

    DealFinder_PCTheme {
        PanelTiendasFavoritas(
            tiendasDisponibles = tiendasMock,
            tiendasSeleccionadas = seleccionadasMock,
            onTiendaToggle = {},
            onGuardarClick = {}
        )
    }
}
