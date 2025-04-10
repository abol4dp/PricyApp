package com.example_app.pricyapp


import android.icu.util.Currency
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.compose.rememberNavController
import com.example_app.pricyapp.mvvm.GoldViewModel
import com.example_app.pricyapp.navcomponent.NavController
import com.example_app.pricyapp.retrofit.model.ContentModel
import com.example_app.pricyapp.retrofit.model.SharedViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        SharedViewModel.viewModel = GoldViewModel()

        setContent {
            val navController = rememberNavController()
            NavController(navController)


        }
    }
}








