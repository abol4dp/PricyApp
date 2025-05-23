package com.example_app.pricyapp.NavScreen

import android.content.ClipData.Item
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example_app.pricyapp.mvvm.GoldViewModel
import com.example_app.pricyapp.ui.theme.mainBackColor


@Composable
fun CryptoScreen(navController: NavController) {
    val viewModel: GoldViewModel = hiltViewModel()
    val cryptoData by viewModel.cryptoData.collectAsState()
    val error by viewModel.errorMessage.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchGoldData()
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp),
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.Center


    ) {







    if (cryptoData != null) {
        val crypto = cryptoData!!.data.cryptocurrencies ?: emptyList()




        LazyColumn (
            modifier = Modifier.padding(bottom = 230.dp)

        ){


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
}