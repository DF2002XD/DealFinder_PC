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
import androidx.compose.runtime.LaunchedEffect
import com.example.dealfinder_pc.ui.theme.DealFinder_PCTheme
import com.example.dealfinder_pc.viewmodel.vm.OfertasViewModel
import com.example.dealfinder_pc.vista.misComponentes.PanelOfertas

@Composable
fun PaginaOfertas(
    onNavigateToFiltros: () -> Unit, // Cuando el usuario pincha el botón "FILTROS"
    onNavigateToDetalles: (String) -> Unit, // Cuando el usuario pincha en un juego (le pasamos el ID)
    modifier: Modifier = Modifier,
    viewModel: OfertasViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    // Recargar ofertas siempre que entremos a la pestaña para no mostrar una pantalla vacía
    LaunchedEffect(Unit) {
        viewModel.recargarDatos()
    }

    // Contenedor "tonto" para acoplarse al NavHost de la app principal
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF191636)) // Fondo retro
    ) {
        PanelOfertas(
            monedaActual = uiState.monedaActual,
            busquedaActual = uiState.busquedaActual,
            onBusquedaChange = { viewModel.buscarJuego(it) }, // Actualiza el texto de búsqueda y filtra
            onFiltrosClick = onNavigateToFiltros,
            textoFiltroActivo = uiState.textoFiltroActivo,
            ofertas = uiState.ofertas, // Le pasamos la lista ya gestionada por el ViewModel
            isLoading = uiState.isLoading,
            errorMessage = uiState.errorMessage,
            onOfertaClick = { idOferta -> 
                onNavigateToDetalles(idOferta) 
            },
            onLoadMore = {
                viewModel.cargarMasOfertas()
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

