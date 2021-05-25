package com.example.monitoreo

import retrofit2.Call
import retrofit2.http.*

interface APIService
{
    @POST("registrarTutor")
    fun registrarTutor(@Body tutor:Tutor):Call<DefaultResponse>
    @GET("Login")
    fun Login(@Query("email")email: String,@Query("pass")pass: String):Call<LoginResponse>
    @POST("recuperarPass")
    fun recuperarPass(@Query("email")email: String,@Query("pass")pass: String):Call<DefaultResponse>
    @GET("listarHijos")
    fun listarHijos(@Query("email")email:String):Call<HijoResponse>
}