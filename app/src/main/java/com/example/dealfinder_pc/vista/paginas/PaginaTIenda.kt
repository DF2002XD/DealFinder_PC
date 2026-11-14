package com.example.dealfinder_pc.vista.paginas

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.dealfinder_pc.ui.theme.DealFinder_PCTheme
import com.example.dealfinder_pc.viewmodel.uistate.FiltrosUIState
import com.example.dealfinder_pc.vista.misComponentes.PanelTiendasFavoritas

@Composable
fun PaginaTienda(
    modifier: Modifier = Modifier
) {
    val contexto = LocalContext.current 
    
    var uiState by remember { mutableStateOf(FiltrosUIState(
        tiendasDisponibles = listOf(
            "Steam", "GOG", "Epic Games Store", "Humble Store", 
            "Fanatical", "GreenManGaming", "Gamesplanet", "IndieGala"
        ),
        tiendasSeleccionadas = listOf("Steam", "GOG")
    )) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF191636)) // Fondo global retro
    ) {
        // La barra de arriba se ha ido al AppNavigation

        // El componente principal que tiene la lista de checkboxes
        PanelTiendasFavoritas(
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
            onGuardarClick = {
                // El ViewModel guardaría la lista de tiendas preferidas
                Toast.makeText(contexto, "Tiendas guardadas", Toast.LENGTH_SHORT).show()
            }
        )
    }
}

@Preview
@Composable
fun PaginaTiendaPreview() {
    DealFinder_PCTheme {
        PaginaTienda()
    }
}

