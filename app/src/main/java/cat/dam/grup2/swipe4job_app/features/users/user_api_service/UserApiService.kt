package cat.dam.grup2.swipe4job_app.features.users.user_api_service

import Criteria
import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import cat.dam.aria.retrofit.shared.criteria.CriteriaEncoder
import cat.dam.grup2.swipe4job_app.CustomError
import cat.dam.grup2.swipe4job_app.features.auth.state.AuthViewModel
import cat.dam.grup2.swipe4job_app.shared.retrofit.RetrofitService
import cat.dam.grup2.swipe4job_app.shared.retrofit.model.CompanyData
import cat.dam.grup2.swipe4job_app.shared.retrofit.model.CompanyPost
import cat.dam.grup2.swipe4job_app.shared.retrofit.model.LoginResponseData
import cat.dam.grup2.swipe4job_app.shared.retrofit.model.LogoutResponseData
import cat.dam.grup2.swipe4job_app.shared.retrofit.model.RemoteResult
import cat.dam.grup2.swipe4job_app.features.users.user_api_service.model.UserData
import cat.dam.grup2.swipe4job_app.features.users.user_api_service.model.UserLogin
import cat.dam.grup2.swipe4job_app.features.users.user_api_service.model.UserLogout
import cat.dam.grup2.swipe4job_app.features.users.user_api_service.model.UserPost

class UserApiService(val retrofit: RetrofitService) {
    suspend fun listUsers(criteria: Criteria): List<UserData> {
        val encodedCriteria = CriteriaEncoder.encodeCriteria(criteria)
        println(encodedCriteria)
        val results = retrofit.listUsers(encodedCriteria)
        return results.data
    }
    suspend fun listCompanies(criteria: Criteria): List<CompanyData> {
        val encodedCriteria = CriteriaEncoder.encodeCriteria(criteria)
        println(encodedCriteria)
        val results = retrofit.listCompanies(encodedCriteria)
        return results.data
    }
    suspend fun addUser(): Unit {
        val userPost = UserPost(
            email = "patata@example.com",
            lastName = "Patata",
            name = "Patata",
            password = "password123",
            phoneNumber = "123346597",
            role = "CANDIDATE"
        )
        val results = retrofit.addUser(userPost)
        return(results.data)
    }
    suspend fun addCompany(): Unit {
        val companyPost = CompanyPost(
            CIF = "834242R",
            companySize = "LESS_10",
            description = "si",
            name = "test",
            phone = "43243423",
            sector = "CONSTRUCTION"
        )
        val results = retrofit.addCompany(companyPost)
        return(results.data)
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    suspend fun getMyData(): UserData {
        val token = AuthViewModel.getInstance().accessToken // get token from AuthViewModel
        try {
            val response = retrofit.getMyData("Bearer $token")
            return response.data
        } catch (e: HttpException) {
            val errorMessage = "Error en la solicitud HTTP: ${e.message}"
            throw CustomError(errorMessage)
        } catch (e: Throwable) {
            val errorMessage = "Error inesperado: ${e.message}"
            throw CustomError(errorMessage)
        }
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    suspend fun userLogin(email: String, password: String): LoginResponseData {
        val userLogIn = UserLogin(
            email = email,
            password = password
        )
        try {
            var response = retrofit.userLogin(userLogIn)
            return response.data
        } catch (e: HttpException) {
            val errorMessage = "Error en la solicitud HTTP: ${e.message}"
            throw CustomError(errorMessage)
        } catch (e: Throwable) {
            val errorMessage = "Error inesperado: ${e.message}"
            throw CustomError(errorMessage)
        }
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    suspend fun userLogout(): RemoteResult<LogoutResponseData> {
        val userLogOut = UserLogout(
            token = "eyJhbGciOiJIUzI1NiJ9.eyJkYXRhIjp7InVzZXJJRCI6ImQ3NTI2N2QzLTllMDQtNDhhOS1hMzRhLWUyZTg0ZDdmODNiYyIsInJvbGUiOiJBRE1JTiJ9LCJ0eXBlIjoiYXV0aC51c2VyIiwia2luZCI6InJlZnJlc2giLCJqdGkiOiI1ZjQ1YTVlYy0yYjE3LTRmMjQtYWU5OS0yMDU1OWNhM2ZjZGIiLCJpYXQiOjE3MDg3MTA2MTQsImV4cCI6MTcwODczMjIxNH0.kJBJ5nUaHhGMrK1ncbWFvsB8jErV_r6h9LLhzrorKZc"
        )
        try {
            val response = retrofit.userLogout(userLogOut)
            return (response)
        } catch (e: HttpException) {
            val errorMessage = "Error en la solicitud HTTP: ${e.message}"
            throw CustomError(errorMessage)
        } catch (e: Throwable) {
            val errorMessage = "Error inesperado: ${e.message}"
            throw CustomError(errorMessage)
        }
    }
}