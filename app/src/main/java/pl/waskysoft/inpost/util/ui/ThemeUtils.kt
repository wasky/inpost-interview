package pl.waskysoft.inpost.util.ui

import android.content.Context
import android.content.res.Configuration
import android.graphics.Color
import android.os.Build
import android.provider.Settings
import androidx.activity.SystemBarStyle
import androidx.compose.ui.graphics.toArgb
import pl.waskysoft.inpost.ui.theme.backgroundLight

object ThemeUtils {

    // Based on EdgeToEdge.DefaultDarkScrim
    private val DefaultDarkScrim = Color.argb(0x80, 0x1b, 0x1b, 0x1b)

    fun getNavigationBarStyle(context: Context): SystemBarStyle {
        val lightScrim = backgroundLight.copy(0.9f) // Based on EdgeToEdge.DefaultLightScrim
        val isDarkTheme = isSystemInDarkTheme(context)

        return if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O_MR1) {
            SystemBarStyle.dark(androidx.compose.ui.graphics.Color.Black.toArgb())
        } else if (isDarkTheme || isGestureNavigationEnabled(context)) {
            // Light scrim color is not transparent when using SystemBarStyle.light
            SystemBarStyle.auto(lightScrim.toArgb(), DefaultDarkScrim, detectDarkMode = { isDarkTheme })
        } else {
            // Light scrim color is ignored when using SystemBarStyle.auto()
            SystemBarStyle.light(lightScrim.toArgb(), DefaultDarkScrim)
        }
    }

    private fun isSystemInDarkTheme(context: Context): Boolean {
        val uiMode = context.resources.configuration.uiMode
        return (uiMode and Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES
    }

    private fun isGestureNavigationEnabled(context: Context): Boolean {
        return Settings.Secure.getInt(context.contentResolver, "navigation_mode", 0) == 2
    }

}