package cat.dam.grup2.swipe4job_app


import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import cat.dam.grup2.swipe4job_app.features.candidate.screens.CandidateCV
import cat.dam.grup2.swipe4job_app.features.candidate.screens.CandidateComplexDetails
import cat.dam.grup2.swipe4job_app.features.candidate.screens.CandidateSignUpPage1
import cat.dam.grup2.swipe4job_app.features.candidate.screens.CandidateSignUpPage2
import cat.dam.grup2.swipe4job_app.features.candidate.screens.CandidateSignUpPage3
import cat.dam.grup2.swipe4job_app.features.candidate.screens.CandidateSimpleDetails
import cat.dam.grup2.swipe4job_app.features.recruiter.screens.CompanyPostOfferPage1
import cat.dam.grup2.swipe4job_app.features.recruiter.screens.CompanyPostOfferPage2
import cat.dam.grup2.swipe4job_app.features.recruiter.screens.CompanyPostOfferPage3
import cat.dam.grup2.swipe4job_app.features.recruiter.screens.JobOfferComplexDetails
import cat.dam.grup2.swipe4job_app.features.recruiter.screens.JobOfferRecruiterView
import cat.dam.grup2.swipe4job_app.features.recruiter.screens.JobOfferSimpleDetails
import cat.dam.grup2.swipe4job_app.features.recruiter.screens.OffersList
import cat.dam.grup2.swipe4job_app.features.recruiter.screens.RecruiterSignUpPage1
import cat.dam.grup2.swipe4job_app.features.recruiter.screens.RecruiterSignUpPage2
import cat.dam.grup2.swipe4job_app.features.recruiter.screens.RecruiterSignUpPage3
import cat.dam.grup2.swipe4job_app.features.recruiter.screens.itemToView
import cat.dam.grup2.swipe4job_app.features.users.UserApiService
import cat.dam.grup2.swipe4job_app.features.users.screens.login.RolSelection
import cat.dam.grup2.swipe4job_app.features.users.screens.login.UserLoginForm
import cat.dam.grup2.swipe4job_app.shared.retrofit.RetrofitServiceFactory

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun AppNavigation(navController: NavHostController) {
    val userApiService = UserApiService(RetrofitServiceFactory.makeRetrofitService())
    NavHost(navController = navController, startDestination = "userLoginForm") {
        composable("userLoginForm") {
            UserLoginForm(navController, userApiService)
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

        composable("jobOfferRecruiterView") { navBackStackEntry ->
            if (itemToView == null) {
                println("handle error")
                throw Exception("item to view is null")
            }
            JobOfferRecruiterView(navController, itemToView!!)
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

        composable("offersList") {
            OffersList(navController)
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