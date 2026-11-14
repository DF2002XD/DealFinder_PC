package com.example.dealfinder_pc.vista.paginas

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dealfinder_pc.ui.theme.DealFinder_PCTheme
import com.example.dealfinder_pc.viewmodel.vm.MonedaViewModel
import com.example.dealfinder_pc.vista.misComponentes.PanelMoneda

@Composable
fun PaginaMoneda(
    modifier: Modifier = Modifier,
    viewModel: MonedaViewModel = viewModel()
) {
    val contexto = LocalContext.current 
    val uiState by viewModel.uiState.collectAsState()

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF191636)) // Fondo global retro
    ) {
        // El componente principal que tiene la lista de radios
        PanelMoneda(
            monedasDisponibles = uiState.monedasDisponibles,
            monedaActual = uiState.monedaActual,
            onMonedaSeleccionada = { nuevaMoneda -> 
                viewModel.seleccionarMoneda(nuevaMoneda) 
            },
            onGuardarClick = {
                viewModel.guardarMoneda(
                    onSuccess = {
                        Toast.makeText(contexto, "Moneda guardada con éxito", Toast.LENGTH_SHORT).show()
                    }
                )
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

