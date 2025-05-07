package com.example_app.pricyapp.retrofit.time

import com.example_app.pricyapp.retrofit.model.DateModel


import retrofit2.http.GET
import retrofit2.http.Query

interface TimeApiService {
     @GET("date/now")
    suspend fun getTime(

         @Query("short") short: Boolean


    ): DateModel


}