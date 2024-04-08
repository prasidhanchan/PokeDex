package com.pika.pokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.pika.pokedex.navigation.PokeDexNavigation
import com.pika.pokedex.presentation.ui.theme.PokeDexTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.auto(
                lightScrim = Color.White.toArgb(),
                darkScrim = Color.Black.toArgb()
            ),
            navigationBarStyle = SystemBarStyle.auto(
                lightScrim = Color.White.toArgb(),
                darkScrim = Color.Black.toArgb()
            )
        )
        setContent {
            PokeDexTheme {
                PokeDexNavigation()
            }
        }
    }
}