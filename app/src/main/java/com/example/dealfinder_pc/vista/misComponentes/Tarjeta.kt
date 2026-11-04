package com.example.dealfinder_pc.vista.misComponentes

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dealfinder_pc.ui.theme.DealFinder_PCTheme
import com.example.dealfinder_pc.ui.theme.bodyFontFamily
import com.example.dealfinder_pc.ui.theme.displayFontFamily
import com.example.dealfinder_pc.ui.theme.extraColors

/**
 * Tarjeta de oferta dispuesta de forma horizontal (ideal para listas), diseñada con un estilo retro (bordes rectos).
 * Muestra información clave del producto como el título, tienda, precios y porcentaje de descuento.
 *
 * @param titulo Nombre del producto o videojuego. Se muestra truncado a 1 línea.
 * @param tienda Nombre de la plataforma o tienda (p.ej. "Steam", "Epic Games"). Se muestra truncado a 1 línea.
 * @param precioOriginal Precio sin rebaja, mostrado tachado.
 * @param precioRebajado Precio actual de oferta, mostrado en el color de "éxito" (generalmente verde).
 * @param descuento Porcentaje de descuento aplicado, destacado en una caja verde en la esquina inferior derecha.
 * @param inicialesImagen Texto o iniciales que se muestran en el cuadrado de la izquierda (placeholder de la imagen).
 * @param onClick Callback que se ejecuta cuando el usuario pulsa en cualquier lugar de la tarjeta.
 * @param modifier Modificador opcional para personalizar el diseño del contenedor (como padding exterior o tamaño).
 */
@Composable
fun TarjetaHorizontal(
    titulo: String,
    tienda: String,
    precioOriginal: String,
    precioRebajado: String,
    descuento: String,
    inicialesImagen: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val retroGreen = MaterialTheme.extraColors.success
    val retroOnGreen = MaterialTheme.extraColors.onSuccess
    val borderColor = MaterialTheme.colorScheme.onSurface
    val cardBackground = MaterialTheme.colorScheme.surfaceContainerLowest

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .border(2.dp, borderColor, RectangleShape)
            .background(cardBackground)
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(72.dp)
                .border(2.dp, borderColor, RectangleShape)
                .background(MaterialTheme.colorScheme.secondary),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = inicialesImagen,
                fontFamily = displayFontFamily,
                color = MaterialTheme.colorScheme.onSecondary,
                fontSize = 14.sp
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = titulo,
                fontFamily = bodyFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(2.dp))

            Text(
                text = tienda,
                fontFamily = bodyFontFamily,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(verticalAlignment = Alignment.Bottom) {
                Text(
                    text = precioOriginal,
                    fontFamily = bodyFontFamily,
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textDecoration = TextDecoration.LineThrough
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = precioRebajado,
                    fontFamily = bodyFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = retroGreen
                )
            }
        }

        Column(
            modifier = Modifier.height(72.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.End
        ) {
            Box(
                modifier = Modifier
                    .background(retroGreen)
                    .padding(horizontal = 4.dp, vertical = 2.dp)
            ) {
                Text(
                    text = descuento,
                    fontFamily = bodyFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = retroOnGreen
                )
            }
        }
    }
}

@Preview
@Composable
fun TarjetaHorizontalPreview() {
    DealFinder_PCTheme {
        TarjetaHorizontal(
            titulo = "Silver Pines",
            tienda = "Steam",
            precioOriginal = "$35.90",
            precioRebajado = "$22.26",
            descuento = "-38%",
            inicialesImagen = "SP",
            onClick = {}
        )
    }
}