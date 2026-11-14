package com.example.dealfinder_pc.vista.misComponentes

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dealfinder_pc.R
import com.example.dealfinder_pc.ui.theme.DealFinder_PCTheme
import com.example.dealfinder_pc.ui.theme.bodyFontFamily
import com.example.dealfinder_pc.ui.theme.displayFontFamily

/**
 * Pantalla o componente completo para el formulario de inicio de sesión de la aplicación.
 * Combina varios componentes personalizados (como CampoCorreo, CampoContrasenha y Boton) 
 * junto con el logotipo/lema de la aplicación y manejo de errores.
 *
 * @param keepLogged Estado que indica si la casilla de "Mantener sesión iniciada" está marcada.
 * @param onKeepLoggedChange Callback que se dispara al pulsar la casilla o el texto de "Mantener sesión iniciada", devolviendo su nuevo estado.
 * @param errorMessage Mensaje de error a mostrar si el inicio de sesión falla. Si no es nulo, los campos se marcan como error y aparece una opción de recuperación de contraseña.
 * @param onLoginClicked Callback que se ejecuta al pulsar el botón "Iniciar Sesión". Devuelve el correo y la contraseña introducidos.
 * @param onNavigateToRegister Callback para navegar a la pantalla de registro al pulsar el botón "Crear una cuenta".
 * @param onNavigateToResetPassword Callback para navegar a la pantalla de recuperación de contraseña si aparece un error.
 */
@Composable
fun Login(
    correo: String,
    onCorreoChange: (String) -> Unit,
    contrasenha: String,
    onContrasenhaChange: (String) -> Unit,
    keepLogged: Boolean,
    onKeepLoggedChange: (Boolean) -> Unit,
    errorMessage: String? = null,
    onLoginClicked: () -> Unit,
    onNavigateToRegister: () -> Unit,
    onNavigateToResetPassword: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = stringResource(R.string.app_name),
            fontSize = 24.sp,
            fontFamily = displayFontFamily,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Text(
            text = stringResource(R.string.login_lema),
            fontSize = 18.sp,
            fontFamily = bodyFontFamily,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(bottom = 10.dp)
        )

        CampoCorreo(
            valor = correo,
            onValorCambiado = onCorreoChange,
            placeholder = R.string.campo_correo,
            esError = errorMessage != null
        )
        
        Spacer(modifier = Modifier.height(20.dp))

        CampoContrasenha(
            valor = contrasenha,
            onValorCambiado = onContrasenhaChange,
            placeholder = R.string.campo_contrasena,
            esError = errorMessage != null
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Checkbox "Mantener sesión iniciada"
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onKeepLoggedChange(!keepLogged) },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = keepLogged,
                onCheckedChange = null // Manejado por el Row
            )
            Text(
                text = stringResource(R.string.login_mantener_sesion),
                fontFamily = displayFontFamily, // Letra pixelada según tu imagen
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        if (errorMessage != null) {
            Text(
                text = stringResource(R.string.login_recuperar),
                fontFamily = bodyFontFamily,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.secondary, // El cyan de tu imagen
                textDecoration = TextDecoration.Underline,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onNavigateToResetPassword() },
                textAlign = TextAlign.Start
            )

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
            nombre = R.string.login_iniciar_sesion,
            onClick = onLoginClicked,
            isPrimary = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        Boton(
            nombre = R.string.login_crear_cuenta,
            onClick = onNavigateToRegister,
            isPrimary = false,
            modifier = Modifier.fillMaxWidth()
        )
        
        Spacer(modifier = Modifier.height(40.dp))
    }
}

@Preview
@Composable
fun LoginPreview() {
    DealFinder_PCTheme {
        Login(
            correo = "",
            onCorreoChange = {},
            contrasenha = "",
            onContrasenhaChange = {},
            keepLogged = false,
            onKeepLoggedChange = {},
            onLoginClicked = {},
            onNavigateToRegister = {},
            onNavigateToResetPassword = {}
        )
    }

}