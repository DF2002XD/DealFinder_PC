package com.example.dealfinder_pc.vista.paginas

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dealfinder_pc.ui.theme.DealFinder_PCTheme
import com.example.dealfinder_pc.viewmodel.vm.RestablecerContraViewModel
import com.example.dealfinder_pc.vista.misComponentes.TarjetaRestablecerContra

@Composable
fun PaginaRestablecerContra(
    correoAsociado: String,
    onNavigateToLogin: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: RestablecerContraViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val contexto = LocalContext.current

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF191636))
    ) {
        TarjetaRestablecerContra(
            correo = correoAsociado,
            codigo = uiState.codigo,
            onCodigoChange = { viewModel.actualizarCodigo(it) },
            nuevaContrasena = uiState.nuevaContrasena,
            onNuevaContrasenaChange = { viewModel.actualizarNuevaContrasena(it) },
            repetirContrasena = uiState.repetirContrasena,
            onRepetirContrasenaChange = { viewModel.actualizarRepetirContrasena(it) },
            errorMessage = uiState.errorMessage,
            onCambiarClicked = {
                viewModel.restablecerContrasena(
                    correoAsociado = correoAsociado,
                    onSuccess = {
                        Toast.makeText(contexto, "Contraseña cambiada exitosamente", Toast.LENGTH_SHORT).show()
                        onNavigateToLogin()
                    }
                )
            },
            onNavigateToLogin = onNavigateToLogin
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PaginaRestablecerContraPreview() {
    DealFinder_PCTheme {
        PaginaRestablecerContra(
            correoAsociado = "usuario@gmail.com",
            onNavigateToLogin = {}
        )
    }
}
