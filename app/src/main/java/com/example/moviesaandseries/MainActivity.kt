package com.example.moviesaandseries

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.moviesaandseries.presentation.Screen
import com.example.moviesaandseries.presentation.movie_detail.MovieDetailScreen
import com.example.moviesaandseries.presentation.movie_list.MovieListScreen
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
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.MovielistScreen.route
                    ) {
                        composable(
                            route = Screen.MovielistScreen.route
                        ) {
                            MovieListScreen(navController)

                        }
                        composable(
                            route = Screen.MovieDetailScreen.route + "/{movieId}"
                        ) {
                            MovieDetailScreen(navController)
                        }
                    }
                }
            }
        }
    }
}