package com.exhale.chat_start.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val NoirColorPalette = darkColors(
    primary = NoirPrimary,
    primaryVariant = NoirPrimaryVariant,
    secondary = NoirSecondary,
    background = NoirBackground,
    surface = NoirSurface,
    onPrimary = Color.White,
    onSecondary = NoirBackground,
    onBackground = NoirText,
    onSurface = NoirText
)

@Composable
fun Chat_startTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = NoirColorPalette,
        typography = Typography,
        content = content
    )
}