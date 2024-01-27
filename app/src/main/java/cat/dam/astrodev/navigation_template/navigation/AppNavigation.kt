package cat.dam.astrodev.navigation_template.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import cat.dam.astrodev.navigation_template.screens.CandidateSimpleDetails
import cat.dam.astrodev.navigation_template.screens.MainScreen
import cat.dam.astrodev.navigation_template.screens.UserLoginForm

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "candidateSimpleDetails") {
        composable("userLoginForm") {
            UserLoginForm()
        }

        composable("candidateSimpleDetails") {
            CandidateSimpleDetails()
        }
    }
}
