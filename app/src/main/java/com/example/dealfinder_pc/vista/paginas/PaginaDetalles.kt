package com.example.dealfinder_pc.vista.paginas

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.dealfinder_pc.ui.theme.DealFinder_PCTheme
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
    onTiendaClick: (OfertaTiendaUIState) -> Unit = {}
) {
    // TODOs: Aquí eventualmente instanciarás o recibirás tu ViewModel
    // val uiState = viewModel.obtenerDetallesJuego(idOferta).collectAsState().value

    // ---------------------------------------------------------
    // MOCK DATA: Reemplazar con datos reales del UIState cuando conectes el ViewModel
    // ---------------------------------------------------------
    val tituloJuego = "Prison Tycoon 3: Lockdown"
    val urlImagen = "https://images.igdb.com/igdb/image/upload/t_cover_big/co1q3k.jpg"
    val minimoHistoricoInfo = "Mínimo histórico · 10/10/2016"
    val precioMinimoHistorico = "$1.99"
    val precioNormal = "$9.99"

    val ofertasEnTiendas = listOf(
        OfertaTiendaUIState(
            nombreTienda = "Steam",
            precio = "$3.49",
            descuentoTexto = "-65%",
            esLaMejor = true,
            urlLogo = "https://upload.wikimedia.org/wikipedia/commons/thumb/8/83/Steam_icon_logo.svg/512px-Steam_icon_logo.svg.png"
        ),
        OfertaTiendaUIState(
            nombreTienda = "Fanatical",
            precio = "$7.48",
            descuentoTexto = "-25%",
            esLaMejor = false
        ),
        OfertaTiendaUIState(
            nombreTienda = "IndieGala",
            precio = "$9.99",
            descuentoTexto = null,
            esLaMejor = false
        )
    )
    // ---------------------------------------------------------

    // Llamada al componente visual puro que creamos antes
    PanelDetalles(
        tituloJuego = tituloJuego,
        urlImagen = urlImagen,
        minimoHistoricoInfo = minimoHistoricoInfo,
        precioMinimoHistorico = precioMinimoHistorico,
        precioNormal = precioNormal,
        ofertasEnTiendas = ofertasEnTiendas,
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
