package com.example.moviesaandseries.presentation

sealed class Screen(val route: String) {

    object MovieListScreen: Screen("movie_list")

    object MovieDetailScreen: Screen("movie_detail_screen")

    object SeriesListScreen: Screen("series_list")

    object SeriesDetailScreen: Screen("series_detail_screen")

}
