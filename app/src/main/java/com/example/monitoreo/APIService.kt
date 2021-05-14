package com.example.monitoreo

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface APIService
{
    @FormUrlEncoded
    @POST("registrarTutor")
    fun registrarTutor(
        @Field("Tutor")tutor:Tutor
    ): Call<DefaultResponse>
    @FormUrlEncoded
    @POST("Login")
    fun Login(
        @Field("email")email:String,
        @Field("pass")password:String
    ):Call<DefaultResponse>
    @FormUrlEncoded
    @POST("registarHijo")
    fun registrarHijo(
        @Field("Hijo")hijo:Hijo
    ): Call<DefaultResponse>
}