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
import com.example.dealfinder_pc.viewmodel.vm.PerfilViewModel
import com.example.dealfinder_pc.vista.misComponentes.TarjetaPerfil

@Composable
fun PaginaPerfil(
    onNavigateToCuenta: () -> Unit,
    onNavigateToPresupuesto: () -> Unit,
    onNavigateToMoneda: () -> Unit,
    onNavigateToTiendas: () -> Unit,
    onCerrarSesionClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: PerfilViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    // Cada vez que la pantalla de perfil entra en composición (nos movemos a ella), recargamos los datos
    LaunchedEffect(Unit) {
        viewModel.recargarDatos()
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF191636)) // Fondo global retro
    ) {
        TarjetaPerfil(
            perfilData = uiState,
            onCuentaClick = onNavigateToCuenta,
            onPresupuestoClick = onNavigateToPresupuesto,
            onMonedaClick = onNavigateToMoneda,
            onTiendasClick = onNavigateToTiendas,
            onAvisosToggle = { viewModel.toggleAvisos(!uiState.avisosActivados) },
            onCerrarSesionClick = {
                viewModel.cerrarSesionLocal()
                onCerrarSesionClick()
            }
        )
    }
}

@Preview
@Composable
fun PaginaPerfilPreview() {
    DealFinder_PCTheme {
        PaginaPerfil(
            onNavigateToCuenta = {},
            onNavigateToPresupuesto = {},
            onNavigateToMoneda = {},
            onNavigateToTiendas = {},
            onCerrarSesionClick = {}
        )
    }
}

