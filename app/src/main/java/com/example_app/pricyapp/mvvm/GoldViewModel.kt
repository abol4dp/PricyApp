package com.example_app.pricyapp.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example_app.pricyapp.retrofit.GoldApiRepository
import com.example_app.pricyapp.retrofit.GoldCallBack
import com.example_app.pricyapp.retrofit.model.GoldModel

class GoldViewModel : ViewModel() {

    private val _goldData = MutableLiveData<GoldModel>()
    val goldData: LiveData<GoldModel> get() = _goldData

    private val _errorMassage = MutableLiveData<String>()
    val errorMassage: LiveData<String> get() = _errorMassage


    fun fetchGoldData() {
        GoldApiRepository.instance.getGolds(object : GoldCallBack {
            override fun onSuccess(data: GoldModel) {
                _goldData.value = data //
            }

            override fun onNotSuccess(message: String) {
                _errorMassage.value = message
            }

            override fun onFailure(t: Throwable) {
                _errorMassage.value = t.message?: "Unknown Error"
            }
        })
    }
}
