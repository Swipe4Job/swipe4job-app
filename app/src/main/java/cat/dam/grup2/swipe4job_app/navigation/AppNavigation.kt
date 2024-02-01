package cat.dam.grup2.swipe4job_app.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import cat.dam.grup2.swipe4job_app.screens.UserLoginForm
import cat.dam.grup2.swipe4job_app.screens.CandidateSimpleDetails
import cat.dam.grup2.swipe4job_app.screens.CandidateComplexDetails
import cat.dam.grup2.swipe4job_app.screens.CandidateSignUpPage1
import cat.dam.grup2.swipe4job_app.screens.CandidateSignUpPage2
import cat.dam.grup2.swipe4job_app.screens.CandidateSignUpPage3
import cat.dam.grup2.swipe4job_app.screens.CompanyPostOfferPage1
import cat.dam.grup2.swipe4job_app.screens.CompanyPostOfferPage2
import cat.dam.grup2.swipe4job_app.screens.CompanyPostOfferPage3
import cat.dam.grup2.swipe4job_app.screens.RolSelection

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "rolSelection") {
        composable("userLoginForm") {
            UserLoginForm()
        }

        composable("candidateSimpleDetails") {
            CandidateSimpleDetails()
        }

        composable("candidateComplexDetails") {
            CandidateComplexDetails()
        }

        composable("candidateSignUpPage1") {
            CandidateSignUpPage1()
        }

        composable("candidateSignUpPage2") {
            CandidateSignUpPage2()
        }

        composable("candidateSignUpPage3") {
            CandidateSignUpPage3()
        }

        composable("companyPostOfferPage1") {
            CompanyPostOfferPage1()
        }

        composable("companyPostOfferPage2") {
            CompanyPostOfferPage2()
        }

        composable("companyPostOfferPage3") {
            CompanyPostOfferPage3()
        }

        composable("rolSelection") {
            RolSelection()
        }
    }
}
