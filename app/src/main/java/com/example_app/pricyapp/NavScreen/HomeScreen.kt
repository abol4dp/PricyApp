package com.example_app.pricyapp.NavScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example_app.pricyapp.R
import com.example_app.pricyapp.mvvm.ViewModel
import com.example_app.pricyapp.ui.theme.ButEffect
import com.example_app.pricyapp.ui.theme.ButtonColor
import com.example_app.pricyapp.ui.theme.mainBackColor
import kotlinx.coroutines.delay

@Composable
fun HomeScreen(navController: NavController, viewModel: _root_ide_package_.com.example_app.pricyapp.mvvm.ViewModel) {


    Box(
        modifier = Modifier.background(mainBackColor)

    ) {


        CustomButton(navController)
        CustomTime(viewModel)
    }


}

@Composable
fun CustomTime(viewModel: _root_ide_package_.com.example_app.pricyapp.mvvm.ViewModel) {
    val viewModel: _root_ide_package_.com.example_app.pricyapp.mvvm.ViewModel = hiltViewModel()
    val timeData by viewModel.timeData.collectAsState()
    val error by viewModel.errorMessage.collectAsState()


    LaunchedEffect(Unit) {
        viewModel.fetchTimeData()
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 70.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when {
            timeData != null -> {


                val date = timeData?.date
                Text(
                    text = "${date?.l_value} ${date?.j_value} ${date?.F_value} ",
                    fontSize = 43.sp,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontFamily = FontFamily(
                            Font(R.font.regular, FontWeight.Normal)
                        ),
                        color = Color.White
                    )
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "${date?.H_value} : ${date?.i_value} ",
                    fontSize = 32.sp,
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

    var isClicked by remember { mutableStateOf<String?>(null) }



    Box(
        modifier = Modifier
            .fillMaxSize(), contentAlignment = Alignment.BottomCenter
    ) {
        Column(
            modifier = Modifier.padding(bottom = 90.dp)
        ) {

            LaunchedEffect(isClicked) {
                if (isClicked != null) {
                    delay(1000)
                }
            }
            Button(
                onClick = {
                    isClicked = "Gold"
                    navController.navigate("goldscreen")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(145.dp)
                    .padding(horizontal = 20.dp, vertical = 8.dp)
                    .border(
                        width = if (isClicked == "Gold") 1.dp else 0.dp,
                        color = if (isClicked == "Gold") ButEffect else Color.Transparent,
                        shape = RoundedCornerShape(20.dp)
                    ),
                colors = ButtonDefaults.buttonColors(containerColor = ButtonColor),
                shape = RoundedCornerShape(20.dp)
            ) {


                Icon(
                    painter = painterResource(id = R.drawable.arrow_left),
                    contentDescription = "Coin Icon",
                    modifier = Modifier.size(32.dp),
                    tint = Color.Unspecified
                )

                Box(modifier = Modifier.fillMaxSize()) {
                    Row(
                        Modifier
                            .padding(top = 12.dp)
                            .fillMaxSize(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Text(
                            text = "طلا و سکه",
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontFamily = FontFamily(
                                    Font(R.font.regular, FontWeight.Normal)
                                ),
                                fontSize = 23.sp,

                                )
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(
                            painter = painterResource(id = R.drawable.banking_coin_svgrepo_com__5_),
                            contentDescription = "Coin Icon",
                            modifier = Modifier.size(32.dp),
                            tint = Color.Unspecified
                        )
                    }
                    Text(

                        modifier = Modifier
                            .align(alignment = Alignment.BottomEnd)
                            .padding(bottom = 30.dp, end = 30.dp),
                        text = "قیمت لحظه ای طلا",
                        fontSize = 18.sp,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontFamily = FontFamily(
                                Font(R.font.regular, FontWeight.Normal)
                            )
                        )
                    )
                }
            }



            Button(
                onClick = {
                    isClicked = "Currency"
                    navController.navigate("currencyscreen")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(145.dp)
                    .padding(horizontal = 20.dp, vertical = 8.dp)
                    .border(
                        width = if (isClicked == "Currency") 1.dp else 0.dp,
                        color = if (isClicked == "Currency") ButEffect else Color.Transparent,
                        shape = RoundedCornerShape(20.dp)
                    ),
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(containerColor = ButtonColor)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.arrow_left),
                    contentDescription = "Coin Icon",
                    modifier = Modifier.size(32.dp),
                    tint = Color.Unspecified
                )
                Box(modifier = Modifier.fillMaxSize()) {
                    Row(
                        modifier = Modifier
                            .padding(top = 12.dp)
                            .fillMaxSize(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Text(
                            text = "دلار",
                            fontSize = 23.sp,
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontFamily = FontFamily(
                                    Font(R.font.regular, FontWeight.Normal)
                                )
                            )


                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(
                            painter = painterResource(id = R.drawable.coin_svgrepo_com),
                            contentDescription = "currency Icon",
                            modifier = Modifier.size(32.dp),
                            tint = Color.Unspecified
                        )
                    }
                    Text(
                        modifier = Modifier
                            .align(alignment = Alignment.BottomEnd)
                            .padding(bottom = 30.dp, end = 30.dp),
                        text = "قیمت لحظه ای دلار",
                        fontSize = 18.sp,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontFamily = FontFamily(
                                Font(R.font.regular, FontWeight.Normal)
                            ),


                            )
                    )
                }
            }



            Button(
                onClick = {
                    isClicked = "Crypto"
                    navController.navigate("cryptoscreen")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(145.dp)
                    .padding(horizontal = 20.dp, vertical = 8.dp)
                    .border(
                        width = if (isClicked == "Crypto") 1.dp else 0.dp,
                        color = if (isClicked == "Crypto") ButEffect else Color.Transparent,
                        shape = RoundedCornerShape(20.dp)
                    ),

                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(containerColor = ButtonColor)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.arrow_left),
                    contentDescription = "Coin Icon",
                    modifier = Modifier.size(32.dp),
                    tint = Color.Unspecified
                )
                Box(modifier = Modifier.fillMaxSize()) {
                    Row(
                        modifier = Modifier
                            .padding(top = 12.dp)
                            .fillMaxSize(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Text(
                            text = "ارز دیجیتال",
                            fontSize = 23.sp,
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontFamily = FontFamily(
                                    Font(R.font.regular, FontWeight.Normal)
                                )

                            )
                        )
                        Spacer(modifier = Modifier.width(8.dp))

                        Icon(
                            painter = painterResource(id = R.drawable.bitcoin_svgrepo_com__1_),
                            contentDescription = "btc",
                            modifier = Modifier.size(32.dp),
                            tint = Color.Unspecified
                        )
                    }
                    Text(
                        modifier = Modifier
                            .align(alignment = Alignment.BottomEnd)
                            .padding(bottom = 30.dp, end = 30.dp),
                        text = "قیمت لحظه ای ارز",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontFamily = FontFamily(
                                Font(R.font.regular, FontWeight.Normal)
                            ),

                            fontSize = 18.sp,

                            )
                    )
                }
            }
        }
    }
}

















