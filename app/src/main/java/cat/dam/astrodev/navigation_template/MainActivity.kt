package cat.dam.astrodev.navigation_template

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import cat.dam.astrodev.navigation_template.navigation.AppNavigation
import cat.dam.astrodev.navigation_template.ui.theme.Navigation_templateTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Navigation_templateTheme {
                val navbarController = rememberNavController()
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation(navController = navbarController)
                }
            }
        }
    }
}