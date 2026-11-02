package com.example.dealfinder_pc.vista.misComponentes

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dealfinder_pc.R
import com.example.dealfinder_pc.ui.theme.DealFinder_PCTheme
import com.example.dealfinder_pc.ui.theme.displayFontFamily
import com.example.dealfinder_pc.ui.theme.extraColors


/**
 * Botón personalizado con un estilo "retro" que incluye un efecto de sombra dura (hard shadow) y bordes rectos.
 * Permite alternar entre un estilo principal (isPrimary = true) y uno secundario (isPrimary = false), 
 * aplicando los colores definidos en el tema de la aplicación.
 *
 * @param nombre ID del recurso de string (R.string...) que se mostrará como texto dentro del botón.
 * @param onClick Callback que se ejecuta cuando el usuario pulsa el botón.
 * @param modifier Modificador opcional para personalizar el diseño, tamaño o posición del componente (por defecto tiene un ancho de 300dp y alto de 50dp).
 * @param enabled Indica si el botón está habilitado o deshabilitado para recibir clics. Si es `false`, cambia su opacidad.
 * @param isPrimary Si es `true`, usa el color primario del tema (amarillo retro). Si es `false`, usa el color secundario/contenedor (morado retro).
 */
@Composable
fun Boton(
    nombre: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    isPrimary: Boolean = true
) {
    // isPrimary usa el amarillo retro (primary), de lo contrario el morado retro (primaryContainer)
    val containerColor =
        if (isPrimary) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.primaryContainer
    val contentColor =
        if (isPrimary) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onPrimaryContainer

    // Obtenemos el color de la sombra directamente desde el tema retro
    val shadowColor = MaterialTheme.extraColors.hardShadow

    Button(
        onClick = onClick,
        modifier = modifier
            .width(300.dp)
            .height(50.dp)
            .drawBehind {
                drawRect(
                    color = shadowColor,
                    topLeft = Offset(4.dp.toPx(), 4.dp.toPx()),
                    size = size
                )
            },
        enabled = enabled,
        shape = RectangleShape,
        border = BorderStroke(2.dp, MaterialTheme.colorScheme.outline),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor,
            disabledContainerColor = containerColor.copy(alpha = 0.5f),
            disabledContentColor = contentColor.copy(alpha = 0.5f)
        )
    ) {
        Text(
            text = stringResource(id = nombre),
            fontFamily = displayFontFamily,
            fontSize = 12.sp
        )
    }
}

@Preview
@Composable
fun BotonPreview() {
    DealFinder_PCTheme {
        Boton(nombre = R.string.login_iniciar_sesion, onClick = {}, enabled = true, isPrimary = true)
    }

}

@Preview
@Composable
fun BotonPreview1() {
    DealFinder_PCTheme {
        Boton(nombre = R.string.login_crear_cuenta, onClick = {}, enabled = true, isPrimary = false)
    }

}