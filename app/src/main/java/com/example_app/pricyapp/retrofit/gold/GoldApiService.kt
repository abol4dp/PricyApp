package com.example_app.pricyapp.retrofit.gold

import com.example_app.pricyapp.retrofit.model.GoldModel
import retrofit2.http.GET

interface GoldApiService {

    @GET("currencies")

   suspend fun getGolds(


    ): GoldModel





}