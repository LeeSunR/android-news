package kr.leesunr.news.core.design.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun AppTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val context = LocalContext.current
    val colors = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        if (isDarkTheme) dynamicDarkColorScheme(context)
        else dynamicLightColorScheme(context)
    } else {
        if (isDarkTheme) darkColorScheme()
        else lightColorScheme()
    }

    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(
        color = colors.surface,
        darkIcons = !isDarkTheme
    )

    MaterialTheme(
        colorScheme = colors,
        content = content
    )
}

private val LightColors = lightColorScheme()

private val DarkColors = darkColorScheme()