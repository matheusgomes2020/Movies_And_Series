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

object MoviesDetailsGraph2 {
    const val ROOT = "movies_details_graph2"
    const val DETAILS = "details2"
    const val CAST= "cast"
    const val SIMILAR = "similar"
}

object SearchSeriesGraph {
    const val ROOT = "search_series_graph"
    const val SEARCH_SERIES = "searchSeries"
}

object SearchMoviesGraph {
    const val ROOT = "search_movies_graph"
    const val SEARCH_MOVIES = "searchMovies"
}

object MoviesGenresGraph {
    const val ROOT = "movies_genres_graph"
    const val GENRE_MOVIES = "genreMovies"
}

object SeriesGenresGraph {
    const val ROOT = "series_genres_graph"
    const val GENRE_SERIES = "genreSeries"
}

object AccountDetailsGraph {
    const val ROOT = "account_details_graph"
    const val ACCOUNT_DETAILS = "accountDetails"
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
object CastGridGraph {
    const val ROOT = "cast_grid_graph"
    const val GRID = "cast_grid"
}

object MoviesGridGraph {
    const val ROOT = "movies_grid_graph"
    const val MOVIES_GRID = "movies_grid"
}

object SeriesGridGraph {
    const val ROOT = "series_grid_graph"
    const val SERIES_GRID = "series_grid"
}

object RootGraph {
    const val ROOT = "root_graph"
}

object SplashGraph {
    const val ROOT = "splash_graph"
    const val SPLASH = "splash"
}

object AppGraph {
    val initial = RootGraph
    val auth = AuthGraph
    val home = HomeGraph
    val splash = SplashGraph
    val movies_details = MoviesDetailsGraph
    val movies_details2 = MoviesDetailsGraph
    val series_details = SeriesDetailsGraph
    val search_movies = SearchMoviesGraph
    val search_series = SearchSeriesGraph
    val season_details = SeasonGraph
    val episode_details = EpisodeGraph
    val cast_details = CastGraph
    val image_cast_details = ImagePersonGraph
    val movie_genres = MoviesGenresGraph
    val series_genres = SeriesGenresGraph
    val account_details = AccountDetailsGraph
    val cast_grid = CastGridGraph
    val movies_grid = MoviesGridGraph
    val series_grid = SeriesGridGraph

}