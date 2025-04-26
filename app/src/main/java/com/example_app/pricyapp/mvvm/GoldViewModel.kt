package com.example_app.pricyapp.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example_app.pricyapp.retrofit.GoldApiRepository
import com.example_app.pricyapp.retrofit.model.GoldModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GoldViewModel @Inject constructor(
    private val repository: GoldApiRepository
) : ViewModel() {
    private val _goldData = MutableStateFlow<GoldModel?>(null)
    val goldData: StateFlow<GoldModel?> get() = _goldData

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> get() = _errorMessage

    private val _currencyData = MutableStateFlow<GoldModel?>(null)
    val currencyData: StateFlow<GoldModel?> get() = _currencyData

    private val _cryptoData = MutableStateFlow<GoldModel?>(null)
    val cryptoData: StateFlow<GoldModel?> get() = _cryptoData

    fun fetchGoldData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = repository.getData()
                result.onSuccess { data ->
                    _goldData.value = data
                    _currencyData.value = data
                    _cryptoData.value = data
                }.onFailure { error ->
                    _errorMessage.value = error.message ?: "Unknown Error"
                }
            } catch (e: Exception) {
                _errorMessage.value = e.message ?: "Unknown Error"
            }
        }
    }
}






