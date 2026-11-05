package com.example.dealfinder_pc.vista.misComponentes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.dealfinder_pc.R
import androidx.compose.ui.unit.dp
import com.example.dealfinder_pc.ui.theme.DealFinder_PCTheme
import com.example.dealfinder_pc.viewmodel.uistate.DeseoUIState
import com.example.dealfinder_pc.viewmodel.uistate.OfertaUIState


/**
 * Muestra una lista vertical y desplazable (LazyColumn) de tarjetas de ofertas.
 *
 * @param ofertas Lista de estados UI [OfertaUIState] que contiene la información de cada oferta.
 * @param onOfertaClick Callback que se ejecuta cuando el usuario pulsa sobre una oferta. Devuelve el ID de la oferta.
 * @param modifier Modificador opcional para personalizar el diseño del contenedor de la lista.
 */
@Composable
fun ListaOfertas(
    ofertas: List<OfertaUIState>,
    onOfertaClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(ofertas) { oferta ->
            TarjetaOferta(
                titulo = oferta.titulo,
                tienda = oferta.tiendaYMeta,
                precioOriginal = oferta.precioOriginal,
                precioRebajado = oferta.precioRebajado,
                descuento = oferta.descuento,
                inicialesImagen = oferta.iniciales,
                colorImagen = oferta.colorEtiqueta,
                onClick = { onOfertaClick(oferta.id) }
            )
        }
    }
}

/**
 * Muestra una lista vertical y desplazable (LazyColumn) de tarjetas pertenecientes a la lista de deseos.
 *
 * @param juegosEnDeseos Lista de estados UI [DeseoUIState] que contiene la información de los juegos deseados.
 * @param onDeseoClick Callback que se ejecuta cuando el usuario pulsa sobre el cuerpo de la tarjeta de deseo. Devuelve el ID del juego.
 * @param onBotonComprarClick Callback que se ejecuta al pulsar el botón de acción (ej. "COMPRADO") de la tarjeta. Devuelve el ID del juego.
 * @param modifier Modificador opcional para personalizar el diseño del contenedor de la lista.
 */
@Composable
fun ListaDeseos(
    juegosEnDeseos: List<DeseoUIState>,
    onDeseoClick: (String) -> Unit,
    onBotonComprarClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(juegosEnDeseos) { deseo ->
            TarjetaDeseo(
                titulo = deseo.titulo,
                precio = deseo.precioRebajado,
                inicialesImagen = deseo.iniciales,
                textoBoton = deseo.textoBoton,
                colorImagen = deseo.colorEtiqueta,
                onClick = { onDeseoClick(deseo.id) },
                onBotonClick = { onBotonComprarClick(deseo.id) }
            )
        }
    }
}


@Preview(showBackground = true, backgroundColor = 0xFF191636)
@Composable
fun ListaOfertasPreview() {
    val ofertasMock = listOf(
        OfertaUIState("1", "Silver Pines", "Steam · Meta 85", "$35.90", "$22.26", "-38%", "SP", Color(0xFF33FFFF)),
        OfertaUIState("2", "Suicide Squad: Kill the Justice Le...", "Epic Games Store · Meta 63", "$69.99", "$3.49", "-95%", "SS", Color(0xFFFF4D94)),
        OfertaUIState("3", "Frosthaven", "GameBillet · Steam 70%", "$39.99", "$23.19", "-42%", "FH", Color(0xFF55FF88)),
        OfertaUIState("4", "EA SPORTS PGA TOUR", "Epic Games Store · Meta 72", "$69.99", "$6.99", "-90%", "PG", Color(0xFFFFCC33)),
        OfertaUIState("5", "Bad Cheese", "Epic Games Store · Meta 63", "$12.99", "GRATIS", "-100%", "BC", Color(0xFFB399FF))
    )

    DealFinder_PCTheme {
        ListaOfertas(
            ofertas = ofertasMock,
            onOfertaClick = {}
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF191636)
@Composable
fun ListaDeseosPreview() {
    val deseosMock = listOf(
        DeseoUIState("1", "Silver Pines", "$22.26", "SP", Color(0xFF33FFFF)),
        DeseoUIState("3", "Frosthaven", "$23.19", "FH", Color(0xFF55FF88)),
        DeseoUIState("4", "EA SPORTS PGA TOUR", "$6.99", "PG", Color(0xFFFFCC33))
    )

    DealFinder_PCTheme {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                TituloPantalla(textoRes = R.string.lista_titulo)
                Spacer(modifier = Modifier.height(12.dp))
                TextoTotalDeseos(texto = "3 juegos · aprox. $52.44")
            }

            ListaDeseos(
                juegosEnDeseos = deseosMock,
                onDeseoClick = {},
                onBotonComprarClick = {},
                modifier = Modifier.weight(1f) // Ocupa el centro
            )

            TextoNota(
                textoRes = R.string.lista_nota,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}