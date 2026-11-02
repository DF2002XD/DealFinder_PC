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
import androidx.compose.ui.graphics.RectangleShape
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


/**
 * Campo de texto personalizado para introducir contraseñas.
 * Incluye un icono al final (trailing icon) que permite alternar la visibilidad
 * del texto entre oculto (asteriscos) y visible.
 *
 * @param valor El texto actual que se muestra en el campo.
 * @param onValorCambiado Callback que se ejecuta cuando el usuario escribe o borra texto.
 * @param modifier Modificador opcional para personalizar el diseño del componente.
 * @param placeholder ID del recurso de string para mostrar como sugerencia cuando el campo esté vacío.
 * @param esError Indica si el campo debe mostrarse en estado de error (por ejemplo, con bordes rojos).
 */
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
            .border(2.dp, MaterialTheme.colorScheme.secondary, RectangleShape),
        isError = esError,
        shape = RectangleShape,
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
            val descripcionRes = if (esVisible) R.string.campo_ocultar_contrasena else R.string.campo_mostrar_contrasena
            IconButton(onClick = { esVisible = !esVisible }) {
                Icon(painter = icono, contentDescription = stringResource(id = descripcionRes))
            }
        })
}

/**
 * Campo de texto para contraseñas que siempre muestra su contenido de forma visible.
 * Mantiene la configuración del teclado adaptada para contraseñas.
 *
 * @param valor El texto actual que se muestra en el campo.
 * @param onValorCambiado Callback que se ejecuta cuando el usuario escribe o borra texto.
 * @param modifier Modificador opcional para personalizar el diseño del componente.
 * @param placeholder ID del recurso de string para mostrar como sugerencia cuando el campo esté vacío.
 * @param esError Indica si el campo debe mostrarse en estado de error.
 */
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
            .border(2.dp, MaterialTheme.colorScheme.secondary, RectangleShape),
        isError = esError,
        shape = RectangleShape,
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

/**
 * Campo de texto genérico y estandarizado para la aplicación.
 *
 * @param valor El texto actual que se muestra en el campo.
 * @param onValorCambiado Callback que se ejecuta cuando el usuario escribe o borra texto.
 * @param modifier Modificador opcional para personalizar el diseño del componente.
 * @param placeholder ID del recurso de string para mostrar como sugerencia cuando el campo esté vacío.
 * @param esError Indica si el campo debe mostrarse en estado de error.
 */
@Composable
fun CampoTexto(
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
            .border(2.dp, MaterialTheme.colorScheme.secondary, RectangleShape),
        isError = esError,
        shape = RectangleShape,
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
        }
    )
}

/**
 * Campo de texto optimizado para la introducción de correos electrónicos.
 * Muestra automáticamente un teclado con facilidades para escribir direcciones (como el símbolo '@').
 *
 * @param valor El texto actual que se muestra en el campo.
 * @param onValorCambiado Callback que se ejecuta cuando el usuario escribe o borra texto.
 * @param modifier Modificador opcional para personalizar el diseño del componente.
 * @param placeholder ID del recurso de string para mostrar como sugerencia cuando el campo esté vacío.
 * @param esError Indica si el campo debe mostrarse en estado de error.
 */
@Composable
fun CampoCorreo(
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
            .border(2.dp, MaterialTheme.colorScheme.secondary, RectangleShape),
        isError = esError,
        shape = RectangleShape,
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
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
    )
}

/**
 * Campo de texto diseñado para introducir valores numéricos decimales (como dinero o cantidades).
 * Despliega un teclado numérico con soporte para punto/coma decimal.
 *
 * @param valor El texto actual que se muestra en el campo.
 * @param onValorCambiado Callback que se ejecuta cuando el usuario escribe o borra texto.
 * @param modifier Modificador opcional para personalizar el diseño del componente.
 * @param placeholder ID del recurso de string para mostrar como sugerencia cuando el campo esté vacío.
 * @param esError Indica si el campo debe mostrarse en estado de error.
 */
@Composable
fun CampoImporte(
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
            .border(2.dp, MaterialTheme.colorScheme.secondary, RectangleShape),
        isError = esError,
        shape = RectangleShape,
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
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
    )
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

@Preview
@Composable
fun CampoTextoPreview() {
    DealFinder_PCTheme {
        CampoTexto(
            valor = "",
            placeholder = R.string.registro_hint_usuario,
            onValorCambiado = {})
    }
}

@Preview
@Composable
fun CampoCorreoPreview() {
    DealFinder_PCTheme {
        CampoCorreo(
            valor = "",
            placeholder = R.string.campo_correo,
            onValorCambiado = {})
    }
}

@Preview
@Composable
fun CampoImportePreview() {
    DealFinder_PCTheme {
        CampoImporte(
            valor = "",
            placeholder = R.string.editar_importe_actual,
            onValorCambiado = {})
    }
}