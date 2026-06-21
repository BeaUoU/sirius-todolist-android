@file:Suppress("DEPRECATION")

package br.com.sirius.todolist.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = MainIcons,
    secondary = CheckboxChecked,
    tertiary = PageTitles,
    background = ScreenBackground,
    surface = TextFieldsBackground,
    onPrimary = ScreenBackground,
    onSecondary = ScreenBackground,
    onTertiary = ScreenBackground,
    onBackground = TextFieldsText,
    onSurface = TextFieldsText,
    surfaceContainer = TextFieldsBackground
)

private val LightColorScheme = lightColorScheme(
    primary = MainIcons,
    secondary = CheckboxChecked,
    tertiary = PageTitles,
    background = ScreenBackground,
    surface = TextFieldsBackground,
    onPrimary = ScreenBackground,
    onSecondary = ScreenBackground,
    onTertiary = ScreenBackground,
    onBackground = TextFieldsText,
    onSurface = TextFieldsText,
    surfaceContainer = TextFieldsBackground
)

@Composable
fun ToDoListTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.background.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
