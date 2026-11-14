package com.example.dealfinder_pc.vista.paginas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.dealfinder_pc.ui.theme.DealFinder_PCTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.dealfinder_pc.viewmodel.uistate.RegistroUIState
import com.example.dealfinder_pc.vista.misComponentes.Registro

@Composable
fun PaginaRegistro(
    onNavigateBack: () -> Unit,
    onRegisterSuccess: () -> Unit, // Cuando se cree la cuenta, salta a la app principal
    modifier: Modifier = Modifier
) {
    var uiState by remember { mutableStateOf(RegistroUIState()) }

    // Sin Scaffold local, lo delegamos a la app principal
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF191636)) // Fondo global retro
    ) {
        Registro(
            usuario = uiState.nombreUsuario,
            onUsuarioChange = { uiState = uiState.copy(nombreUsuario = it) },
            correo = uiState.correo,
            onCorreoChange = { uiState = uiState.copy(correo = it) },
            contrasenha = uiState.contrasenha,
            onContrasenhaChange = { uiState = uiState.copy(contrasenha = it) },
            repeticion = uiState.confirmarContrasenha,
            onRepeticionChange = { uiState = uiState.copy(confirmarContrasenha = it) },
            errorMessage = uiState.errorMessage,
            onRegistroClicked = {
                // Simulamos que el registro va bien
                onRegisterSuccess()
            },
            onNavigateToLogin = onNavigateBack
        )
    }
}

@Preview
@Composable
fun PaginaRegistroPreview() {
    DealFinder_PCTheme {
        PaginaRegistro(
            onNavigateBack = {},
            onRegisterSuccess = {}
        )
    }
}

