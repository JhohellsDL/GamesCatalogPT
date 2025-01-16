package com.example.gamescatalogpt.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.gamescatalogpt.navigation.AppNavHost
import com.example.gamescatalogpt.ui.theme.GamesCatalogPTTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GamesCatalogPTTheme {
                AppNavHost()
            }
        }
    }
}