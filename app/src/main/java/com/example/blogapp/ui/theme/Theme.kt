package com.example.blogapp.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.RoundedCornerShape

val BrightColors = lightColorScheme(
    primary = Color(0xFFB56576),
    secondary = Color(0xFFD8A6A5),
    tertiary = Color(0xFFF0D9D6),
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.Black,
    onSecondary = Color.Black,
    onTertiary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
)

val Shapes = Shapes(
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(4.dp),
    large = RoundedCornerShape(0.dp)
)

@Composable
fun BrightTheme(
    content: @Composable () -> Unit
) {
    val context = LocalContext.current
    val isDarkTheme = isSystemInDarkTheme()
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (context as? Activity)?.window
            window?.let {
                it.statusBarColor = Color.White.toArgb()
                WindowCompat.getInsetsController(it, view)?.isAppearanceLightStatusBars = !isDarkTheme
            }
        }
    }

    MaterialTheme(
        colorScheme = BrightColors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}

