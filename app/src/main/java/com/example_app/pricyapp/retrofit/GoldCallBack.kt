package com.example_app.pricyapp.retrofit

import com.example_app.pricyapp.retrofit.model.GoldModel

interface GoldCallBack {

    fun onSuccess(data: GoldModel)
    fun onNotSuccess(message: String)
    fun onFailure(throwable: Throwable)
}