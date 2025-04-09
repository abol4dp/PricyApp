package com.example_app.pricyapp.navcomponent

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example_app.pricyapp.NavScreen.GoldScreen
import com.example_app.pricyapp.NavScreen.HomeScreen
import com.example_app.pricyapp.NavScreen.CurrencyScreen

@Composable
fun NavController (navController: NavHostController){
  NavHost(navController = navController, startDestination = "homescreen"){
        composable("homescreen") { HomeScreen(navController) }
        composable("goldscreen") { GoldScreen(navController) }
        composable("currencyscreen") { CurrencyScreen(navController) }

    }

}