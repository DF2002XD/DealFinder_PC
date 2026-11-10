package com.example.dealfinder_pc.vista.misComponentes

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dealfinder_pc.R
import com.example.dealfinder_pc.ui.theme.DealFinder_PCTheme
import com.example.dealfinder_pc.ui.theme.bodyFontFamily

@Composable
fun PanelCuenta(
    correoActual: String,
    modifier: Modifier = Modifier,
    mensajeErrorCorreo: String? = null,
    mensajeErrorContrasenha: String? = null,
    onGuardarCorreoClick: (nuevoCorreo: String) -> Unit,
    onCambiarContrasenhaClick: (actual: String, nueva: String, repeticion: String) -> Unit,
    onOlvidoContrasenhaClick: () -> Unit // NUEVO: Acción para cuando olvida la contraseña
) {
    // Estados internos para lo que escribe el usuario antes de guardarlo
    var correo by remember { mutableStateOf(correoActual) }
    var contrasenhaActual by remember { mutableStateOf("") }
    var contrasenhaNueva by remember { mutableStateOf("") }
    var repeticionContrasenha by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(32.dp))

        TituloPantalla(textoRes = R.string.cuenta_titulo)

        Spacer(modifier = Modifier.height(24.dp))

        // --- SECCIÓN: CAMBIAR CORREO ---
        TextoEncabezado(textoRes = R.string.campo_correo) // O la string que decidas para "Correo electrónico"
        Spacer(modifier = Modifier.height(12.dp))
        
        CampoCorreo(
            valor = correo,
            onValorCambiado = { correo = it },
            esError = mensajeErrorCorreo != null
        )
        
        // Si hay un error al cambiar el correo, lo mostramos (opcional, como en el login)
        if (mensajeErrorCorreo != null) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = mensajeErrorCorreo,
                color = MaterialTheme.colorScheme.error,
                fontFamily = bodyFontFamily,
                fontSize = 14.sp
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Boton(
            nombre = R.string.cuenta_guardar_correo,
            onClick = { onGuardarCorreoClick(correo) },
            isPrimary = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(32.dp))

        // --- SECCIÓN: CAMBIAR CONTRASEÑA ---
        TextoEncabezado(textoRes = R.string.cuenta_cambiar_contrasena)
        Spacer(modifier = Modifier.height(12.dp))

        CampoContrasenha(
            valor = contrasenhaActual,
            onValorCambiado = { contrasenhaActual = it },
            placeholder = R.string.cuenta_hint_actual,
            esError = mensajeErrorContrasenha != null
        )

        Spacer(modifier = Modifier.height(12.dp))

        CampoContrasenha(
            valor = contrasenhaNueva,
            onValorCambiado = { contrasenhaNueva = it },
            placeholder = R.string.cuenta_hint_nueva,
            esError = mensajeErrorContrasenha != null
        )

        Spacer(modifier = Modifier.height(12.dp))

        CampoContrasenha(
            valor = repeticionContrasenha,
            onValorCambiado = { repeticionContrasenha = it },
            placeholder = R.string.cuenta_hint_repetir,
            esError = mensajeErrorContrasenha != null
        )
        
        if (mensajeErrorContrasenha != null) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = mensajeErrorContrasenha,
                color = MaterialTheme.colorScheme.error,
                fontFamily = bodyFontFamily,
                fontSize = 14.sp
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Boton(
            nombre = R.string.cuenta_boton_cambiar_contrasena,
            onClick = { onCambiarContrasenhaClick(contrasenhaActual, contrasenhaNueva, repeticionContrasenha) },
            isPrimary = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // NUEVO: Texto de olvidó contraseña
        Text(
            text = "¿Olvidaste la contraseña actual?",
            fontFamily = bodyFontFamily,
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.secondary, // Cyan brillante
            textDecoration = TextDecoration.Underline,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onOlvidoContrasenhaClick() },
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(40.dp))
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF191636)
@Composable
fun PanelCuentaPreview() {
    DealFinder_PCTheme {
        PanelCuenta(
            correoActual = "jugador@correo.com",
            onGuardarCorreoClick = {},
            onCambiarContrasenhaClick = { _, _, _ -> },
            onOlvidoContrasenhaClick = {}
        )
    }
}