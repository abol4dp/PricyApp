package com.example_app.pricyapp


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example_app.pricyapp.mvvm.GoldViewModel

import com.example_app.pricyapp.navcomponent.NavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        setContent {
            val viewModel: GoldViewModel = hiltViewModel()
            val navController = rememberNavController()
            NavController(navController, viewModel)


        }
    }
}








