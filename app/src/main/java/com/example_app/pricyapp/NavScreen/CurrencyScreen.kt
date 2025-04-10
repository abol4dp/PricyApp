package com.example_app.pricyapp.NavScreen

import android.icu.number.Precision.currency
import android.view.View
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavController
import com.example_app.pricyapp.mvvm.GoldViewModel
import com.example_app.pricyapp.retrofit.model.ContentModel
import com.example_app.pricyapp.retrofit.model.GoldModel
import com.example_app.pricyapp.retrofit.model.SharedViewModel


@Composable
fun CurrencyScreen(navController: NavController) {
    val viewModel = SharedViewModel.viewModel
    val currencyData by viewModel.currencyData.observeAsState()
    val error by viewModel.errorMassage.observeAsState()

    viewModel.fetchGoldData()

    if (currencyData != null) {
        val currency = currencyData?.data?.currencies ?: emptyList()

        LazyColumn {
            items(currency) { currency ->
                Column {
                    Text(text = currency.label, style = MaterialTheme.typography.bodyLarge)
                    Text(
                        text = "قیمت: ${currency.price} تومان",
                        style = MaterialTheme.typography.bodySmall
                    )
                }

            }
        }
    }else{
        if (!error.isNullOrEmpty()) {
            Text(
                text = "خطا: $error",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.error
            )


        }

    }


}



