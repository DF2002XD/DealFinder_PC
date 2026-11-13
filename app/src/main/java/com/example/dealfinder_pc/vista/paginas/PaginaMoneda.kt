package com.example.dealfinder_pc.vista.paginas

import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.dealfinder_pc.ui.theme.DealFinder_PCTheme
import com.example.dealfinder_pc.viewmodel.uistate.MonedaUIState
import com.example.dealfinder_pc.vista.misComponentes.PanelMoneda

@Composable
fun PaginaMoneda(
    modifier: Modifier = Modifier
) {
    val contexto = LocalContext.current 
    
    // ESTADOS SIMULADOS PARA DEMOSTRACIÓN
    var monedaActual by remember { mutableStateOf("USD") }
    
    val monedasDisponiblesMock = listOf(
        MonedaUIState("Dólar estadounidense", "USD"),
        MonedaUIState("Euro", "EUR"),
        MonedaUIState("Libra esterlina", "GBP"),
        MonedaUIState("Peso mexicano", "MXN"),
        MonedaUIState("Peso argentino", "ARS")
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF191636)) // Fondo global retro
    ) {
        // El componente principal que tiene la lista de radios
        PanelMoneda(
            monedasDisponibles = monedasDisponiblesMock,
            monedaActual = monedaActual,
            onMonedaSeleccionada = { nuevaMoneda -> 
                monedaActual = nuevaMoneda 
            },
            onGuardarClick = {
                // El ViewModel guardaría esta moneda y recalcularía todos los precios
                Toast.makeText(contexto, "Moneda guardada con éxito", Toast.LENGTH_SHORT).show()
                // Y a diferencia de la cuenta, como esto es una simple elección, podemos volver directamente
                // O mantenerte aquí, según prefieras. En este caso te mantenemos en la pantalla por coherencia:
            }
        )
    }
}

@Preview
@Composable
fun PaginaMonedaPreview() {
    DealFinder_PCTheme {
        PaginaMoneda()
    }
}

