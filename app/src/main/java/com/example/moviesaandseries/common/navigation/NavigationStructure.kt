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

object PopularMoviesGraph {
    const val ROOT = "popular_movies_graph"
    const val POPULAR_MOVIES = "popularMovies"
}

object RatedMoviesGraph {
    const val ROOT = "rated_movies_graph"
    const val RATED_MOVIES = "ratedMovies"
}

object UpcomingMoviesGraph {
    const val ROOT = "upcoming_movies_graph"
    const val UPCOMING_MOVIES = "upcomingMovies"
}

object NowPlayingMoviesGraph {
    const val ROOT = "nowPlaying_movies_graph"
    const val NOW_PLAYING_MOVIES = "nowPlayingMovies"
}

object TrendingTodayMoviesGraph {
    const val ROOT = "trending_Today_movies_graph"
    const val TRENDING_TODAY_MOVIES = "trendingTodayMovies"
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
    val series_details = SeriesDetailsGraph
    val search_movies = SearchMoviesGraph
    val search_series = SearchSeriesGraph
    val season_details = SeasonGraph
    val episode_details = EpisodeGraph
    val cast_details = CastGraph
    val image_cast_details = ImagePersonGraph
    val movie_genres = MoviesGenresGraph
    val popular_movies = PopularMoviesGraph
    val rated_movies = RatedMoviesGraph
    val upcoming_movies = UpcomingMoviesGraph
    val now_Playing_movies = NowPlayingMoviesGraph
    val trending_today_movies = TrendingTodayMoviesGraph

}