package com.example.monitoreo

import retrofit2.Call
import retrofit2.http.*

interface APIService
{
    @POST("registrarTutor")
    fun registrarTutor(tutor:String):Call<DefaultResponse>
    @GET("Login")
    fun Login(email: String,pass: String):Call<DefaultResponse>
}