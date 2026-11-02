package com.example.dealfinder_pc.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

// Esquema de color Material 3 (la app es siempre oscura, sin colores dinámicos)
private val RetroColorScheme = darkColorScheme(
    primary = RetroYellow,
    onPrimary = RetroGround,
    primaryContainer = RetroPanel,
    onPrimaryContainer = RetroText,

    // Chips seleccionados, selectores activos
    secondary = RetroCyan,
    onSecondary = RetroGround,
    secondaryContainer = RetroCyan,
    onSecondaryContainer = RetroGround,

    tertiary = RetroPink,
    onTertiary = RetroGround,
    tertiaryContainer = RetroPink,
    onTertiaryContainer = RetroGround,

    background = RetroGround,
    onBackground = RetroText,

    surface = RetroGround,
    onSurface = RetroText,
    surfaceVariant = RetroPanel,
    onSurfaceVariant = RetroMuted,

    // Tarjetas (Card) y barra inferior (NavigationBar)
    surfaceContainerLowest = RetroNavBar,
    surfaceContainerLow = RetroPanel,
    surfaceContainer = RetroNavBar,
    surfaceContainerHigh = RetroPanel,
    surfaceContainerHighest = RetroPanel,

    outline = RetroText,
    outlineVariant = RetroDivider,

    error = RetroPink,
    onError = RetroGround,
    errorContainer = RetroPink,
    onErrorContainer = RetroGround
)

// Estilo retro: esquinas rectas en todo
private val RetroShapes = Shapes(
    extraSmall = RoundedCornerShape(0.dp),
    small = RoundedCornerShape(0.dp),
    medium = RoundedCornerShape(0.dp),
    large = RoundedCornerShape(0.dp),
    extraLarge = RoundedCornerShape(0.dp)
)

// Colores que Material 3 no tiene
@Immutable
data class RetroExtraColors(
    val success: Color,       // descuentos y precios de oferta
    val onSuccess: Color,
    val navBar: Color,        // fondo de la barra inferior
    val inputBackground: Color,
    val hardShadow: Color     // sombra dura de tarjetas y botones
)

private val RetroExtras = RetroExtraColors(
    success = RetroGreen,
    onSuccess = RetroOnGreen,
    navBar = RetroNavBar,
    inputBackground = RetroNavBar,
    hardShadow = RetroShadow
)

val LocalRetroExtraColors = staticCompositionLocalOf { RetroExtras }

// Uso: MaterialTheme.extraColors.success
val MaterialTheme.extraColors: RetroExtraColors
    @Composable
    @ReadOnlyComposable
    get() = LocalRetroExtraColors.current

@Composable
fun DealFinder_PCTheme(content: @Composable () -> Unit) {
    CompositionLocalProvider(LocalRetroExtraColors provides RetroExtras) {
        MaterialTheme(
            colorScheme = RetroColorScheme,
            typography = AppTypography, // viene de Type.kt
            shapes = RetroShapes,
            content = content
        )
    }
}
