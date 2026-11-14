package com.example.dealfinder_pc.vista.misComponentes

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.dealfinder_pc.ui.theme.DealFinder_PCTheme
import com.example.dealfinder_pc.ui.theme.bodyFontFamily
import com.example.dealfinder_pc.ui.theme.displayFontFamily
import com.example.dealfinder_pc.ui.theme.extraColors
import com.example.dealfinder_pc.viewmodel.uistate.OfertaTiendaUIState

/**
 * Tarjeta de oferta dispuesta de forma horizontal (ideal para listas), diseñada con un estilo retro (bordes rectos).
 * Muestra información clave del producto como el título, tienda, precios y porcentaje de descuento.
 *
 * @param titulo Nombre del producto o videojuego. Se muestra truncado a 1 línea.
 * @param tienda Nombre de la plataforma o tienda (p.ej. "Steam", "Epic Games"). Se muestra truncado a 1 línea.
 * @param precioOriginal Precio sin rebaja, mostrado tachado.
 * @param precioRebajado Precio actual de oferta, mostrado en el color de "éxito" (generalmente verde).
 * @param descuento Porcentaje de descuento aplicado, destacado en una caja verde en la esquina inferior derecha.
 * @param urlImagen URL o ruta de la portada del juego.
 * @param onClick Callback que se ejecuta cuando el usuario pulsa en cualquier lugar de la tarjeta.
 * @param modifier Modificador opcional para personalizar el diseño del contenedor (como padding exterior o tamaño).
 */
@Composable
fun TarjetaOferta(
    titulo: String,
    tienda: String,
    precioOriginal: String,
    precioRebajado: String,
    descuento: String,
    urlImagen: String,
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
                .border(2.dp, borderColor, RectangleShape),
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                model = urlImagen,
                contentDescription = "Portada de $titulo",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
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

@Composable
fun TarjetaDeseo(
    titulo: String,
    precio: String,
    urlImagen: String,
    textoBoton: String,
    onClick: () -> Unit,
    onBotonClick: () -> Unit,
    onEliminarClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val retroGreen = MaterialTheme.extraColors.success
    val borderColor = MaterialTheme.colorScheme.onSurface
    val cardBackground = MaterialTheme.colorScheme.surfaceContainerLowest
    val buttonBackground = MaterialTheme.colorScheme.primary
    val buttonTextColor = MaterialTheme.colorScheme.onPrimary

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .border(2.dp, borderColor, RectangleShape)
            .background(cardBackground)
            .padding(8.dp), // Reducido el padding para coincidir con la imagen
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Imagen
        Box(
            modifier = Modifier
                .width(64.dp)
                .height(85.dp)
                .border(2.dp, borderColor, RectangleShape),
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                model = urlImagen,
                contentDescription = "Portada de $titulo",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        // Detalles (Título, Precio, Botón)
        Column(
            modifier = Modifier
                .weight(1f)
                .height(85.dp), // Misma altura que la imagen para alinear superior e inferiormente
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = titulo,
                    fontFamily = displayFontFamily,
                    fontSize = 16.sp, // Título principal
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = precio,
                    fontFamily = displayFontFamily,
                    fontSize = 16.sp, // Precio
                    color = retroGreen
                )
            }

            // Botón principal (sin sombra)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(2.dp, borderColor, RectangleShape)
                    .background(buttonBackground)
                    .clickable { onBotonClick() }
                    .padding(vertical = 4.dp), // Padding ajustado
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = textoBoton.uppercase(),
                    fontFamily = displayFontFamily,
                    color = buttonTextColor,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp
                )
            }
        }

        // Papelera en la derecha
        IconButton(
            onClick = onEliminarClick,
            modifier = Modifier
                .padding(start = 8.dp)
                .size(40.dp)
        ) {
            Icon(
                imageVector = Icons.Outlined.Delete,
                contentDescription = "Eliminar de deseos",
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
fun TarjetaPerfil(
    nombre: String,
    correo: String
) {
    val borderColor = MaterialTheme.colorScheme.onSurface
    val cardBackground = MaterialTheme.colorScheme.surfaceContainerLowest

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .border(2.dp, borderColor, RectangleShape)
            .background(cardBackground)
            .padding(vertical = 12.dp, horizontal = 16.dp), // Menos padding vertical
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(56.dp) // Avatar un poco más pequeño
                .border(2.dp, borderColor, RectangleShape)
                .padding(3.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFFF4D94))
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        Column(modifier = Modifier.weight(1f)) {
            // Sombra en el texto del nombre
            Box {
                Text(
                    text = nombre.uppercase(),
                    fontFamily = displayFontFamily,
                    color = Color.White, // La sombra parece ser blanca/clara en esta captura
                    fontSize = 12.sp, // Letra más pequeña
                    maxLines = 1,
                    modifier = Modifier.offset(1.dp, 1.dp) // Sombra más sutil
                )
                Text(
                    text = nombre.uppercase(),
                    fontFamily = displayFontFamily,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 12.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = correo,
                fontFamily = bodyFontFamily,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 16.sp, // El correo es VT323, necesita ser algo más grande para leerse bien
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview
@Composable
fun TarjetaDeseoPreview() {
    DealFinder_PCTheme {
        TarjetaDeseo(
            titulo = "Silver Pines",
            precio = "$22.26",
            urlImagen = "https://images.igdb.com/igdb/image/upload/t_cover_big/co1q3k.jpg",
            textoBoton = "Comprado",
            onClick = {},
            onBotonClick = {},
            onEliminarClick = {}
        )
    }
}

@Preview
@Composable
fun TarjetaOfertaPreview() {
    DealFinder_PCTheme {
        TarjetaOferta(
            titulo = "Silver Pines",
            tienda = "Steam",
            precioOriginal = "$35.90",
            precioRebajado = "$22.26",
            descuento = "-38%",
            urlImagen = "https://images.igdb.com/igdb/image/upload/t_cover_big/co1q3k.jpg",
            onClick = {}
        )
    }
}

@Preview
@Composable
fun TarjetaPerfilPreview() {
    DealFinder_PCTheme {
        TarjetaPerfil(
            nombre = "JUGADOR_01",
            correo = "jugador@correo.com"
        )
    }
}


/**
 * Componente para dibujar la barra de cada tienda.
 * Maneja automáticamente los bordes verdes y etiquetas si es la "Mejor Oferta".
 */
@Composable
fun FilaTiendaDetalle(
    oferta: OfertaTiendaUIState,
    onClick: () -> Unit
) {
    val retroGreen = MaterialTheme.extraColors.success
    val colorBorde = if (oferta.esLaMejor) retroGreen else MaterialTheme.colorScheme.onSurface
    val colorPrecio = if (oferta.esLaMejor) retroGreen else MaterialTheme.colorScheme.onSurface

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .border(2.dp, colorBorde, RectangleShape)
            .background(MaterialTheme.colorScheme.surfaceContainerLowest)
            .padding(12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            if (oferta.urlLogo != null) {
                AsyncImage(
                    model = oferta.urlLogo,
                    contentDescription = "Logo de ${oferta.nombreTienda}",
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
            Text(
                text = oferta.nombreTienda,
                fontFamily = bodyFontFamily,
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 18.sp
            )
            
            if (oferta.descuentoTexto != null) {
                Spacer(modifier = Modifier.width(8.dp))
                // Etiqueta de descuento verde
                Box(
                    modifier = Modifier
                        .background(retroGreen)
                        .padding(horizontal = 4.dp, vertical = 2.dp)
                ) {
                    val textoEtiqueta = if (oferta.esLaMejor) "${oferta.descuentoTexto} MEJOR" else oferta.descuentoTexto
                    Text(
                        text = textoEtiqueta,
                        fontFamily = bodyFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.background // Letras oscuras
                    )
                }
            }
        }

        Text(
            text = oferta.precio,
            fontFamily = displayFontFamily,
            color = colorPrecio,
            fontSize = 14.sp
        )
    }
}
