/**
 * Copyright (C) 2024  Antonio Tari
 *
 * This file is a part of Power Ampache 2
 * Ampache Android client application
 * @author Antonio Tari
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package luci.sixsixsix.powerampache2.lyricsplugin.presentation.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme: Colors
    @Composable
    get() = darkColors(
        primary = Color(0xFF70CCCC),
        onPrimary = Color(0xFF122E2E),
        secondary = Color(0xFFB8C0CC),
        onSecondary = Color(0xFF2D2F33),
        background = Color(0xFF282E2C),
        onBackground = Color(0xFFDFE5E3),
        surface = Color(0xFF1B1F1D),
        onSurface = Color(0xFFDFE5E3),
        error = Color(0xFFF593AB),
        onError = Color(0xFF520417),
    )

private val LightColorScheme
    @Composable
    get() = lightColors(
        primary = Color(0xFF1B6B6B),
        onPrimary = Color(0xFFF2FFFF),
        secondary = Color(0xFF4F5661),
        onSecondary = Color(0xFFFFF8F7),
        background = Color(0xFFF2FAF7),
        onBackground = Color(0xFF1F2124),
        surface = Color(0xFFF2FAF7),
        onSurface = Color(0xFF1F2124),
        error = Color(0xFF990127),
        onError = Color(0xFFFFF5F9),
    )

@Composable
fun PowerAmpachePluginTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true, // Dynamic color is available on Android 12+
    content: @Composable () -> Unit
) {
    //val isDynamic = dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
    val colorScheme = when {
        //isDynamic-> if (darkTheme) { darkColors() } else { lightColors() }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colors = colorScheme,
        //colorScheme = colorScheme,
        //typography = Typography,
        content = content
    )
}
