package com.example_app.pricyapp.navcomponent


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example_app.pricyapp.NavScreen.CryptoScreen
import com.example_app.pricyapp.NavScreen.CurrencyScreen
import com.example_app.pricyapp.NavScreen.GoldScreen
import com.example_app.pricyapp.NavScreen.HomeScreen
import com.example_app.pricyapp.ext.SealedScreenName

@Composable
fun NavController(navController: NavHostController, viewModel: _root_ide_package_.com.example_app.pricyapp.mvvm.ViewModel) {


    NavHost(navController = navController, startDestination = SealedScreenName.Home.Route) {
        composable(SealedScreenName.Home.Route) { HomeScreen(navController, viewModel) }
        composable(SealedScreenName.Gold.Route) { GoldScreen(navController) }
        composable(SealedScreenName.Currency.Route) { CurrencyScreen(navController) }
        composable(SealedScreenName.Crypto.Route) { CryptoScreen(navController) }


    }

}