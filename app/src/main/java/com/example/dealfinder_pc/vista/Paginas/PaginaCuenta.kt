package com.example.dealfinder_pc.vista.Paginas

import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.dealfinder_pc.ui.theme.DealFinder_PCTheme
import com.example.dealfinder_pc.vista.misComponentes.PanelCuenta

@Composable
fun PaginaCuenta(
    onRecuperarContrasenhaClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val contexto = LocalContext.current 
    
    // Simulación del estado del ViewModel
    var correoActual by remember { mutableStateOf("jugador@correo.com") }
    var mensajeErrorCorreo by remember { mutableStateOf<String?>(null) }

    // Usamos Box porque la barra superior ya la provee el AppNavigation global
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF191636)) // Fondo global retro
    ) {
        // El componente principal que tiene el diseño de la cuenta
        PanelCuenta(
            correoActual = correoActual, 
            mensajeErrorCorreo = mensajeErrorCorreo,
            mensajeErrorContrasenha = null,
            onGuardarCorreoClick = { nuevoCorreo ->
                if (nuevoCorreo.isBlank()) {
                    mensajeErrorCorreo = "El correo no puede estar vacío"
                } else if (nuevoCorreo == correoActual) {
                    mensajeErrorCorreo = "Tiene que ser otro correo distinto"
                } else {
                    mensajeErrorCorreo = null
                    correoActual = nuevoCorreo
                    Toast.makeText(contexto, "Correo actualizado con éxito", Toast.LENGTH_SHORT).show()
                }
            },
            onCambiarContrasenhaClick = { _, _, _ ->
                Toast.makeText(contexto, "Contraseña cambiada", Toast.LENGTH_SHORT).show()
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

