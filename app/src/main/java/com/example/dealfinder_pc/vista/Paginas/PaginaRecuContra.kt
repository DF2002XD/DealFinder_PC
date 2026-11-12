package com.example.dealfinder_pc.vista.Paginas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.dealfinder_pc.ui.theme.DealFinder_PCTheme
import com.example.dealfinder_pc.vista.misComponentes.RecuperarContrasenha

@Composable
fun PaginaRecuContra(
    onNavigateBack: () -> Unit, // Para cuando pulsa "Volver"
    onEmailSent: () -> Unit, // Para cuando el correo se envía correctamente (generalmente vuelve al login)
    modifier: Modifier = Modifier
) {
    // Usamos el Box global para el fondo como en Login/Registro
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF191636)) // Fondo retro de tu app
    ) {
        RecuperarContrasenha(
            errorMessage = null, // De momento sin mensajes de error
            onEnviarClicked = { _ -> 
                // Aquí el ViewModel llamará a Firebase auth para enviar el correo
                onEmailSent()
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

