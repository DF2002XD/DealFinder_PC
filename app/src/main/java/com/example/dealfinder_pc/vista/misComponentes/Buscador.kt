package com.example.dealfinder_pc.vista.misComponentes

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dealfinder_pc.R
import com.example.dealfinder_pc.ui.theme.DealFinder_PCTheme
import com.example.dealfinder_pc.ui.theme.bodyFontFamily

@Composable
fun Buscador(
    textoBusqueda: String,
    onTextoCambiado: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = textoBusqueda,
        onValueChange = onTextoCambiado,
        modifier = modifier
            .fillMaxWidth()
            .border(2.dp, MaterialTheme.colorScheme.secondary, RectangleShape), // Borde cyan brillante
        shape = RectangleShape,
        textStyle = androidx.compose.ui.text.TextStyle(
            fontFamily = bodyFontFamily,
            color = MaterialTheme.colorScheme.onSurface
        ),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = MaterialTheme.colorScheme.surfaceContainerLowest,
            focusedContainerColor = MaterialTheme.colorScheme.surfaceContainerLowest,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
            focusedPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant
        ),
        placeholder = {
            Text(
                text = stringResource(id = R.string.ofertas_buscar),
                fontFamily = bodyFontFamily
            )
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search)
    )
}

@Preview(showBackground = true, backgroundColor = 0xFF191636)
@Composable
fun BuscadorPreview() {
    DealFinder_PCTheme {
        Buscador(
            textoBusqueda = "",
            onTextoCambiado = {}
        )
    }
}