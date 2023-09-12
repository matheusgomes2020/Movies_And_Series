package com.example.moviesaandseries.common.navigation2

object AuthGraph {
    const val ROOT = "auth_graph"
    const val LOGIN = "login"
    const val SIGN_UP = "sign_up"
    const val FORGOT_PASSWORD = "forgot_password"
}

object HomeGraph {
    const val ROOT = "home_graph"
    const val MOVIES = "movies"
    const val SERIES = "series"
    const val FAVORITES = "favorites"
}

object SeriesDetailsGraph {
    const val ROOT = "series_details_graph"
    const val DETAILS = "series_details"
    const val CAST= "cast"
    const val SEASON = "season"
    const val SIMILAR = "similar"
}

object MoviesDetailsGraph {
    const val ROOT = "movies_details_graph"
    const val DETAILS = "details"
    const val CAST= "cast"
    const val SIMILAR = "similar"
}

object SeasonGraph {
    const val ROOT = "season_details_graph"
    const val DETAILS = "details"
    const val EPISODE = "episode"
    const val CAST= "cast"
}

object RootGraph {
    const val ROOT = "root_graph"
}

object AppGraph {
    val auth = AuthGraph
    val series_details = SeriesDetailsGraph
    val movies_details = MoviesDetailsGraph
    val season_details = SeasonGraph
    val home = HomeGraph
    val initial = RootGraph
}