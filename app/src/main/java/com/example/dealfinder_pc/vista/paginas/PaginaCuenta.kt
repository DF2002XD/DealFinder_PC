package com.example.dealfinder_pc.vista.paginas

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dealfinder_pc.ui.theme.DealFinder_PCTheme
import com.example.dealfinder_pc.viewmodel.vm.CuentaViewModel
import com.example.dealfinder_pc.vista.misComponentes.PanelCuenta

@Composable
fun PaginaCuenta(
    onRecuperarContrasenhaClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CuentaViewModel = viewModel()
) {
    val contexto = LocalContext.current 
    
    val uiState by viewModel.uiState.collectAsState()
    var inputCorreo by remember { mutableStateOf(uiState.correo) }
    var inputContrasenhaActual by remember { mutableStateOf("") }
    var inputContrasenhaNueva by remember { mutableStateOf("") }
    var inputRepeticion by remember { mutableStateOf("") }

    // Usamos Box porque la barra superior ya la provee el AppNavigation global
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF191636)) // Fondo global retro
    ) {
        // El componente principal que tiene el diseño de la cuenta
        PanelCuenta(
            correo = inputCorreo,
            onCorreoChange = { inputCorreo = it },
            contrasenhaActual = inputContrasenhaActual,
            onContrasenhaActualChange = { inputContrasenhaActual = it },
            contrasenhaNueva = inputContrasenhaNueva,
            onContrasenhaNuevaChange = { inputContrasenhaNueva = it },
            repeticionContrasenha = inputRepeticion,
            onRepeticionContrasenhaChange = { inputRepeticion = it },
            mensajeErrorCorreo = uiState.errorMessage,
            mensajeErrorContrasenha = null,
            onGuardarCorreoClick = {
                viewModel.cambiarCorreo(
                    nuevoCorreo = inputCorreo,
                    onSuccess = {
                        Toast.makeText(contexto, "Correo actualizado con éxito", Toast.LENGTH_SHORT).show()
                    }
                )
            },
            onCambiarContrasenhaClick = {
                viewModel.cambiarContrasenha(
                    actual = inputContrasenhaActual,
                    nueva = inputContrasenhaNueva,
                    repeticion = inputRepeticion,
                    onSuccess = {
                        Toast.makeText(contexto, "Contraseña cambiada", Toast.LENGTH_SHORT).show()
                        // Reseteamos campos
                        inputContrasenhaActual = ""
                        inputContrasenhaNueva = ""
                        inputRepeticion = ""
                    }
                )
            },
            onOlvidoContrasenhaClick = onRecuperarContrasenhaClick
        )
    }
}

@Preview
@Composable
fun PaginaCuentaPreview() {
    DealFinder_PCTheme {
        PaginaCuenta(
            onRecuperarContrasenhaClick = {}
        )
    }
}

