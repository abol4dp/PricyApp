package com.example_app.pricyapp.NavScreen

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example_app.pricyapp.R
import com.example_app.pricyapp.mvvm.GoldViewModel
import com.example_app.pricyapp.ui.theme.mainBackColor
import com.example_app.pricyapp.ui.theme.smallfontcolor


@Composable

fun GoldScreen(navController: NavHostController) {

    val viewModel: GoldViewModel = hiltViewModel()
    val goldData by viewModel.goldData.collectAsState()
    val error by viewModel.errorMessage.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchGoldData()

    }




    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(mainBackColor)
            .padding(horizontal = 30.dp),
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.Center


    ) {


        if (goldData !== null) {
            val gold = goldData?.data?.golds ?: emptyList()


            LazyColumn {


                items(gold) { golds ->


                    Column(

                        modifier = Modifier
                            .padding(top = 30.dp)
                            .padding(horizontal = 5.dp),
                    ) {


                        Text(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(end = 10.dp), textAlign = TextAlign.End,
                            text = golds.label,
                            style = MaterialTheme.typography.bodyLarge,
                            color = smallfontcolor, fontSize = 15.sp

                        )

                        Spacer(modifier = Modifier.height(3.dp))

                        Text(
                            modifier = Modifier.fillMaxSize(),
                            textAlign = TextAlign.End,
                            text = " ${golds.price} تومان",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.White, fontSize = 30.sp
                        )
                    }
                }
            }
        } else {
            if (!error.isNullOrEmpty()) {
                Text(
                    text = "خطا: $error",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.White, fontSize = 15.sp
                )


            } else
                CircularProgressIndicator()
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
            text = "طلا و ارز",
            style = MaterialTheme.typography.labelMedium,
            color = Color.White, fontSize = 35.sp
        )


    }


}

