package com.andreising.mockapp3june2025.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = DarkOrange,
    onPrimary = White,
    secondary = DarkSecondaryOrange,
    onSecondary = Dark,
    surfaceVariant = DarkWhite,
    background = DarkSurface,
    outline = DarkGray
)

private val LightColorScheme = lightColorScheme(
    primary = Orange,
    onPrimary = Dark,
    secondary = SecondaryOrange,
    onSecondary = White,
    surfaceVariant = ContainerWhite,
    background = White,
    outline = Gray
)

@Composable
fun MockApp3June2025Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme
    else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}