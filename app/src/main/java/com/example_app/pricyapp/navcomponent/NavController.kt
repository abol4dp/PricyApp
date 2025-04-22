package com.example_app.pricyapp.navcomponent

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example_app.pricyapp.NavScreen.CryptoScreen
import com.example_app.pricyapp.NavScreen.GoldScreen
import com.example_app.pricyapp.NavScreen.HomeScreen
import com.example_app.pricyapp.NavScreen.CurrencyScreen
import com.example_app.pricyapp.ext.SealedScreenName
import com.example_app.pricyapp.mvvm.GoldViewModel

@Composable
fun NavController(navController: NavHostController, viewModel: GoldViewModel) {
    NavHost(navController = navController, startDestination = SealedScreenName.Home.Route) {
        composable(SealedScreenName.Home.Route) { HomeScreen(navController) }
        composable(SealedScreenName.Gold.Route) { GoldScreen(navController) }
        composable(SealedScreenName.Currency.Route) { CurrencyScreen(navController) }
        composable(SealedScreenName.Crypto.Route) { CryptoScreen(navController) }


    }

}