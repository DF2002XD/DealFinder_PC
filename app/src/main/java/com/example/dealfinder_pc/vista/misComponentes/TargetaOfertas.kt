package com.example.dealfinder_pc.vista.misComponentes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dealfinder_pc.R
import com.example.dealfinder_pc.ui.theme.DealFinder_PCTheme
import com.example.dealfinder_pc.ui.theme.bodyFontFamily
import com.example.dealfinder_pc.viewmodel.uistate.OfertaUIState

@Composable
fun PanelOfertas(
    monedaActual: String, // Para pintar "Precios en $monedaActual"
    busquedaActual: String,
    onBusquedaChange: (String) -> Unit,
    onFiltrosClick: () -> Unit,
    textoFiltroActivo: String, // Ej: "Todas las ofertas · mejor oferta primero"
    ofertas: List<OfertaUIState>,
    onOfertaClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 24.dp) // Padding superior general
    ) {
        // 1. CABECERA ("OFERTAS" y "Precios en...")
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            TituloPantalla(textoRes = R.string.nav_ofertas)
            
            Text(
                text = "Precios en $monedaActual",
                fontFamily = bodyFontFamily,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 14.sp
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 2. BUSCADOR
        Buscador(
            textoBusqueda = busquedaActual,
            onTextoCambiado = onBusquedaChange,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 3. BARRA DE FILTROS
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BotonVolverLimpiar(
                texto = "FILTROS", // Si quieres puedes sacarlo a strings.xml
                onClick = onFiltrosClick,
                isPrimary = false,
                mostrarIconoVolver = false
            )
            
            Spacer(modifier = Modifier.width(12.dp))
            
            Text(
                text = textoFiltroActivo,
                fontFamily = bodyFontFamily,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 14.sp,
                modifier = Modifier.weight(1f) // Para que el texto ocupe el resto y si es largo baje de línea
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 4. LISTA DE OFERTAS
        // Al usar weight(1f), la lista tomará todos el espacio restante hasta el fondo
        ListaOfertas(
            ofertas = ofertas,
            onOfertaClick = onOfertaClick,
            modifier = Modifier.weight(1f) 
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF191636)
@Composable
fun PanelOfertasPreview() {
    val ofertasMock = listOf(
        OfertaUIState("1", "Silver Pines", "Steam · Meta 85", "$35.90", "$22.26", "-38%", "SP", Color(0xFF33FFFF)),
        OfertaUIState("2", "Suicide Squad: Kill the Justice Le...", "Epic Games Store · Meta 63", "$69.99", "$3.49", "-95%", "SS", Color(0xFFFF4D94)),
        OfertaUIState("3", "Frosthaven", "GameBillet · Steam 70%", "$39.99", "$23.19", "-42%", "FH", Color(0xFF55FF88)),
        OfertaUIState("4", "EA SPORTS PGA TOUR", "Epic Games Store · Meta 72", "$69.99", "$6.99", "-90%", "PG", Color(0xFFFFCC33)),
        OfertaUIState("5", "Bad Cheese", "Epic Games Store · Meta 63", "$12.99", "GRATIS", "-100%", "BC", Color(0xFFB399FF))
    )

    DealFinder_PCTheme {
        PanelOfertas(
            monedaActual = "USD",
            busquedaActual = "",
            onBusquedaChange = {},
            onFiltrosClick = {},
            textoFiltroActivo = "Todas las ofertas · mejor oferta primero",
            ofertas = ofertasMock,
            onOfertaClick = {}
        )
    }
}

