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
import com.example.dealfinder_pc.vista.misComponentes.Login

@Composable
fun PaginaLogin(
    onNavigateToRegistro: () -> Unit,
    onNavigateToRecuperarContrasenha: () -> Unit,
    onLoginSuccess: () -> Unit, // Cuando el usuario se loguea bien, salta a la app principal
    modifier: Modifier = Modifier
) {
    // Estado de prueba para la UI hasta que conectemos el ViewModel
    var keepLogged by remember { mutableStateOf(false) }

    // En lugar de Scaffold, usamos una Box simple que ocupa el espacio que le asigne el NavHost
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF191636)) // Fondo global retro de tu app
    ) {
        // Tu pieza de Lego "Login"
        Login(
            keepLogged = keepLogged,
            onKeepLoggedChange = { keepLogged = it },
            errorMessage = null, // De momento sin error
            onLoginClicked = { _, _ ->
                onLoginSuccess() 
            },
            onNavigateToRegister = onNavigateToRegistro,
            onNavigateToResetPassword = onNavigateToRecuperarContrasenha
        )
    }
}

@Preview
@Composable
fun PaginaLoginPreview() {
    DealFinder_PCTheme {
        PaginaLogin(
            onNavigateToRegistro = {},
            onNavigateToRecuperarContrasenha = {},
            onLoginSuccess = {}
        )
    }
}

