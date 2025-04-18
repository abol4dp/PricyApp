package com.example_app.pricyapp.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example_app.pricyapp.retrofit.GoldApiRepository
import com.example_app.pricyapp.retrofit.model.GoldModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GoldViewModel @Inject constructor(
    private val repository: GoldApiRepository

) : ViewModel() {


    private val _goldData = MutableLiveData<GoldModel>()
    val goldData: LiveData<GoldModel> get() = _goldData

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    private val _currencyData = MutableLiveData<GoldModel>()
    val currencyData: LiveData<GoldModel> get() = _currencyData

    private val _cryptoData = MutableLiveData<GoldModel>()
    val cryptoData: LiveData<GoldModel> get() = _cryptoData


    fun fetchGoldData() {

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = repository.getData()
                result.onSuccess { data ->
                    _goldData.postValue(data)
                    _currencyData.postValue(data)
                    _cryptoData.postValue(data)
                }.onFailure { error ->
                    _errorMessage.postValue(error.message ?: "Unknown Error")
                }

            } catch (e: Exception) {
                _errorMessage.postValue(e.message ?: "Unknown Error")
            }
        }


    }
}








