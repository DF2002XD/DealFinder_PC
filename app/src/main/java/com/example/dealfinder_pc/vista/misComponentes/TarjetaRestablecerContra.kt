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

@Composable
fun TarjetaRestablecerContra(
    correo: String,
    codigo: String,
    onCodigoChange: (String) -> Unit,
    nuevaContrasena: String,
    onNuevaContrasenaChange: (String) -> Unit,
    repetirContrasena: String,
    onRepetirContrasenaChange: (String) -> Unit,
    errorMessage: String? = null,
    onCambiarClicked: () -> Unit,
    onNavigateToLogin: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(100.dp))

        Text(
            text = stringResource(R.string.restablecer_titulo),
            fontSize = 14.sp,
            fontFamily = displayFontFamily,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = stringResource(R.string.restablecer_descripcion, correo),
            fontSize = 16.sp,
            fontFamily = bodyFontFamily,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        CampoTexto(
            valor = codigo,
            onValorCambiado = onCodigoChange,
            placeholder = R.string.campo_codigo,
            esError = false
        )

        Spacer(modifier = Modifier.height(16.dp))

        CampoContrasenha(
            valor = nuevaContrasena,
            onValorCambiado = onNuevaContrasenaChange,
            placeholder = R.string.cuenta_hint_nueva,
            esError = false
        )

        Spacer(modifier = Modifier.height(16.dp))

        CampoContrasenha(
            valor = repetirContrasena,
            onValorCambiado = onRepetirContrasenaChange,
            placeholder = R.string.cuenta_hint_repetir,
            esError = false
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
            nombre = R.string.restablecer_cambiar,
            onClick = onCambiarClicked,
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

@Preview(showBackground = true)
@Composable
fun TarjetaRestablecerContraPreview() {
    DealFinder_PCTheme {
        TarjetaRestablecerContra(
            correo = "usuario@gmail.com",
            codigo = "",
            onCodigoChange = {},
            nuevaContrasena = "",
            onNuevaContrasenaChange = {},
            repetirContrasena = "",
            onRepetirContrasenaChange = {},
            errorMessage = null,
            onCambiarClicked = {},
            onNavigateToLogin = {}
        )
    }
}
