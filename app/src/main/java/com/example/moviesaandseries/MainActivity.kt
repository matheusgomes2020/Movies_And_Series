package com.example.moviesaandseries

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.moviesaandseries.common.navigation.RootNavigationGraph
import com.example.moviesaandseries.ui.theme.MoviesAandSeriesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoviesAandSeriesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //RootNavigationGraph(navController = rememberNavController() )
                    com.example.moviesaandseries.common.navigation2.RootNavigationGraph(
                        navController = rememberNavController()
                    )
                }
            }
        }
    }
}

