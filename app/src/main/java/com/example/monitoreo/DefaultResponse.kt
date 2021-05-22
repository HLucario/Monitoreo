package com.example.monitoreo

import com.google.gson.annotations.SerializedName

data class DefaultResponse(@SerializedName("status")val status:String, @SerializedName("message")val message:String)