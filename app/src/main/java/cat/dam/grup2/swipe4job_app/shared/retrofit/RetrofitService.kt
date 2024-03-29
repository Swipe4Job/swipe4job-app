package cat.dam.grup2.swipe4job_app.shared.retrofit

import cat.dam.grup2.swipe4job_app.features.recruiter.models.CompanyData
import cat.dam.grup2.swipe4job_app.features.recruiter.models.CompanyPost
import cat.dam.grup2.swipe4job_app.features.recruiter.models.OfferData
import cat.dam.grup2.swipe4job_app.features.recruiter.models.OfferPost
import cat.dam.grup2.swipe4job_app.shared.retrofit.model.LoginResponseData
import cat.dam.grup2.swipe4job_app.shared.retrofit.model.LogoutResponseData
import cat.dam.grup2.swipe4job_app.shared.retrofit.model.RemoteResult
import cat.dam.grup2.swipe4job_app.features.users.user_api_service.model.UserData
import cat.dam.grup2.swipe4job_app.features.users.user_api_service.model.UserLogin
import cat.dam.grup2.swipe4job_app.features.users.user_api_service.model.UserLogout
import cat.dam.grup2.swipe4job_app.features.users.user_api_service.model.UserPost
import com.google.firebase.components.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface RetrofitService {
    @GET("/users")
    suspend fun listUsers(
        @Query("criteria") param1: String
    ): RemoteResult<List<UserData>>

    @GET("/users/me")
    suspend fun getMyData(
        @Header("Authorization") token: String,
    ): RemoteResult<UserData>

    @GET("/company")
    suspend fun listCompanies(
        @Query("criteria") param1: String
    ): RemoteResult<List<CompanyData>>

    @GET("/offer")
    suspend fun listOffers(
        @Query("criteria") param1: String
    ): RemoteResult<List<OfferData>>
    @POST("/users/register")
    suspend fun addUser(
        @Body post: UserPost
    ): RemoteResult<Unit>

    @POST("/company")
    suspend fun addCompany(
        @Body post: CompanyPost
    ): RemoteResult<Unit>

    @POST("/offer")
    suspend fun addOffer(
        @Body post: OfferPost
    ): RemoteResult<Unit>

    @POST("/auth/users/login")
    suspend fun userLogin(
        @Body post: UserLogin
    ): RemoteResult<LoginResponseData>

    @POST("/auth/users/logout")
    suspend fun userLogout(
        @Body post: UserLogout
    ): RemoteResult<LogoutResponseData>
}

object RetrofitServiceFactory {
    val BASE_URL = "https://swipe4job-api.fly.dev/"

    private var instance: RetrofitService? = null

    private val httpClientBuilder = OkHttpClient.Builder();

    fun makeRetrofitService(): RetrofitService {
        if (instance == null) {
            httpClientBuilder.addInterceptor {
                val request =
                    it.request().newBuilder().addHeader("ngrok-skip-browser-warning", "").build()
                it.proceed(request)
            }

            instance = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClientBuilder.build())
                .build().create(RetrofitService::class.java)
        }
        return instance!!
    }
}
