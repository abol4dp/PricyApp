package com.example_app.pricyapp.ui.theme.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import com.example_app.pricyapp.ui.theme.mainBackColor

class SplashScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SpScreen()
        }


        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 2000)
    }
}

@Composable
fun SpScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(mainBackColor)
    ) {
        Text(
            text = "Test",
            color = White,
            modifier = Modifier.align(Alignment.Center)

        )
    }
}

