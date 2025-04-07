package com.example_app.pricyapp


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

import com.example_app.pricyapp.mvvm.GoldViewModel
import com.example_app.pricyapp.retrofit.model.ContentModel


class MainActivity : ComponentActivity() {
    private val viewModel: GoldViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.fetchGoldData()

        setContent {
            GoldScreen(viewModel = viewModel)
        }
    }
}

@Composable
fun GoldScreen(viewModel: GoldViewModel) {
    val goldData by viewModel.goldData.observeAsState()
    val error by viewModel.errorMassage.observeAsState()

    if (goldData != null) {
        val golds = goldData?.data?.golds ?: emptyList()

        LazyColumn {
            items(golds) { gold ->
                goldItem(gold)
            }


        }

    }


}

@Composable
fun goldItem(gold: ContentModel) {
    Column {
        Text(text = gold.label, style = MaterialTheme.typography.bodyLarge)
        Text(text = "قیمت: ${gold.price} تومان", style = MaterialTheme.typography.bodySmall)

    }

}







