package com.example.dealfinder_pc.vista.misComponentes

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dealfinder_pc.R
import com.example.dealfinder_pc.ui.theme.DealFinder_PCTheme
import com.example.dealfinder_pc.ui.theme.bodyFontFamily

@Composable
fun CampoContrasenha(
    valor: String,
    onValorCambiado: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: Int? = null,
    esError: Boolean = false
) {
    // Estado para controlar la visibilidad, interno a este componente
    var esVisible by remember { mutableStateOf(false) }

    TextField(
        value = valor,
        onValueChange = onValorCambiado,
        modifier = modifier
            .fillMaxWidth()
            .border(2.dp, MaterialTheme.colorScheme.secondary),
        isError = esError,
        textStyle = androidx.compose.ui.text.TextStyle(
            fontFamily = bodyFontFamily,
            color = MaterialTheme.colorScheme.onSurface
        ),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = MaterialTheme.colorScheme.surfaceContainerLowest,
            focusedContainerColor = MaterialTheme.colorScheme.surfaceContainerLowest,
            unfocusedIndicatorColor = androidx.compose.ui.graphics.Color.Transparent,
            focusedIndicatorColor = androidx.compose.ui.graphics.Color.Transparent,
            unfocusedPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
            focusedPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant
        ),
        placeholder = {
            if (placeholder != null) {
                Text(
                    text = stringResource(id = placeholder),
                    fontFamily = bodyFontFamily
                )
            }
        },
        visualTransformation = if (esVisible) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {
            val icono =
                if (esVisible) painterResource(id = R.drawable.outline_visibility_24) else painterResource(
                    id = R.drawable.outline_visibility_off_24
                )
            val descripcion = if (esVisible) "Ocultar contraseña" else "Mostrar contraseña"
            IconButton(onClick = { esVisible = !esVisible }) {
                Icon(painter = icono, contentDescription = descripcion)
            }
        })
}

@Composable
fun CampoContrasenhaSiempreVisible(
    valor: String,
    onValorCambiado: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: Int? = null,
    esError: Boolean = false
) {
    TextField(
        value = valor,
        onValueChange = onValorCambiado,
        modifier = modifier
            .fillMaxWidth()
            .border(2.dp, MaterialTheme.colorScheme.secondary),
        isError = esError,
        textStyle = androidx.compose.ui.text.TextStyle(
            fontFamily = bodyFontFamily,
            color = MaterialTheme.colorScheme.onSurface
        ),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = MaterialTheme.colorScheme.surfaceContainerLowest,
            focusedContainerColor = MaterialTheme.colorScheme.surfaceContainerLowest,
            unfocusedIndicatorColor = androidx.compose.ui.graphics.Color.Transparent,
            focusedIndicatorColor = androidx.compose.ui.graphics.Color.Transparent,
            unfocusedPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
            focusedPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant
        ),
        placeholder = {
            if (placeholder != null) {
                Text(
                    text = stringResource(id = placeholder),
                    fontFamily = bodyFontFamily
                )
            }
        },
        visualTransformation = VisualTransformation.None,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
    )
}

@Composable
fun CampoTexto(

) {

}

@Composable
fun CampoCorreo(

) {

}

@Composable
fun CampoImporte(

) {

}

@Preview
@Composable
fun CampoContrasenhaPreview() {
    DealFinder_PCTheme {
        CampoContrasenha(
            valor = "",
            placeholder = R.string.registro_hint_repetir_contrasena,
            onValorCambiado = {})
    }

}

@Preview
@Composable
fun CampoContrasenhaSiempreVisiblePreview() {
    DealFinder_PCTheme {
        CampoContrasenhaSiempreVisible(
            valor = "",
            placeholder = R.string.campo_contrasena,
            onValorCambiado = {})
    }
}