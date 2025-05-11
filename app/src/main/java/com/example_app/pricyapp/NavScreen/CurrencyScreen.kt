package com.example_app.pricyapp.NavScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example_app.pricyapp.mvvm.GoldViewModel


@Composable
fun CurrencyScreen(navController: NavController) {
    val viewModel: GoldViewModel = hiltViewModel()
    val currencyData by viewModel.currencyData.collectAsState()
    val error by viewModel.errorMessage.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchGoldData()
    }
    if (currencyData != null) {
        val currency = currencyData?.data?.currencies ?: emptyList()

        LazyColumn {
            items(currency) { currency ->
                Column {
                    Text(
                        text = currency.label,
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        text = "قیمت: ${currency.price} تومان",
                        style = MaterialTheme.typography.bodySmall
                    )
                }

            }
        }
    } else {
        if (!error.isNullOrEmpty()) {
            Text(
                text = "خطا: $error",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.error
            )


        }else
            CircularProgressIndicator()

    }


}



