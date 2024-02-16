package cat.dam.grup2.swipe4job_app.retrofit

import android.graphics.Region
import cat.dam.grup2.swipe4job_app.model.RemoteResult
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

data class HttpResponse<T>(val message: String, val success: Boolean, val data: T, val errorCode: Int)

interface RetrofitService {
    @GET("/users")
    suspend fun listApi(
        @Query("criteria") param1: String
    ) : RemoteResult
}

object RetrofitServiceFactory {
    fun makeRetrofitService(): RetrofitService {
        return Retrofit.Builder()
            .baseUrl("https://swipe4job-api.fly.dev/") // Remove "/swagger" from the base URL
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(RetrofitService::class.java)
    }
}
