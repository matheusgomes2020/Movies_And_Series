package com.example.moviesaandseries.presentation

sealed class Screen(val route: String) {
    object MovielistScreen: Screen("movie_list")
    object MovieDetailScreen: Screen("movie_detail_screen")
}
