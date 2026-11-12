package com.example.dealfinder_pc.vista.Paginas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.dealfinder_pc.ui.theme.DealFinder_PCTheme
import com.example.dealfinder_pc.vista.misComponentes.Registro

@Composable
fun PaginaRegistro(
    onNavigateBack: () -> Unit,
    onRegisterSuccess: () -> Unit, // Cuando se cree la cuenta, salta a la app principal
    modifier: Modifier = Modifier
) {
    // Sin Scaffold local, lo delegamos a la app principal
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF191636)) // Fondo global retro
    ) {
        Registro(
            errorMessage = null, // De momento sin errores
            onRegistroClicked = { _, _, _, _ ->
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

