package com.example_app.pricyapp.retrofit.model

import com.google.gson.annotations.SerializedName

data class GoldModel(

    val message: String,
    val data: Alldata

)


data class Alldata(

    val golds: List<ContentModel>,
    val currencies: List<ContentModel>,
    val cryptocurrencies: List<ContentModel>

)

data class ContentModel(

    val label: String,
    val price: Int


)

data class DateModel(
    val date: Date,
    val message: String

)

data class Date(
    @SerializedName("F") val month: String,
    @SerializedName("Y") val year: String,
    @SerializedName("I") val day: String,
    @SerializedName("J") val dayOfMonth: String


)

