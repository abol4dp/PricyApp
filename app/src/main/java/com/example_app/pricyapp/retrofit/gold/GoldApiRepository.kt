package com.example_app.pricyapp.retrofit.gold

import com.example_app.pricyapp.retrofit.model.GoldModel
import javax.inject.Inject

class GoldApiRepository @Inject constructor(

    private val apiService: GoldApiService
) {


    suspend fun getData(): Result<GoldModel> {
        return try {
            val response = apiService.getGolds()
            Result.success(response)


        } catch (e: Exception) {

            Result.failure(e)
        }

    }

}

