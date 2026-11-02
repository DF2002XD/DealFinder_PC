package com.example.dealfinder_pc.vista.misComponentes

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dealfinder_pc.R
import com.example.dealfinder_pc.ui.theme.DealFinder_PCTheme
import com.example.dealfinder_pc.ui.theme.bodyFontFamily
import com.example.dealfinder_pc.ui.theme.displayFontFamily

/**
 * Pantalla para el proceso de recuperación de contraseña.
 * Permite al usuario introducir su correo electrónico para solicitar un restablecimiento de su cuenta,
 * e incluye opciones para volver a la pantalla de inicio de sesión.
 *
 * @param errorMessage Mensaje de error a mostrar (por ejemplo, si el correo no tiene un formato válido o no existe en la base de datos). Si no es nulo, se muestra en texto rojo bajo el campo de correo.
 * @param onEnviarClicked Callback que se ejecuta al pulsar el botón "Enviar". Devuelve el correo electrónico introducido por el usuario.
 * @param onNavigateToLogin Callback para navegar de vuelta a la pantalla de inicio de sesión cuando el usuario pulsa "Volver a Iniciar Sesión".
 */
@Composable
fun RecuperarContrasenha(
    errorMessage: String? = null,
    onEnviarClicked: (correo: String) -> Unit,
    onNavigateToLogin: () -> Unit
) {
    var correo by remember { mutableStateOf(value = "") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(100.dp))

        Text(
            text = stringResource(R.string.recuperar_titulo),
            fontSize = 14.sp,
            fontFamily = displayFontFamily,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = stringResource(R.string.recuperar_descripcion),
            fontSize = 16.sp,
            fontFamily = bodyFontFamily,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        CampoCorreo(
            valor = correo,
            onValorCambiado = { correo = it },
            placeholder = R.string.campo_correo,
            esError = errorMessage != null
        )

        if (errorMessage != null) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error,
                fontFamily = bodyFontFamily,
                fontSize = 14.sp
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Boton(
            nombre = R.string.recuperar_enviar,
            onClick = { onEnviarClicked(correo) },
            isPrimary = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        Boton(
            nombre = R.string.recuperar_volver_login,
            onClick = onNavigateToLogin,
            isPrimary = false,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(40.dp))
    }
}

@Preview
@Composable
fun RecuperarContrasenhaPreview() {
    DealFinder_PCTheme {
        RecuperarContrasenha(
            errorMessage = null,
            onEnviarClicked = {},
            onNavigateToLogin = {}
        )
    }
}