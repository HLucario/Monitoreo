package com.example.monitoreo

import com.example.monitoreo.api.HijoNetwork
import com.example.monitoreo.api.TutorNetwork
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface APIService
{
    @POST("registrarTutor")
    fun registrarTutor(@Body tutor:TutorNetwork):Call<ResponseBody>
    @POST("Login")
    fun Login(@Query("email")email: String,@Query("pass")pass: String):Call<LoginResponse>
    @POST("recuperarPass")
    fun recuperarPass(@Query("email")email: String,@Query("pass")new_pass: String):Call<ResponseBody>
    @GET("listarHijos")
    fun listarHijos(@Query("email")email:String):Call<HijoResponse>
    @POST("registrarHijo")
    fun registrarHijo(@Body hijo: HijoNetwork):Call<ResponseBody>
}