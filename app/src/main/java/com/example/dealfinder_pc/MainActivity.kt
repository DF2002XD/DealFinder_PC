package com.example.dealfinder_pc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.dealfinder_pc.ui.theme.DealFinder_PCTheme

import com.example.dealfinder_pc.vista.Navegacion.AppNavigation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DealFinder_PCTheme {
                AppNavigation() // Llamamos al cerebro de la app
            }
        }
    }
}

// BORRADO GREETING