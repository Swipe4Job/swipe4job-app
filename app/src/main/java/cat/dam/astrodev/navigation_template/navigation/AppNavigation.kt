package cat.dam.astrodev.navigation_template.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import cat.dam.astrodev.navigation_template.screens.MainScreen

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "screen1") {
        composable("screen1") {
            MainScreen()
        }
    }
}
