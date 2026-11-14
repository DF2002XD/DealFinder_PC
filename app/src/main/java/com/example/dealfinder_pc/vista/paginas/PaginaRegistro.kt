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
import com.example.dealfinder_pc.viewmodel.vm.RegistroViewModel
import com.example.dealfinder_pc.vista.misComponentes.Registro

@Composable
fun PaginaRegistro(
    onNavigateBack: () -> Unit,
    onRegisterSuccess: () -> Unit, // Cuando se cree la cuenta, salta a la app principal
    modifier: Modifier = Modifier,
    viewModel: RegistroViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    // Sin Scaffold local, lo delegamos a la app principal
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF191636)) // Fondo global retro
    ) {
        Registro(
            usuario = uiState.nombreUsuario,
            onUsuarioChange = { viewModel.actualizarUsuario(it) },
            correo = uiState.correo,
            onCorreoChange = { viewModel.actualizarCorreo(it) },
            contrasenha = uiState.contrasenha,
            onContrasenhaChange = { viewModel.actualizarContrasenha(it) },
            repeticion = uiState.confirmarContrasenha,
            onRepeticionChange = { viewModel.actualizarRepeticion(it) },
            errorMessage = uiState.errorMessage,
            onRegistroClicked = {
                viewModel.registrarUsuario(onSuccess = onRegisterSuccess)
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

