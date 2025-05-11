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
import androidx.navigation.NavHostController
import com.example_app.pricyapp.mvvm.GoldViewModel


@Composable
fun GoldScreen(navController: NavHostController) {
    val viewModel: GoldViewModel = hiltViewModel()
    val goldData by viewModel.goldData.collectAsState()
    val error by viewModel.errorMessage.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchGoldData()
    }

    if (goldData !== null) {
        val gold = goldData?.data?.golds ?: emptyList()


        LazyColumn {
            items(gold) { golds ->
                Column {
                    Text(
                        text = golds.label,
                        style = MaterialTheme.typography.bodyLarge
                    )

                    Text(
                        text = "قیمت: ${golds.price} تومان",
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


        } else
            CircularProgressIndicator()

    }
}