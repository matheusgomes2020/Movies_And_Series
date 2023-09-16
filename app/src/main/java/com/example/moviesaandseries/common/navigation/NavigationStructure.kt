package com.example.moviesaandseries.common.navigation

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
    const val CAST= CastGraph.DETAILS
     //val CAST = CastGraph
    const val SEASON = "season"
    const val SIMILAR = "similar"
}

object MoviesDetailsGraph {
    const val ROOT = "movies_details_graph"
    const val DETAILS = "details"
    const val CAST= "cast"
    const val SIMILAR = "similar"
}

object SearchMoviesGraph {
    const val ROOT = "search_movies_graph"
    const val DETAILS = "search"
}

object DetailsGraph {
    const val ROOT = "details_graph"
    const val HELP= "details"
    const val FAQ = "cast"
    const val DISCLAIMER = "disclaimer"
}

object SeasonGraph {
    const val ROOT = "season_details_graph"
    const val DETAILS = "details"
    const val EPISODE = "episode"
    const val CAST= "cast"
}

object ImagePersonGraph {
    const val ROOT = "image_person_graph"
    const val DETAILS = "image_details"
}

object EpisodeGraph {
    const val ROOT = "episode_details_graph"
    const val DETAILS = "episode_details"
    const val CAST= "cast"
}

object CastGraph {
    const val ROOT = "cast_details_graph"
    const val DETAILS = "cast_details"
    const val MOVIES = "movies"
    const val SERIES = "series"
}


object RootGraph {
    const val ROOT = "root_graph"
}

object AppGraph {
    val auth = AuthGraph
    val series_details = SeriesDetailsGraph
    val movies_details = MoviesDetailsGraph
    val season_details = SeasonGraph
    val episode_details = EpisodeGraph
    val cast_details = CastGraph
    val image_cast_details = ImagePersonGraph
    val search_movies = SearchMoviesGraph
    val home = HomeGraph
    val initial = RootGraph
}