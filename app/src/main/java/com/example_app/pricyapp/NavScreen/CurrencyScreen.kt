package com.example_app.pricyapp.NavScreen

import android.icu.text.DecimalFormat
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example_app.pricyapp.R
import com.example_app.pricyapp.ext.ShimmerLoader
import com.example_app.pricyapp.ui.theme.mainBackColor
import com.example_app.pricyapp.ui.theme.smallfontcolor
import kotlinx.coroutines.delay


@Composable
fun CurrencyScreen(navController: NavController) {
    val viewModel: _root_ide_package_.com.example_app.pricyapp.mvvm.ViewModel = hiltViewModel()
    val currencyData by viewModel.currencyData.collectAsState()
    val error by viewModel.errorMessage.collectAsState()
    val shimmerLoader = remember { ShimmerLoader() }

    LaunchedEffect(Unit) {
        while (true) {
            shimmerLoader.startLoading()
            viewModel.fetchGoldData()
            delay(1500)
            shimmerLoader.stopLoading()
            delay(40000)
        }
    }

    Column(
        modifier = Modifier

            .fillMaxSize()
            .background(mainBackColor)
            .padding(horizontal = 30.dp),
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.Center


    ) {


        if (currencyData != null) {
            val currency = currencyData?.data?.currencies ?: emptyList()

            LazyColumn(
                modifier = Modifier.padding(bottom = 120.dp)


            ) {

                items(currency) { currency ->
                    shimmerLoader.ShimmerListItem(
                        contentAfterLoading = {


                            Column(
                                modifier = Modifier
                                    .padding(top = 30.dp)
                                    .padding(horizontal = 5.dp),
                            ) {
                                Text(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(end = 10.dp), textAlign = TextAlign.End,
                                    text = currency.label,
                                    style = MaterialTheme.typography.bodyLarge.copy(
                                        fontFamily = FontFamily(
                                            Font(R.font.thin, FontWeight.Normal)
                                        )
                                    ),
                                    color = smallfontcolor, fontSize = 15.sp

                                )
                                Spacer(modifier = Modifier.height(3.dp))

                                val priceLong = try {
                                    currency.price.toLong()
                                } catch (e: NumberFormatException) {
                                    0L
                                }
                                val shortPrice = priceLong / 10
                                val formattedPrice = DecimalFormat("#,###").format(shortPrice)




                                Text(
                                    modifier = Modifier.fillMaxSize(),
                                    textAlign = TextAlign.End,
                                    text = " $formattedPrice تومان ",
                                    style = MaterialTheme.typography.bodyLarge.copy(
                                        fontFamily = FontFamily(
                                            Font(R.font.regular, FontWeight.Normal)
                                        )
                                    ),
                                    color = Color.White, fontSize = 30.sp
                                )
                            }

                        }

                    )
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
                ShimmerLoader()

        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 20.dp, top = 30.dp)


    ) {

        Icon(
            painter = painterResource(id = R.drawable.chevron_left),
            contentDescription = "Coin Icon",
            modifier = Modifier
                .size(35.dp)
                .clickable {
                    navController.popBackStack()


                },
            tint = Color.Unspecified

        )


    }


    Column(
        modifier = Modifier.fillMaxSize(), Arrangement.Top, Alignment.End

    ) {
        Text(
            modifier = Modifier.padding(top = 30.dp, end = 35.dp),
            text = "دلار و ارز",
            style = MaterialTheme.typography.bodyLarge.copy(
                fontFamily = FontFamily(
                    Font(R.font.regular, FontWeight.Normal)
                )
            ),
            color = Color.White, fontSize = 35.sp
        )


    }


}









