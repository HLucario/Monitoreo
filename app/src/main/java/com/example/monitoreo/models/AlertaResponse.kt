package com.example.monitoreo.models

import com.google.gson.annotations.SerializedName

data class AlertaResponse(
    @SerializedName("tutor_email") val tutor_email: String,
    @SerializedName("id_hijo") val id_hijo: Int,
    @SerializedName("id_alerta") val id_alerta: Int,
    @SerializedName("fecha") val fecha: String,
    @SerializedName("texto") val texto: String,
    @SerializedName("img") val img: String
)
