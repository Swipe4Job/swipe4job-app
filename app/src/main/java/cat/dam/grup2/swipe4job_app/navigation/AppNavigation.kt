package cat.dam.grup2.swipe4job_app.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import cat.dam.grup2.swipe4job_app.screens.CandidateSimpleDetails
import cat.dam.grup2.swipe4job_app.screens.UserLoginForm

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
