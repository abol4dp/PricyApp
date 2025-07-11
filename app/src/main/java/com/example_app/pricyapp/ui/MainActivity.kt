package com.example_app.pricyapp.ui


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.graphics.toArgb
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example_app.pricyapp.mvvm.ViewModel

import com.example_app.pricyapp.navcomponent.NavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        setContent {
            window.navigationBarColor = androidx.compose.ui.graphics.Color.Black.toArgb()
            window.statusBarColor = androidx.compose.ui.graphics.Color.Black.toArgb()
            val viewModel: ViewModel = hiltViewModel()
            val navController = rememberNavController()
            NavController(navController, viewModel)
        }
    }
}








