package com.practicletaskapp.network


import com.practicletaskapp.data.responce.LoginResponse
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface MyApi {


    @POST(WebFields.LOGIN_PATH_PARAM)
    suspend fun callExecuteLogin(
        @Query(WebFields.REQUEST_EMAIL) email: String?,
        @Query(WebFields.REQUEST_PASSWORD) password: String?,
        @Query("device_token") device_token: String?,
        @Query("device_type") device_type: String?
    ): Response<LoginResponse>

    companion object {
        operator fun invoke(
            networkConnectionInterceptor: NetworkConnectionInterceptor
        ): MyApi {

            val okHttpClient = OkHttpClient.Builder().addInterceptor(networkConnectionInterceptor)
                .build() // Used to check internet connections.

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(WebFields.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MyApi::class.java)
        }
    }
}