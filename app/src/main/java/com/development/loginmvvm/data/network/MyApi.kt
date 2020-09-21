package net.simplifiedcoding.mvvmsampleapp.data.network

import com.development.loginmvvm.model.UserDetail
import net.simplifiedcoding.mvvmsampleapp.data.network.responses.AuthResponse
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface MyApi {

    @FormUrlEncoded
    @POST("login")
    suspend fun userLogin(
        @Field("email") email: String,
        @Field("password") password: String
    ) : Response<AuthResponse>

    @GET("users/1")
    suspend fun getUserDetail() : Response<UserDetail>



     companion object{

        lateinit var mMyApi:MyApi

        operator fun invoke(
        ) : MyApi{


                val okkHttpclient = OkHttpClient.Builder()
                    .build()

                mMyApi=Retrofit.Builder()
                    .client(okkHttpclient)
                    .baseUrl(" https://reqres.in/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(MyApi::class.java)


            return mMyApi
        }
    }

}

