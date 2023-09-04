package com.example.moviesaandseries.common.navigation

sealed class Screen(val route: String) {

    object MovieListScreen: Screen("movie_list")

    object MovieDetailScreen: Screen("movie_detail_screen")

    object SeriesListScreen: Screen("series_list")

    object SeriesDetailScreen: Screen("series_detail_screen")
    object Movies: Screen("movies")
    object Series: Screen("series")
    object Favorites: Screen("favorites")

}
