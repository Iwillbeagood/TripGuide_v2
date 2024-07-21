package com.jun.tripguide_v2.core.designsystem.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    surfaceDim = Black,
    primary = Purple80,
    primaryContainer = Black,
    secondary = PurpleGrey80,
    tertiary = Pink80,
    background = DuskGray,
    onTertiary = White,
    onSurface = White,
    onSurfaceVariant = Black
)

private val LightColorScheme = lightColorScheme(
    surfaceDim = White,
    primary = Sky,
    primaryContainer = White,
    secondary = PurpleGrey40,
    tertiary = Pink40,
    background = White,
    onTertiary = Black,
    onSurface = Black,
    onSurfaceVariant = White
    )

val LocalDarkTheme = compositionLocalOf { true }

@Composable
fun MyTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    if (!LocalInspectionMode.current) {
        val view = LocalView.current
        SideEffect {
            val window = (view.context as Activity).window
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
            WindowCompat.getInsetsController(window, view).isAppearanceLightNavigationBars =
                !darkTheme
        }
    }

    CompositionLocalProvider(
        LocalDarkTheme provides darkTheme
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content
        )
    }
}