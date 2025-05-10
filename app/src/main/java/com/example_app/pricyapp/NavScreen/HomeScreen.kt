package com.example_app.pricyapp.NavScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example_app.pricyapp.R
import com.example_app.pricyapp.mvvm.GoldViewModel
import com.example_app.pricyapp.ui.theme.ButtonColor
import com.example_app.pricyapp.ui.theme.mainBackColor

@Composable
fun HomeScreen(navController: NavController, viewModel: GoldViewModel) {
    Box (
        modifier = Modifier.background(mainBackColor)

    ){

        CustomButton(navController)
        CustomTime(viewModel)
    }


}

@Composable
fun CustomTime(viewModel: GoldViewModel) {
    val viewModel: GoldViewModel = hiltViewModel()
    val timeData by viewModel.timeData.collectAsState()
    val error by viewModel.errorMessage.collectAsState()

    // فراخوانی fetchTimeData هنگام بارگذاری کامپوزابل
    LaunchedEffect(Unit) {
        viewModel.fetchTimeData()
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when {
            timeData != null -> {

                val date = timeData?.date
                Text(
                    text = "${date?.l_value} ${date?.j_value} ${date?.F_value} ",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.White
                )

                Text(
                    text = "${date?.H_value} : ${date?.i_value} ",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.White
                )

            }

            error != null -> {
                Text(
                    text = "خطا: $error",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.error
                )
            }

            else -> {
                CircularProgressIndicator()
            }
        }
    }
}


@Composable
fun CustomButton(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
        //.//background(mainBackColor),
        , contentAlignment = Alignment.BottomCenter
    ) {
        Column(
            modifier = Modifier.padding(bottom = 150.dp)
        ) {
            Button(
                onClick = { navController.navigate("goldscreen") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(130.dp)
                    .padding(horizontal = 20.dp, vertical = 8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = ButtonColor),
                shape = RoundedCornerShape(20.dp)
            ) {
                Box(modifier = Modifier.fillMaxSize()) {
                    Row(
                        Modifier
                            .padding(top = 12.dp)
                            .fillMaxSize(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Text(text = "طلا و سکه", fontSize = 23.sp)
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(
                            painter = painterResource(id = R.drawable.gold_svgrepo_com),
                            contentDescription = "Coin Icon",
                            modifier = Modifier.size(32.dp),
                            tint = Color.Unspecified
                        )
                    }
                    Text(
                        text = "قیمت لحظه ای طلا",
                        fontSize = 18.sp,
                        modifier = Modifier
                            .align(alignment = Alignment.BottomEnd)
                            .padding(bottom = 30.dp, end = 30.dp)
                    )
                }
            }

            Button(
                onClick = { navController.navigate("currencyscreen") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(130.dp)
                    .padding(horizontal = 20.dp, vertical = 8.dp),
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(containerColor = ButtonColor)
            ) {
                Box(modifier = Modifier.fillMaxSize()) {
                    Row(
                        modifier = Modifier
                            .padding(top = 12.dp)
                            .fillMaxSize(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Text(text = "دلار", fontSize = 23.sp)
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(
                            painter = painterResource(id = R.drawable.coin_svgrepo_com),
                            contentDescription = "currency Icon",
                            modifier = Modifier.size(32.dp),
                            tint = Color.Unspecified
                        )
                    }
                    Text(
                        text = "قیمت لحظه ای دلار",
                        fontSize = 18.sp,
                        modifier = Modifier
                            .align(alignment = Alignment.BottomEnd)
                            .padding(bottom = 30.dp, end = 30.dp)
                    )
                }
            }

            Button(
                onClick = { navController.navigate("cryptoscreen") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(130.dp)
                    .padding(horizontal = 20.dp, vertical = 8.dp),
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(containerColor = ButtonColor)
            ) {
                Box(modifier = Modifier.fillMaxSize()) {
                    Row(
                        modifier = Modifier
                            .padding(top = 12.dp)
                            .fillMaxSize(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Text(text = "ارز دیجیتال", fontSize = 23.sp)
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(
                            painter = painterResource(id = R.drawable.bitcoin_svgrepo_com__1_),
                            contentDescription = "btc",
                            modifier = Modifier.size(32.dp),
                            tint = Color.Unspecified
                        )
                    }
                    Text(
                        text = "قیمت لحظه ای ارز",
                        fontSize = 18.sp,
                        modifier = Modifier
                            .align(alignment = Alignment.BottomEnd)
                            .padding(bottom = 30.dp, end = 30.dp)
                    )
                }
            }
        }
    }
}

















