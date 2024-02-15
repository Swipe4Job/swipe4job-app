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
import cat.dam.grup2.swipe4job_app.screens.JobOfferComplexDetails
import cat.dam.grup2.swipe4job_app.screens.JobOfferSimpleDetails
import cat.dam.grup2.swipe4job_app.screens.RecruiterSignUpPage1
import cat.dam.grup2.swipe4job_app.screens.RecruiterSignUpPage2
import cat.dam.grup2.swipe4job_app.screens.RecruiterSignUpPage3
import cat.dam.grup2.swipe4job_app.screens.RolSelection

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "candidateSignUpPage2") {
        composable("userLoginForm") {
            UserLoginForm(navController)
        }

        composable("rolSelection") {
            RolSelection(navController)
        }

        composable("candidateSimpleDetails") {
            CandidateSimpleDetails(navController)
        }

        composable("candidateComplexDetails") {
            CandidateComplexDetails()
        }

        composable("jobOfferSimpleDetails") {
            JobOfferSimpleDetails(navController)
        }

        composable("jobOfferComplexDetails") {
            JobOfferComplexDetails()
        }

        composable("candidateSignUpPage1") {
            CandidateSignUpPage1(navController)
        }

        composable("candidateSignUpPage2") {
            CandidateSignUpPage2(navController)
        }

        composable("candidateSignUpPage3") {
            CandidateSignUpPage3(navController)
        }

        composable("recruiterSignUpPage1") {
            RecruiterSignUpPage1(navController)
        }

        composable("recruiterSignUpPage2") {
            RecruiterSignUpPage2(navController)
        }

        composable("recruiterSignUpPage3") {
            RecruiterSignUpPage3(navController)
        }

        composable("companyPostOfferPage1") {
            CompanyPostOfferPage1(navController)
        }

        composable("companyPostOfferPage2") {
            CompanyPostOfferPage2(navController)
        }

        composable("companyPostOfferPage3") {
            CompanyPostOfferPage3(navController)
        }


    }
}
