package com.example.dealfinder_pc.vista.misComponentes


import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.example.dealfinder_pc.R
import com.example.dealfinder_pc.ui.theme.DealFinder_PCTheme
import com.example.dealfinder_pc.ui.theme.displayFontFamily
import com.example.dealfinder_pc.ui.theme.extraColors
import com.example.dealfinder_pc.viewmodel.uistate.OfertaTiendaUIState

@Composable
fun PanelDetalles(
    tituloJuego: String,
    urlImagen: String, // Cambiado a URL de imagen
    minimoHistoricoInfo: String, // ej: "Mínimo histórico · 10/10/2016"
    precioMinimoHistorico: String, // ej: "$1.99"
    precioNormal: String, // ej: "$9.99"
    ofertasEnTiendas: List<OfertaTiendaUIState>,
    onTiendaClick: (OfertaTiendaUIState) -> Unit,
    onAgregarDeseosClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
    ) {
        // --- 1. IMAGEN DE CABECERA GIGANTE ---
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp) // Imagen muy grande como en la captura
                .border(2.dp, MaterialTheme.colorScheme.onSurface, RectangleShape),
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                model = urlImagen, // Aquí cargamos la imagen (puede ser URL de internet o R.drawable...)
                contentDescription = "Portada de $tituloJuego",
                contentScale = ContentScale.Crop, // Corta la imagen para llenar el contenedor
                modifier = Modifier.fillMaxSize()
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // El contenido completo como una lista delegada
        ListaOfertasTiendas(
            ofertasEnTiendas = ofertasEnTiendas,
            onTiendaClick = onTiendaClick,
            modifier = Modifier.weight(1f)
        ) {
            // --- 2. TÍTULO DEL JUEGO AMARILLO ---
            Text(
                text = tituloJuego.uppercase(),
                fontFamily = displayFontFamily,
                color = MaterialTheme.colorScheme.primary,
                fontSize = 22.sp, // Tamaño de tu TituloPantalla
                lineHeight = 28.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            // --- 3. INFORMACIÓN DE PRECIOS GLOBALES ---
            FilaInformacionPrecio(
                etiqueta = minimoHistoricoInfo,
                precio = precioMinimoHistorico,
                colorPrecio = MaterialTheme.extraColors.success // Verde
            )
            
            Spacer(modifier = Modifier.height(8.dp))

            FilaInformacionPrecio(
                etiqueta = stringResource(R.string.detalle_precio_normal), 
                precio = precioNormal,
                colorPrecio = MaterialTheme.colorScheme.onSurface // Blanco
            )

            Spacer(modifier = Modifier.height(16.dp))
        }

        // --- 5. BOTÓN LISTA DE DESEOS ANCLADO AL FONDO ---
        Boton(
            nombre = R.string.detalle_agregar_lista, 
            onClick = onAgregarDeseosClick,
            isPrimary = true,
            modifier = Modifier.fillMaxWidth()
        )
        
        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF191636)
@Composable
fun PanelDetallesPreview() {
    val ofertasSimuladas = listOf(
        OfertaTiendaUIState("Steam", "$3.49", "-65%", esLaMejor = true, urlLogo = "https://upload.wikimedia.org/wikipedia/commons/thumb/8/83/Steam_icon_logo.svg/512px-Steam_icon_logo.svg.png"),
        OfertaTiendaUIState("Fanatical", "$7.48", "-25%", esLaMejor = false),
        OfertaTiendaUIState("IndieGala", "$9.99", null, esLaMejor = false)
    )

    DealFinder_PCTheme {
        PanelDetalles(
            tituloJuego = "Prison Tycoon 3: Lockdown",
            urlImagen = "https://images.igdb.com/igdb/image/upload/t_cover_big/co1q3k.jpg", // URL de prueba
            minimoHistoricoInfo = "Mínimo histórico · 10/10/2016",
            precioMinimoHistorico = "$1.99",
            precioNormal = "$9.99",
            ofertasEnTiendas = ofertasSimuladas,
            onTiendaClick = {},
            onAgregarDeseosClick = {}
        )
    }
}

