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
import com.example.dealfinder_pc.viewmodel.uistate.MonedaUIState

@Composable
fun PanelMoneda(
    monedasDisponibles: List<MonedaUIState>, // Ahora usa la clase del UI State
    monedaActual: String, // ej. "USD"
    onMonedaSeleccionada: (String) -> Unit,
    onGuardarClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
            // Hemos quitado el verticalScroll de aquí para dejárselo a la LazyColumn
    ) {
        Spacer(modifier = Modifier.height(32.dp))

        TituloPantalla(textoRes = R.string.moneda_titulo)

        Spacer(modifier = Modifier.height(16.dp))

        TextoNota(textoRes = R.string.moneda_descripcion)

        Spacer(modifier = Modifier.height(24.dp))

        // Lista delegada al componente puro de ListasTarjeta
        ListaMonedas(
            monedasDisponibles = monedasDisponibles,
            monedaActual = monedaActual,
            onMonedaSeleccionada = onMonedaSeleccionada,
            modifier = Modifier.weight(1f) // Esto hace que la lista ocupe todos el hueco del medio
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextoNota(textoRes = R.string.moneda_nota)

        Spacer(modifier = Modifier.height(24.dp))

        Boton(
            nombre = R.string.accion_guardar,
            onClick = onGuardarClick,
            isPrimary = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(40.dp))
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF191636)
@Composable
fun PanelMonedaPreview() {
    val opcionesMonedas = listOf(
        MonedaUIState("Dólar estadounidense", "USD"),
        MonedaUIState("Euro", "EUR"),
        MonedaUIState("Libra esterlina", "GBP"),
        MonedaUIState("Peso mexicano", "MXN"),
        MonedaUIState("Peso argentino", "ARS"),
        MonedaUIState("Yen japonés", "JPY"),
        MonedaUIState("Franco suizo", "CHF"),
        MonedaUIState("Dólar canadiense", "CAD")
    )

    DealFinder_PCTheme {
        PanelMoneda(
            monedasDisponibles = opcionesMonedas,
            monedaActual = "USD",
            onMonedaSeleccionada = {},
            onGuardarClick = {}
        )
    }
}
