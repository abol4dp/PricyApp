package com.example_app.pricyapp.ui.theme.ui


import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.graphics.toArgb
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example_app.pricyapp.mvvm.GoldViewModel

import com.example_app.pricyapp.navcomponent.NavController
import com.example_app.pricyapp.ui.theme.mainBackColor
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        setContent {
            window.navigationBarColor = androidx.compose.ui.graphics.Color.Black.toArgb()
            window.statusBarColor = androidx.compose.ui.graphics.Color.Black.toArgb()
            val viewModel: GoldViewModel = hiltViewModel()
            val navController = rememberNavController()
            NavController(navController, viewModel)
        }
    }
}








