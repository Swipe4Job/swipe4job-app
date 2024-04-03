package cat.dam.grup2.swipe4job_app


import RecruiterNotifications
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import cat.dam.grup2.swipe4job_app.features.candidate.screens.AddExperience
import cat.dam.grup2.swipe4job_app.features.candidate.screens.AddLanguage
import cat.dam.grup2.swipe4job_app.features.candidate.screens.AddPreferences
import cat.dam.grup2.swipe4job_app.features.candidate.screens.AddSoftSkill
import cat.dam.grup2.swipe4job_app.features.candidate.screens.AddStudy
import cat.dam.grup2.swipe4job_app.shared.retrofit.RetrofitServiceFactory
import cat.dam.grup2.swipe4job_app.features.candidate.screens.CandidateCV
import cat.dam.grup2.swipe4job_app.features.candidate.screens.CandidateComplexDetails
import cat.dam.grup2.swipe4job_app.features.candidate.screens.CandidateConnections
import cat.dam.grup2.swipe4job_app.features.candidate.screens.CandidateNotifications
import cat.dam.grup2.swipe4job_app.features.candidate.screens.CandidateSignUpPage1
import cat.dam.grup2.swipe4job_app.features.candidate.screens.CandidateSignUpPage2
import cat.dam.grup2.swipe4job_app.features.candidate.screens.CandidateSignUpPage3
import cat.dam.grup2.swipe4job_app.features.candidate.screens.CandidateSimpleDetails
import cat.dam.grup2.swipe4job_app.features.candidate.screens.RecruiterContact
import cat.dam.grup2.swipe4job_app.features.recruiter.screens.CandidateContact
import cat.dam.grup2.swipe4job_app.features.recruiter.screens.CompanyPostOfferPage1
import cat.dam.grup2.swipe4job_app.features.recruiter.screens.CompanyPostOfferPage2
import cat.dam.grup2.swipe4job_app.features.recruiter.screens.CompanyPostOfferPage3
import cat.dam.grup2.swipe4job_app.features.recruiter.screens.JobOfferComplexDetails
import cat.dam.grup2.swipe4job_app.features.recruiter.screens.JobOfferRecruiterView
import cat.dam.grup2.swipe4job_app.features.recruiter.screens.JobOfferSimpleDetails
import cat.dam.grup2.swipe4job_app.features.recruiter.screens.OffersList
import cat.dam.grup2.swipe4job_app.features.recruiter.screens.RecruiterConnections
import cat.dam.grup2.swipe4job_app.features.recruiter.screens.RecruiterSignUpPage1
import cat.dam.grup2.swipe4job_app.features.recruiter.screens.RecruiterSignUpPage2
import cat.dam.grup2.swipe4job_app.features.recruiter.screens.candidateToView
import cat.dam.grup2.swipe4job_app.features.recruiter.screens.itemToView
import cat.dam.grup2.swipe4job_app.features.candidate.screens.recruiterToView
import cat.dam.grup2.swipe4job_app.features.users.user_api_service.UserApiService
import cat.dam.grup2.swipe4job_app.features.users.screens.login.RolSelection
import cat.dam.grup2.swipe4job_app.features.users.screens.login.UserLoginForm
import cat.dam.grup2.swipe4job_app.features.users.state.UserViewModel
import cat.dam.grup2.swipe4job_app.shared.screen.SplashScreen

val userApiService = UserApiService(RetrofitServiceFactory.makeRetrofitService())

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "candidateSimpleDetails") {

        composable("splashScreen") {
            SplashScreen(navController = navController)
        }

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
            CandidateComplexDetails(navController)
        }

        composable("jobOfferSimpleDetails") {
            JobOfferSimpleDetails(navController)
        }

        composable("jobOfferComplexDetails") {
            JobOfferComplexDetails(navController)
        }

        composable("jobOfferRecruiterView") { navBackStackEntry ->
            if (itemToView == null) {
                throw Exception("item to view is null")
            }
            JobOfferRecruiterView(navController, itemToView!!)
        }

        composable("candidateSignUpPage1") {
            CandidateSignUpPage1(navController, userApiService)
        }

        composable("candidateSignUpPage2") {
            CandidateSignUpPage2(navController)
        }

        composable("candidateSignUpPage3") {
            CandidateSignUpPage3(navController)
        }

        composable("recruiterSignUpPage1") {
            RecruiterSignUpPage1(navController, userApiService)
        }

        composable("recruiterSignUpPage2") {
            RecruiterSignUpPage2(navController, userApiService)
        }

        composable("candidateCV") {
            val userViewModel = UserViewModel.getInstance()
            if (userViewModel.userData == null) {
                navController.navigate("userLoginForm")
            }
            CandidateCV(navController)
        }

        composable("offersList") {
            OffersList(navController, userApiService)
        }

        composable("companyPostOfferPage1") {
            CompanyPostOfferPage1(navController)
        }

        composable("companyPostOfferPage2") {
            CompanyPostOfferPage2(navController)
        }

        composable("companyPostOfferPage3") {
            CompanyPostOfferPage3(navController, userApiService)
        }

        composable("recruiterConnections") {
            RecruiterConnections(navController)
        }

        composable("candidateContact") { navBackStackEntry ->
            if (candidateToView == null) {
                throw Exception("candidate to view is null")
            }
            CandidateContact(navController, candidateToView!!)
        }

        composable("recruiterNotifications") {
            RecruiterNotifications(navController)
        }

        composable("addLanguage") {
            AddLanguage(navController)
        }

        composable("addSoftSkill") {
            AddSoftSkill(navController)
        }

        composable("addStudy") {
            AddStudy(navController)
        }

        composable("addExperience") {
            AddExperience(navController)
        }

        composable("addPreferences") {
            AddPreferences(navController)
        }

        composable("candidateConnections") {
            CandidateConnections(navController)
        }

        composable("recruiterContact") { navBackStackEntry ->
            if (recruiterToView == null) {
                throw Exception("recruiter to view is null")
            }
            RecruiterContact(navController, recruiterToView!!)
        }

        composable("candidateNotifications") {
            CandidateNotifications(navController)
        }
    }
}
