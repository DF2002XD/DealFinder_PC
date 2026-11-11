package com.example.dealfinder_pc.vista.misComponentes

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dealfinder_pc.R
import com.example.dealfinder_pc.ui.theme.DealFinder_PCTheme
import com.example.dealfinder_pc.ui.theme.bodyFontFamily


@Composable
fun TopAppBarNav(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        BotonVolverLimpiar(
            texto = stringResource(id = R.string.accion_volver),
            onClick = onBackClick,
            isPrimary = false,
            mostrarIconoVolver = true
        )
    }
}


@Composable
fun BottomAppBarNav(
    pestanhaSeleccionada: Int,
    onPestanhaClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val opcionesMenu = listOf(
        R.string.nav_ofertas,
        R.string.nav_lista,
        R.string.nav_presupuesto,
        R.string.nav_perfil
    )

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        // La línea blanca separadora de 2dp
        HorizontalDivider(
            color = MaterialTheme.colorScheme.onSurface,
            thickness = 2.dp,
            modifier = Modifier.fillMaxWidth()
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .background(MaterialTheme.colorScheme.background)
        ) {
            opcionesMenu.forEachIndexed { index, textoRes ->
                val seleccionado = pestanhaSeleccionada == index

                // Fondo y colores dependiendo de si está activa o no
                val backgroundColor =
                    if (seleccionado) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.background
                val textColor =
                    if (seleccionado) Color.Black else MaterialTheme.colorScheme.onSurface

                Box(
                    modifier = Modifier
                        .weight(1f) // Cada pestaña ocupa exactamente el mismo espacio (25%)
                        .fillMaxWidth()
                        .height(56.dp)
                        .background(backgroundColor)
                        .clickable { onPestanhaClick(index) },
                    contentAlignment = Alignment.Center
                ) {
                    if (seleccionado) {
                        // Texto simple negro si está seleccionada
                        Text(
                            text = stringResource(id = textoRes).uppercase(),
                            fontFamily = bodyFontFamily,
                            color = textColor,
                            fontSize = 18.sp
                        )
                    } else {
                        // Usamos tu componente público para las pestañas inactivas
                        TextoSombra(
                            textoRes = textoRes,
                            enMayusculas = true
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun TopAppBarNavPreview() {
    DealFinder_PCTheme {
        TopAppBarNav(onBackClick = {})
    }
}

@Preview
@Composable
fun BottomAppBarNavPreview() {
    DealFinder_PCTheme {
        BottomAppBarNav(
            pestanhaSeleccionada = 0, // "OFERTAS" seleccionada
            onPestanhaClick = {}
        )
    }
}