package com.example.dealfinder_pc.vista.paginas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.dealfinder_pc.ui.theme.DealFinder_PCTheme
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dealfinder_pc.viewmodel.vm.RecuperarContraViewModel
import com.example.dealfinder_pc.vista.misComponentes.RecuperarContrasenha

@Composable
fun PaginaRecuContra(
    onNavigateBack: () -> Unit, // Para cuando pulsa "Volver"
    onEmailSent: () -> Unit, // Para cuando el correo se envía correctamente (generalmente vuelve al login)
    modifier: Modifier = Modifier,
    viewModel: RecuperarContraViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    // Usamos el Box global para el fondo como en Login/Registro
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF191636)) // Fondo retro de tu app
    ) {
        RecuperarContrasenha(
            correo = uiState.correo,
            onCorreoChange = { viewModel.actualizarCorreo(it) },
            errorMessage = uiState.errorMessage,
            onEnviarClicked = { 
                viewModel.enviarCorreoRecuperacion(onSuccess = onEmailSent)
            },
            onNavigateToLogin = onNavigateBack
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PaginaRecuContraPreview() {
    DealFinder_PCTheme {
        PaginaRecuContra(
            onNavigateBack = {},
            onEmailSent = {}
        )
    }
}

