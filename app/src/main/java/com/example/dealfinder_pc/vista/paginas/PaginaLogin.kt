package com.example.dealfinder_pc.vista.paginas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.dealfinder_pc.ui.theme.DealFinder_PCTheme
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dealfinder_pc.viewmodel.vm.LoginViewModel
import com.example.dealfinder_pc.vista.misComponentes.Login

@Composable
fun PaginaLogin(
    onNavigateToRegistro: () -> Unit,
    onNavigateToRecuperarContrasenha: () -> Unit,
    onLoginSuccess: () -> Unit, // Cuando el usuario se loguea bien, salta a la app principal
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = viewModel()
) {
    // Conectamos con el estado de la UI del ViewModel
    val uiState by viewModel.uiState.collectAsState()

    // En lugar de Scaffold, usamos una Box simple que ocupa el espacio que le asigne el NavHost
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF191636)) // Fondo global retro de tu app
    ) {
        Login(
            correo = uiState.correo,
            onCorreoChange = { viewModel.actualizarCorreo(it) },
            contrasenha = uiState.contrasenha,
            onContrasenhaChange = { viewModel.actualizarContrasenha(it) },
            keepLogged = uiState.keepLogged,
            onKeepLoggedChange = { viewModel.toggleKeepLogged(it) },
            errorMessage = uiState.errorMessage,
            onLoginClicked = {
                // Delegamos la acción al ViewModel
                viewModel.iniciarSesion(onSuccess = onLoginSuccess)
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

