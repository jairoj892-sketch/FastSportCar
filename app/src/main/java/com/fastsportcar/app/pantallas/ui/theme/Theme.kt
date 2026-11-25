
package com.fastsportcar.app.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val RojoPrincipal = Color(0xFFD71920)
val RojoOscuro = Color(0xFFAD1018)
val Blanco = Color(0xFFFFFFFF)
val FondoClaro = Color(0xFFF7F7F7)
val GrisClaro = Color(0xFFF0F0F0)
val TextoOscuro = Color(0xFF252525)
val TextoSecundario = Color(0xFF777777)

private val FastSportCarColors = lightColorScheme(
    primary = RojoPrincipal,
    onPrimary = Blanco,
    primaryContainer = Color(0xFFFFDAD9),
    onPrimaryContainer = RojoOscuro,
    secondary = Color(0xFF444444),
    onSecondary = Blanco,
    background = FondoClaro,
    onBackground = TextoOscuro,
    surface = Blanco,
    onSurface = TextoOscuro,
    surfaceVariant = GrisClaro,
    onSurfaceVariant = TextoSecundario,
    error = Color(0xFFBA1A1A),
    onError = Blanco
)

@Composable
fun FastSportCarTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = FastSportCarColors,
        content = content
    )
}
