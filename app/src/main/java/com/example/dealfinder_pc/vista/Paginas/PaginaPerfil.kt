package com.example.dealfinder_pc.vista.Paginas

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
import androidx.compose.ui.tooling.preview.Preview
import com.example.dealfinder_pc.ui.theme.DealFinder_PCTheme
import com.example.dealfinder_pc.viewmodel.uistate.PerfilUIState
import com.example.dealfinder_pc.vista.misComponentes.TarjetaPerfil

@Composable
fun PaginaPerfil(
    onNavigateToCuenta: () -> Unit,
    onNavigateToPresupuesto: () -> Unit,
    onNavigateToMoneda: () -> Unit,
    onNavigateToTiendas: () -> Unit,
    onCerrarSesionClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    // ESTADOS SIMULADOS PARA DEMOSTRACIÓN
    var avisosActivados by remember { mutableStateOf(true) }
    
    val perfilMock = PerfilUIState(
        nombreUsuario = "JUGADOR_01",
        correoUsuario = "jugador@correo.com",
        presupuestoTexto = "$80.00",
        monedaActual = "USD",
        tiendasTexto = "Steam, GOG",
        avisosActivados = avisosActivados
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF191636)) // Fondo global retro
    ) {
        TarjetaPerfil(
            perfilData = perfilMock,
            onCuentaClick = onNavigateToCuenta,
            onPresupuestoClick = onNavigateToPresupuesto,
            onMonedaClick = onNavigateToMoneda,
            onTiendasClick = onNavigateToTiendas,
            onAvisosToggle = { 
                avisosActivados = !avisosActivados
                // Próximamente -> El ViewModel guardaría esta preferencia
            },
            onCerrarSesionClick = onCerrarSesionClick
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

