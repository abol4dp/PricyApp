package com.example_app.pricyapp.retrofit

import com.example_app.pricyapp.retrofit.model.GoldModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GoldApiService {

    @GET("now")

    fun getGolds(


    ):Call<GoldModel>


}