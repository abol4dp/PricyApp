package com.example_app.pricyapp.retrofit.time

import android.util.Log
import com.example_app.pricyapp.retrofit.model.DateModel
import com.example_app.pricyapp.retrofit.model.GoldModel
import javax.inject.Inject

class TimeApiRepository @Inject constructor(
    private val apiService: TimeApiService
) {

    private val TAG = "TimeApiRepository" // تگ برای لاگ‌ها

    suspend fun getDataTime(): Result<DateModel> {
        return try {
            Log.d(TAG, "Starting API call to getTime with short = true")
            val response = apiService.getTime(true)
            Log.d(TAG, "API Response: $response")
            Result.success(response)
        } catch (e: Exception) {
            Log.e(TAG, "Error occurred while fetching time data: ${e.message}", e)
            Result.failure(e)
        }
    }
}