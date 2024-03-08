package cat.dam.grup2.swipe4job_app.shared.retrofit

import cat.dam.grup2.swipe4job_app.shared.retrofit.model.CompanyData
import cat.dam.grup2.swipe4job_app.shared.retrofit.model.LoginResponseData
import cat.dam.grup2.swipe4job_app.shared.retrofit.model.LogoutResponseData
import cat.dam.grup2.swipe4job_app.shared.retrofit.model.RemoteResult
import cat.dam.grup2.swipe4job_app.shared.retrofit.model.UserData
import cat.dam.grup2.swipe4job_app.shared.retrofit.model.UserLogin
import cat.dam.grup2.swipe4job_app.shared.retrofit.model.UserLogout
import cat.dam.grup2.swipe4job_app.shared.retrofit.model.UserPost
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface RetrofitService {
    @GET("/users")
    suspend fun listUsers(
        @Query("criteria") param1: String
    ) : RemoteResult<List<UserData>>
    @GET("/company")
    suspend fun listCompanies(
        @Query("criteria") param1: String
    ) : RemoteResult<List<CompanyData>>
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
    private var instance: RetrofitService? = null
    fun makeRetrofitService(): RetrofitService {
        if (instance == null) {
            instance = Retrofit.Builder()
//                .baseUrl("https://swipe4job-api.fly.dev/")
                .baseUrl("http://localhost:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(RetrofitService::class.java)
        }
        return instance!!
    }
}
