package com.example.monitoreo

import android.util.Base64
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient
{
    private val AUTH="Basic"+ Base64.encodeToString("root:root".toByteArray(),Base64.NO_WRAP)
    private const val BASE_URL="http://40.119.56.244:8080/ControlParental/rest/svc/"

    private val okHttpClient = OkHttpClient.Builder()
            .addInterceptor{ chain->
                val original = chain.request()

                val requestBuilder=original.newBuilder()
                    .addHeader("Authorization", AUTH)
                    .method(original.method(),original.body())

                val request = requestBuilder.build()
                chain.proceed(request)
            }.readTimeout(160, TimeUnit.SECONDS)
        .connectTimeout(120, TimeUnit.SECONDS)
        .writeTimeout(120, TimeUnit.SECONDS)
        .connectionPool(ConnectionPool(32,10, TimeUnit.SECONDS))
        .build()
    val instance: APIService by lazy{
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
        retrofit.create(APIService::class.java)
    }
}