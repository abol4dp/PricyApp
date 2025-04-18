package com.example_app.pricyapp.retrofit

import com.example_app.pricyapp.retrofit.model.GoldModel
import retrofit2.Call
import retrofit2.http.GET

interface GoldApiService {

    @GET("currencies")

   suspend fun getGolds(


    ): GoldModel


}