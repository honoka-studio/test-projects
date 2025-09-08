package de.honoka.lavender.android.uitest.ui.style

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun AppDefaultTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if(darkTheme) {
                dynamicDarkColorScheme(context)
            } else {
                dynamicLightColorScheme(context)
            }
        }
        darkTheme -> ColorSchemes.dark
        else -> ColorSchemes.light
    }
    MaterialTheme(
        colorScheme = colorScheme,
        typography = typography,
        content = content
    )
}

private object Colors {

    val Purple40 = Color(0xFF6650A4)

    val PurpleGrey40 = Color(0xFF625B71)

    val Pink40 = Color(0xFF7D5260)

    val Purple80 = Color(0xFFD0BCFF)

    val PurpleGrey80 = Color(0xFFCCC2DC)

    val Pink80 = Color(0xFFEFB8C8)
}

private object ColorSchemes {

    val light = lightColorScheme(
        primary = Colors.Purple40,
        secondary = Colors.PurpleGrey40,
        tertiary = Colors.Pink40
    )

    val dark = darkColorScheme(
        primary = Colors.Purple80,
        secondary = Colors.PurpleGrey80,
        tertiary = Colors.Pink80
    )
}

private val typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
)
