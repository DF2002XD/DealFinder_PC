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
import com.example.dealfinder_pc.viewmodel.uistate.OfertaUIState
import com.example.dealfinder_pc.vista.misComponentes.PanelOfertas

@Composable
fun PaginaOfertas(
    onNavigateToFiltros: () -> Unit, // Cuando el usuario pincha el botón "FILTROS"
    onNavigateToDetalles: (String) -> Unit, // Cuando el usuario pincha en un juego (le pasamos el ID)
    modifier: Modifier = Modifier
) {
    // ESTADOS SIMULADOS (Próximamente los controlará el ViewModel)

    var busquedaActual by remember { mutableStateOf("") }
    
    // Lista de prueba para que la pantalla no salga vacía
    val ofertasMock = listOf(
        OfertaUIState("1", "Silver Pines", "Steam · Meta 85", "$35.90", "$22.26", "-38%", "SP"),
        OfertaUIState("2", "Suicide Squad: Kill the Justice Le...", "Epic Games Store · Meta 63", "$69.99", "$3.49", "-95%", "SS"),
        OfertaUIState("3", "Frosthaven", "GameBillet · Steam 70%", "$39.99", "$23.19", "-42%", "FH"),
        OfertaUIState("4", "EA SPORTS PGA TOUR", "Epic Games Store · Meta 72", "$69.99", "$6.99", "-90%", "PG"),
        OfertaUIState("5", "Bad Cheese", "Epic Games Store · Meta 63", "$12.99", "GRATIS", "-100%", "BC")
    )

    // Simulamos la lógica del ViewModel: Filtramos la lista según lo que el usuario ha escrito
    val ofertasFiltradas = if (busquedaActual.isBlank()) {
        ofertasMock // Si no ha escrito nada, mostramos todas
    } else {
        ofertasMock.filter { oferta ->
            // Comprobamos si el título del juego contiene el texto buscado (ignorando mayúsculas/minúsculas)
            oferta.titulo.contains(busquedaActual, ignoreCase = true)
        }
    }

    // Contenedor "tonto" para acoplarse al NavHost de la app principal
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF191636)) // Fondo retro
    ) {
        PanelOfertas(
            monedaActual = "USD",
            busquedaActual = busquedaActual,
            onBusquedaChange = { busquedaActual = it }, // Actualiza el texto de búsqueda al teclear
            onFiltrosClick = onNavigateToFiltros,
            textoFiltroActivo = "Todas las ofertas · mejor oferta primero",
            ofertas = ofertasFiltradas, // Le pasamos la lista ya filtrada
            onOfertaClick = { idOferta -> 
                onNavigateToDetalles(idOferta) 
            }
        )
    }
}

@Preview
@Composable
fun PaginaOfertasPreview() {
    DealFinder_PCTheme {
        PaginaOfertas(
            onNavigateToFiltros = {},
            onNavigateToDetalles = {}
        )
    }
}

