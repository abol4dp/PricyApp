package com.example_app.pricyapp.NavScreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.SegmentedButtonDefaults.borderStroke
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.android.material.shape.CornerTreatment

@Composable
fun HomeScreen(navController: NavController) {
    CustomButton(navController)
    // Box(
    //   modifier = Modifier.fillMaxWidth(),
    // contentAlignment = Alignment.Center
    //) {
    //    Row(
    // horizontalArrangement = Arrangement.spacedBy(20.dp),
    // verticalAlignment = Alignment.CenterVertically
    //) {
    //  Button(onClick = { navController.navigate("currencyscreen") }) {
    //// Text("دلار و ارز")
    //   }
    // Button(onClick = { navController.navigate("goldscreen") }) {
    //   Text("طلا و سکه")
    //  }

    // Button(onClick = { navController.navigate("cryptoscreen") }) {
    ///     Text("ارز دیجیتال")
    //  }


}


@Composable
fun CustomButton(navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column {
            Button(onClick = { navController.navigate("currencyscreen") },
                modifier = Modifier
                    .size(width = 370.dp, height = 80.dp)
                    .padding(bottom = 10.dp), shape = RoundedCornerShape(15.dp)
            ) {
                Text("دلار ")
            }



            Button(
                onClick = { navController.navigate("goldscreen") },
                modifier = Modifier.size(width = 370.dp, height = 80.dp)
                    .padding(bottom = 10.dp )
                ,
                shape = RoundedCornerShape(15.dp)

            ) {

                Text("طلا و سکه")

            }

            Button(onClick = {navController.navigate("cryptoscreen")},
                modifier = Modifier.size(width = 370.dp  , height = 80.dp)
                , shape = RoundedCornerShape(15.dp)
                ) {

               Text("ارز دیجیتال")

            }



        }


    }


}


