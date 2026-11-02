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

@Composable
fun Registro(
    errorMessage: String? = null,
    onRegistroClicked: (usuario: String, correo: String, contrasenha: String, repeticion: String) -> Unit,
    onNavigateToLogin: () -> Unit
) {
    var usuario by remember { mutableStateOf(value = "") }
    var correo by remember { mutableStateOf(value = "") }
    var contrasenha by remember { mutableStateOf(value = "") }
    var repeticion by remember { mutableStateOf(value = "") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(100.dp))

        Text(
            text = stringResource(R.string.registro_titulo),
            fontSize = 24.sp,
            fontFamily = displayFontFamily,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = stringResource(R.string.registro_lema),
            fontSize = 18.sp,
            fontFamily = bodyFontFamily,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        CampoTexto(
            valor = usuario,
            onValorCambiado = { usuario = it },
            placeholder = R.string.registro_hint_usuario,
            esError = errorMessage != null
        )

        Spacer(modifier = Modifier.height(16.dp))

        CampoCorreo(
            valor = correo,
            onValorCambiado = { correo = it },
            placeholder = R.string.campo_correo,
            esError = errorMessage != null
        )

        Spacer(modifier = Modifier.height(16.dp))

        CampoContrasenhaSiempreVisible(
            valor = contrasenha,
            onValorCambiado = { contrasenha = it },
            placeholder = R.string.campo_contrasena,
            esError = errorMessage != null
        )

        Spacer(modifier = Modifier.height(16.dp))

        CampoContrasenha(
            valor = repeticion,
            onValorCambiado = { repeticion = it },
            placeholder = R.string.registro_hint_repetir_contrasena,
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
            nombre = R.string.registro_registrarme,
            onClick = { onRegistroClicked(usuario, correo, contrasenha, repeticion) },
            isPrimary = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        Boton(
            nombre = R.string.registro_ya_tengo_cuenta,
            onClick = onNavigateToLogin,
            isPrimary = false,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(40.dp))
    }
}

@Preview
@Composable
fun RegistroPreview() {
    DealFinder_PCTheme {
        Registro(
            errorMessage = null,
            onRegistroClicked = { _, _, _, _ -> },
            onNavigateToLogin = {}
        )
    }
}