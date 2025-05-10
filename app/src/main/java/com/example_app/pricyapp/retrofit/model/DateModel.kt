package com.example_app.pricyapp.retrofit.model

import com.google.gson.annotations.SerializedName

data class DateModel(
    val success: Int,
    val message: String,
    val help: String,
    val date: Date
)

data class Date(
    @SerializedName("F") val F_value: String,
    @SerializedName("Y") val Y_value: String,
    @SerializedName("j") val j_value: String,
    @SerializedName("l") val l_value: String,
    @SerializedName("h") val h_value: Int,
    @SerializedName("i") val i_value: Int

)