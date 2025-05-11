package com.example_app.pricyapp.NavScreen

import android.content.ClipData.Item
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
fun CryptoScreen(navController: NavController) {
    val viewModel: GoldViewModel = hiltViewModel()
    val cryptoData by viewModel.cryptoData.collectAsState()
    val error by viewModel.errorMessage.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchGoldData()
    }


    if (cryptoData != null) {
        val crypto = cryptoData!!.data.cryptocurrencies ?: emptyList()

        LazyColumn {


            items(crypto) { crypto ->

                Column {

                    Text(
                        text = crypto.label,
                        style = MaterialTheme.typography.bodyLarge
                    )

                    Text(text = ":قیمت${crypto.price}تومان ")


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