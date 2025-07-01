package com.example_app.pricyapp.mvvm

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example_app.pricyapp.retrofit.gold.GoldApiRepository
import com.example_app.pricyapp.retrofit.model.DateModel
import com.example_app.pricyapp.retrofit.model.GoldModel

import com.example_app.pricyapp.retrofit.time.TimeApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModel @Inject constructor(
    private val repository: GoldApiRepository,
    private val timeRepository: TimeApiRepository
) : ViewModel() {
    private val _goldData = MutableStateFlow<GoldModel?>(null)
    val goldData: StateFlow<GoldModel?>  = _goldData.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    private val _currencyData = MutableStateFlow<GoldModel?>(null)
    val currencyData: StateFlow<GoldModel?> = _currencyData.asStateFlow()

    private val _cryptoData = MutableStateFlow<GoldModel?>(null)
    val cryptoData: StateFlow<GoldModel?>  = _cryptoData.asStateFlow()

    private val _timeData = MutableStateFlow<DateModel?>(null)
    val timeData: StateFlow<DateModel?> = _timeData.asStateFlow()



    fun fetchGoldData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = repository.getData()
                result. onSuccess{ data ->
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
    }// for gold and currency

    fun fetchTimeData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                Log.d("GoldViewModel", "Fetching time data...")
                val result = timeRepository.getDataTime()
                result.onSuccess { date ->
                    Log.d("GoldViewModel", "Time data received and set: $date")
                    _timeData.value = date
                }.onFailure { error ->
                    Log.e("GoldViewModel", "Error fetching time data: ${error.message}")
                    _errorMessage.value = error.message ?: "Unknown Error"
                }
            } catch (e: Exception) {
                Log.e("GoldViewModel", "Exception in fetchTimeData: ${e.message}")
                _errorMessage.value = e.message ?: "Unknown Error"
            }
        }
    }


}






