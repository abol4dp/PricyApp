package com.example_app.pricyapp.retrofit

import com.example_app.pricyapp.retrofit.model.GoldModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class GoldApiRepository @Inject constructor(

    private val apiService: GoldApiService
) {


    fun getData(callBack: GoldCallBack) {
        apiService.getGolds().enqueue(object : Callback<GoldModel> {
            override fun onResponse(call: Call<GoldModel>, response: Response<GoldModel>) {
                if (response.isSuccessful && response.body() != null) {
                    callBack.onSuccess(response.body()!!)
                } else {
                    callBack.onNotSuccess("Response was not successful or empty body")
                }
            }

            override fun onFailure(call: Call<GoldModel>, t: Throwable) {
                callBack.onFailure(t)
            }
        })
    }
}
