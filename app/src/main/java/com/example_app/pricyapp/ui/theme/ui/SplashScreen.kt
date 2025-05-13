package com.example_app.pricyapp.ui.theme.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.ViewGroup
import android.widget.VideoView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.example_app.pricyapp.R
import com.example_app.pricyapp.ui.theme.mainBackColor

class SplashScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.navigationBarColor = Color.Black.toArgb()
        window.statusBarColor = Color.Black.toArgb()
        setContent {
            SpScreen()
        }
    }


    @Composable
    fun SpScreen() {
        val context = LocalContext.current
        val videoView = remember {
            VideoView(context).apply {

                setVideoURI(Uri.parse("android.resource://${context.packageName}/${R.raw.app2}"))
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                setOnPreparedListener { _ ->
                    scaleX = 1.2f
                    scaleY = 1.2f
                    start()
                }
                setOnCompletionListener {

                    val intent = Intent(context, MainActivity::class.java)
                    context.startActivity(intent)
                    (context as SplashScreen).finish()
                }
            }
        }
        AndroidView(
            factory = {videoView},
            modifier =Modifier.
            fillMaxSize()
                .background(Color.Black)
        )


    }

}

