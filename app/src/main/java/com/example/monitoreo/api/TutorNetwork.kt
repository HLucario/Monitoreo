package com.example.monitoreo.api

import com.example.monitoreo.Tutor
import com.google.gson.annotations.SerializedName

data class TutorNetwork(
    @SerializedName("email")
    val email: String,
    @SerializedName("nombre")
    val nombre: String,
    @SerializedName("ap_pat")
    val apellidoPaterno: String,
    @SerializedName("ap_Mat")
    val apellidoMaterno: String,
    @SerializedName("edad")
    val edad: Int,
    @SerializedName("password")
    val password: String,
)

fun Tutor.asNetwork() = TutorNetwork(
    email = email,
    nombre = nombre,
    apellidoPaterno = ap_pat,
    apellidoMaterno = ap_Mat,
    edad = edad,
    password = password,
)