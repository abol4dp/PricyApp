package com.example_app.pricyapp.ext

sealed class SealedScreenName(val Route: String) {
    data object Home : SealedScreenName("homescreen")
    data object Gold : SealedScreenName("goldscreen")
    data object Currency : SealedScreenName("currencyscreen")
    data object Crypto : SealedScreenName("cryptoscreen")


}