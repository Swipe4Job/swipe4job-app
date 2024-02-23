package cat.dam.grup2.swipe4job_app.retrofit

import cat.dam.grup2.swipe4job_app.model.LoginResponseData
import cat.dam.grup2.swipe4job_app.model.LogoutResponseData
import cat.dam.grup2.swipe4job_app.model.RemoteResult
import cat.dam.grup2.swipe4job_app.model.UserData
import cat.dam.grup2.swipe4job_app.model.UserLogin
import cat.dam.grup2.swipe4job_app.model.UserLogout
import cat.dam.grup2.swipe4job_app.model.UserPost
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

data class HttpResponse<T>(val message: String, val success: Boolean, val data: T, val errorCode: Int)

interface RetrofitService {
    @GET("/users")
    suspend fun listApi(
        @Query("criteria") param1: String
    ) : RemoteResult<List<UserData>>

    @POST("/users/register")
    suspend fun addUser(
        @Body post: UserPost
    ) : RemoteResult<Unit>
    @POST("/auth/users/login")
    suspend fun userLogin(
        @Body post: UserLogin
    ) : RemoteResult<LoginResponseData>
    @POST("/auth/users/logout")
    suspend fun userLogout(
        @Body post: UserLogout
    ) : RemoteResult<LogoutResponseData>
}

object RetrofitServiceFactory {
    fun makeRetrofitService(): RetrofitService {
        return Retrofit.Builder()
            .baseUrl("https://swipe4job-api.fly.dev/") // Remove "/swagger" from the base URL
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(RetrofitService::class.java)
    }
}
