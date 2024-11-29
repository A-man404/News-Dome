package com.example.newsdomemain.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.newsdomemain.R
import com.example.newsdomemain.viewmodel.MainViewModel


@Composable
fun MainScreen() {


    val viewModel: MainViewModel = viewModel()
    val state by viewModel.dataState

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        when {
            state.loading -> {
                //CircularProgressIndicator()
                SplashScreen()
            }

            state.error != null -> {
                Text("Either we have some problem or check your internet connection :)")
            }

            else -> {
                state.list?.let { HomePage(it.articles) }

            }


        }
    }
}

@Composable
fun SplashScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AsyncImage(
            model = R.drawable.splash_icon,
            contentDescription = null
        )
    }
}
