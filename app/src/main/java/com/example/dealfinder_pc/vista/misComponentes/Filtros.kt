package com.example.dealfinder_pc.vista.misComponentes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dealfinder_pc.R
import com.example.dealfinder_pc.ui.theme.DealFinder_PCTheme

@Composable
fun PanelFiltros(
    ordenSeleccionado: String,
    onOrdenSeleccionado: (String) -> Unit,
    tiendasDisponibles: List<String>,
    tiendasSeleccionadas: List<String>,
    onTiendaToggle: (String) -> Unit,
    precioMaximo: String,
    onPrecioMaximoChange: (String) -> Unit,
    monedaTexto: String, // ej. "USD"
    soloGratis: Boolean,
    onSoloGratisChange: (Boolean) -> Unit,
    soloEnOferta: Boolean,
    onSoloEnOfertaChange: (Boolean) -> Unit,
    onLimpiarClick: () -> Unit,
    onAplicarClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val opcionesOrden = listOf(
        R.string.orden_mejor_oferta,
        R.string.orden_menor_precio,
        R.string.orden_mayor_precio,
        R.string.orden_recientes,
        R.string.orden_mayor_descuento
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TituloPantalla(textoRes = R.string.filtros_titulo)
            BotonVolverLimpiar(
                texto = stringResource(R.string.filtros_limpiar),
                onClick = onLimpiarClick,
                isPrimary = false
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        TextoEncabezado(textoRes = R.string.filtros_ordenar_por)
        Spacer(modifier = Modifier.height(12.dp))
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            opcionesOrden.forEach { idTexto ->
                val textoOrden = stringResource(idTexto)
                BotonOpciones(
                    texto = textoOrden,
                    seleccionado = ordenSeleccionado == textoOrden,
                    onClick = { onOrdenSeleccionado(textoOrden) }
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        TextoEncabezado(textoRes = R.string.filtros_tiendas)
        Spacer(modifier = Modifier.height(12.dp))
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            tiendasDisponibles.forEach { tienda ->
                BotonOpciones(
                    texto = tienda,
                    seleccionado = tiendasSeleccionadas.contains(tienda),
                    onClick = { onTiendaToggle(tienda) }
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        TextoEtiqueta(texto = stringResource(R.string.filtros_precio_maximo, monedaTexto))
        Spacer(modifier = Modifier.height(8.dp))
        CampoImporte(
            valor = precioMaximo,
            onValorCambiado = onPrecioMaximoChange
        )

        Spacer(modifier = Modifier.height(24.dp))

        BotonCheckbox(
            textoRes = R.string.filtros_solo_gratis,
            marcado = soloGratis,
            onMarcadoChange = onSoloGratisChange
        )

        Spacer(modifier = Modifier.height(12.dp))

        BotonCheckbox(
            textoRes = R.string.filtros_solo_oferta,
            marcado = soloEnOferta,
            onMarcadoChange = onSoloEnOfertaChange
        )

        Spacer(modifier = Modifier.height(32.dp))

        Boton(
            nombre = R.string.filtros_aplicar,
            onClick = onAplicarClick,
            isPrimary = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(40.dp))
    }
}

@Preview
@Composable
fun PanelFiltrosPreview() {
    val tiendasMock = listOf("STEAM", "EPIC", "GOG", "HUMBLE", "FANATICAL") // Simulación del ViewModel

    DealFinder_PCTheme {
        PanelFiltros(
            ordenSeleccionado = "",
            onOrdenSeleccionado = {},
            tiendasDisponibles = tiendasMock,
            tiendasSeleccionadas = listOf(),
            onTiendaToggle = {},
            precioMaximo = "15.00",
            onPrecioMaximoChange = {},
            monedaTexto = "USD",
            soloGratis = false,
            onSoloGratisChange = {},
            soloEnOferta = false,
            onSoloEnOfertaChange = {},
            onLimpiarClick = {},
            onAplicarClick = {}
        )
    }
}
