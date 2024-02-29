package cat.dam.grup2.swipe4job_app.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import cat.dam.grup2.swipe4job_app.candidate.screens.CandidateCV
import cat.dam.grup2.swipe4job_app.login.UserLoginForm
import cat.dam.grup2.swipe4job_app.candidate.screens.CandidateSimpleDetails
import cat.dam.grup2.swipe4job_app.candidate.screens.CandidateComplexDetails
import cat.dam.grup2.swipe4job_app.candidate.screens.CandidateSignUpPage1
import cat.dam.grup2.swipe4job_app.candidate.screens.CandidateSignUpPage2
import cat.dam.grup2.swipe4job_app.candidate.screens.CandidateSignUpPage3
import cat.dam.grup2.swipe4job_app.recruiter.screens.CompanyPostOfferPage1
import cat.dam.grup2.swipe4job_app.recruiter.screens.CompanyPostOfferPage2
import cat.dam.grup2.swipe4job_app.recruiter.screens.CompanyPostOfferPage3
import cat.dam.grup2.swipe4job_app.recruiter.screens.JobOfferComplexDetails
import cat.dam.grup2.swipe4job_app.recruiter.screens.JobOfferSimpleDetails
import cat.dam.grup2.swipe4job_app.recruiter.screens.RecruiterSignUpPage1
import cat.dam.grup2.swipe4job_app.recruiter.screens.RecruiterSignUpPage2
import cat.dam.grup2.swipe4job_app.recruiter.screens.RecruiterSignUpPage3
import cat.dam.grup2.swipe4job_app.login.RolSelection

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "jobOfferSimpleDetails") {
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

        composable("candidateCV") {
            CandidateCV(navController)
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
