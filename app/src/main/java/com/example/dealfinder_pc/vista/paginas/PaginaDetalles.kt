package com.example.dealfinder_pc.vista.paginas

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dealfinder_pc.ui.theme.DealFinder_PCTheme
import com.example.dealfinder_pc.viewmodel.vm.DetallesViewModel
import com.example.dealfinder_pc.viewmodel.uistate.OfertaTiendaUIState
import com.example.dealfinder_pc.vista.misComponentes.PanelDetalles

/**
 * Página principal para mostrar los detalles de un juego y sus ofertas en diferentes tiendas.
 *
 * @param idOferta ID del juego o de la oferta recibido por navegación.
 * @param onVolver Callback para navegar hacia atrás (si decides agregar un TopBar luego).
 * @param onTiendaClick Callback ejecutado al pulsar sobre la fila de una tienda.
 * @param modifier Modificador opcional.
 */
@Composable
fun PaginaDetalles(
    idOferta: String,
    modifier: Modifier = Modifier,
    onVolver: () -> Unit = {},
    onTiendaClick: (OfertaTiendaUIState) -> Unit = {},
    viewModel: DetallesViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(idOferta) {
        viewModel.cargarDetalles(idOferta)
    }

    // Llamada al componente visual puro
    PanelDetalles(
        tituloJuego = uiState.tituloJuego,
        urlImagen = uiState.urlImagen,
        minimoHistoricoInfo = uiState.minimoHistoricoInfo,
        precioMinimoHistorico = uiState.precioMinimoHistorico,
        precioNormal = uiState.precioNormal,
        ofertasEnTiendas = uiState.ofertasEnTiendas,
        onTiendaClick = onTiendaClick,
        onAgregarDeseosClick = {
            // TODOs: Lógica para guardar en Base de Datos / ViewModel
        },
        modifier = modifier.fillMaxSize()
    )
}

@Preview(showBackground = true, backgroundColor = 0xFF191636)
@Composable
fun PaginaDetallesPreview() {
    DealFinder_PCTheme {
        PaginaDetalles(
            idOferta = "demo-123"
        )
    }
}
