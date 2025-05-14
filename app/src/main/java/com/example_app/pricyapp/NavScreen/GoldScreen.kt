package com.example_app.pricyapp.NavScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example_app.pricyapp.mvvm.GoldViewModel
import java.lang.reflect.Modifier


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


        LazyColumn (
            modifier = androidx.compose.ui.Modifier.fillMaxSize()

        ){
            items(gold) { golds ->
                Box(modifier = androidx.compose.ui.Modifier.
                            fillMaxSize ()
                    .padding(top = 80.dp)
                        .height(50.dp) , contentAlignment = Alignment.CenterEnd) {


                    Column(

                    ) {


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