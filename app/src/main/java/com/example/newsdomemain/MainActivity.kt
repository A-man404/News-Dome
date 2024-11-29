package com.example.newsdomemain

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.newsdomemain.screens.MainScreen
import com.example.newsdomemain.ui.theme.YourAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            YourAppTheme {
                MainScreen()
            }
        }
    }
}


