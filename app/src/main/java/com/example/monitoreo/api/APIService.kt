package com.example.monitoreo.api

import com.example.monitoreo.api.HijoNetwork
import com.example.monitoreo.api.TutorNetwork
import com.example.monitoreo.models.AlertaResponse
import com.example.monitoreo.models.HijoResponse
import com.example.monitoreo.models.LoginResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface APIService
{
    @POST("registrarTutor")
    fun registrarTutor(@Body tutor: TutorNetwork): Call<ResponseBody>

    @POST("Login")
    fun Login(@Query("email") email: String, @Query("pass") pass: String): Call<LoginResponse>

    @POST("recuperaPass")
    fun recuperaPass(
        @Query("email") email: String,
        @Query("new_pass") new_pass: String
    ): Call<ResponseBody>

    @GET("listarHijos")
    fun listarHijos(@Query("email") email: String): Call<List<HijoResponse>>

    @POST("registrarHijo")
    fun registrarHijo(@Body hijo: HijoNetwork): Call<ResponseBody>

    @GET("tablaAlertasLast")
    fun tablaAlertasLast(
        @Query("email") email: String,
        @Query("hijo_id") hijo_id: Int
    ): Call<List<AlertaResponse>>

    @POST("actualizaTutor")
    fun actualizaTutor(@Body tutor: TutorNetwork): Call<ResponseBody>

    @POST("actualizaPass")
    fun actualizaPass(
        @Query("email") email: String,
        @Query("old_pass") old_pass: String,
        @Query("new_pass") new_pass: String
    ): Call<ResponseBody>

    @POST("eliminaHijo")
    fun eliminaHijo(@Body hijo: HijoNetwork): Call<ResponseBody>

    @POST("actualizaHijo")
    fun actualizaHijo(@Body hijo: HijoNetwork): Call<ResponseBody>
}