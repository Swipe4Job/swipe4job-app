package cat.dam.grup2.swipe4job_app.features.users

import Criteria
import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import cat.dam.aria.retrofit.shared.criteria.CriteriaEncoder
import cat.dam.grup2.swipe4job_app.CustomError
import cat.dam.grup2.swipe4job_app.shared.retrofit.RetrofitService
import cat.dam.grup2.swipe4job_app.shared.retrofit.model.LoginResponseData
import cat.dam.grup2.swipe4job_app.shared.retrofit.model.LogoutResponseData
import cat.dam.grup2.swipe4job_app.shared.retrofit.model.RemoteResult
import cat.dam.grup2.swipe4job_app.shared.retrofit.model.UserData
import cat.dam.grup2.swipe4job_app.shared.retrofit.model.UserLogin
import cat.dam.grup2.swipe4job_app.shared.retrofit.model.UserLogout
import cat.dam.grup2.swipe4job_app.shared.retrofit.model.UserPost

class UserApiService(val retrofit: RetrofitService) {
    suspend fun listUsers(criteria: Criteria): List<UserData> {
        val encodedCriteria = CriteriaEncoder.encodeCriteria(criteria)
        val results = retrofit.listUsers(encodedCriteria)
        return results.data
    }

    suspend fun addUser(): Unit {
        val userPost = UserPost(
            email = "test@example.com",
            lastName = "Test",
            name = "Test",
            password = "password123",
            phoneNumber = "123346589",
            role = "CANDIDATE"
        )
        val results = retrofit.addUser(userPost)
        return(results.data)
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    suspend fun userLogin(email: String, password: String): RemoteResult<LoginResponseData> {
        val userLogIn = UserLogin(
            email = email,
            password = password
        )
        try {
            var response = retrofit.userLogin(userLogIn)
            return (response)
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