package com.example.dealfinder_pc.vista.misComponentes

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dealfinder_pc.ui.theme.DealFinder_PCTheme
import com.example.dealfinder_pc.ui.theme.bodyFontFamily
import com.example.dealfinder_pc.ui.theme.displayFontFamily


/**
 * Título principal de la pantalla, estilizado con letra pixelada y el color primario del tema.
 *
 * @param textoRes ID del recurso de string (R.string...) que se mostrará como título.
 * @param modifier Modificador opcional para ajustar diseño o posición.
 */
@Composable
fun TituloPantalla(
    textoRes: Int,
    modifier: Modifier = Modifier
) {
    Text(
        text = stringResource(id = textoRes),
        fontFamily = displayFontFamily,
        color = MaterialTheme.colorScheme.primary,
        fontSize = 24.sp,
        modifier = modifier
    )
}

@Composable
fun TextoEncabezado(
    textoRes: Int,
    modifier: Modifier = Modifier
){
    Text(
        text = stringResource(id = textoRes),
        fontFamily = displayFontFamily,
        color = MaterialTheme.colorScheme.onSurface,
        fontSize = 14.sp,
        modifier = modifier
    )
}

/**
 * Texto utilizado para mostrar resúmenes numéricos o importes totales (como el total en la lista de deseos).
 * Usa la fuente estándar del cuerpo (bodyFontFamily).
 *
 * @param texto Cadena de texto directa a mostrar (ej. "3 juegos · aprox. $52.44").
 * @param modifier Modificador opcional para ajustar diseño o posición.
 */
@Composable
fun TextoResumen(
    texto: String, // String dinámico para admitir plurals y formato
    modifier: Modifier = Modifier
) {
    Text(
        text = texto,
        fontFamily = bodyFontFamily,
        color = MaterialTheme.colorScheme.onSurface,
        fontSize = 24.sp,
        modifier = modifier
    )
}


/**
 * Texto de estilo secundario o aclaratorio, mostrado en un tamaño más pequeño y color menos destacado.
 * Útil para notas al pie o advertencias (ej. "Al marcar comprado...").
 *
 * @param textoRes ID del recurso de string (R.string...) que se mostrará como nota.
 * @param modifier Modificador opcional para ajustar diseño o posición.
 */
@Composable
fun TextoNota(
    textoRes: Int,
    modifier: Modifier = Modifier
) {
    Text(
        text = stringResource(id = textoRes),
        fontFamily = bodyFontFamily,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        modifier = modifier
    )
}

/**
 * Etiqueta para poner encima de los campos de formulario o para textos pequeños.
 * Usa la letra pixelada pequeña en gris.
 */
@Composable
fun TextoEtiqueta(
    texto: String, // String dinámico para poder admitir formatos como el de la moneda
    modifier: Modifier = Modifier
) {
    Text(
        text = texto,
        fontFamily = displayFontFamily,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        fontSize = 12.sp,
        modifier = modifier
    )
}

@Composable
fun TextoSombra(textoRes: Int) {
    Box {
        Text(
            text = stringResource(id = textoRes),
            fontFamily = bodyFontFamily,
            color = Color.White.copy(alpha = 0.5f), // Sombra fantasma clara
            fontSize = 18.sp,
            modifier = Modifier.offset(1.dp, 1.dp)
        )
        Text(
            text = stringResource(id = textoRes),
            fontFamily = bodyFontFamily,
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 18.sp
        )
    }}

@Preview
@Composable
fun  TituloPantallaPreview(){
    DealFinder_PCTheme {
        TituloPantalla(
            textoRes = com.example.dealfinder_pc.R.string.filtros_titulo
        )
    }
}

@Preview
@Composable
fun TextoEncabezadoPreview(){
    DealFinder_PCTheme {
        TextoEncabezado(
            textoRes = com.example.dealfinder_pc.R.string.filtros_titulo
        )
    }
}

@Preview
@Composable
fun TextoResumenPreview(){
    DealFinder_PCTheme {
        TextoResumen(texto = "3 juegos · aprox. $52.44")
    }
}

@Preview
@Composable
fun TextoNotaPReview(){
    DealFinder_PCTheme {
        TextoNota(textoRes = com.example.dealfinder_pc.R.plurals.lista_resumen)
    }
}

@Preview
@Composable
fun TextoEtiquetaPreview(){
    DealFinder_PCTheme {
        TextoEtiqueta(texto = "Precio máximo (USD)")
    }
}

@Preview
@Composable
fun TextoSombraPreview(){
    DealFinder_PCTheme {
        TextoSombra(textoRes = com.example.dealfinder_pc.R.plurals.lista_resumen)
    }
}
