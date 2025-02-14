package pl.waskysoft.inpost.screen

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import pl.waskysoft.inpost.util.ui.ThemeUtils

abstract class BaseActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            enableEdgeToEdge(navigationBarStyle = ThemeUtils.getNavigationBarStyle(this))
        }
    }

}