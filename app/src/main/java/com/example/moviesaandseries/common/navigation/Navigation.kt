package com.example.moviesaandseries.common.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.moviesaandseries.presentation.cast.CastScreen
import com.example.moviesaandseries.presentation.cast.castImage.ImagePersonScreen
import com.example.moviesaandseries.presentation.episode.EpisodeScreen
import com.example.moviesaandseries.presentation.general.ScreenContent
import com.example.moviesaandseries.presentation.home.HomeScreen
import com.example.moviesaandseries.presentation.login.LoginContent
import com.example.moviesaandseries.presentation.movie_detail.MovieDetailScreen
import com.example.moviesaandseries.presentation.movie_list.MovieListScreen
import com.example.moviesaandseries.presentation.searchMovies.SearchMoviesScreen
import com.example.moviesaandseries.presentation.searchSeries.SearchSeriesScreen
import com.example.moviesaandseries.presentation.season.SeasonDetailScreen
import com.example.moviesaandseries.presentation.series_detail.SeriesDetailScreen
import com.example.moviesaandseries.presentation.series_list.SeriesListScreen
import com.example.moviesaandseries.presentation.signIn.ProfileScreen
import com.example.moviesaandseries.presentation.signIn.UserData

@Composable
fun RootNavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = AppGraph.initial.ROOT,
        startDestination = AppGraph.auth.ROOT
    ) {
        authNavGraph(navController = navController)
        composable(route = AppGraph.home.ROOT) {
           //HomeScreen()
        }
    }
}

fun NavGraphBuilder.authNavGraph(navController: NavHostController) {
    navigation(
        route = AppGraph.auth.ROOT,
        startDestination = AppGraph.auth.LOGIN
    ) {
        composable(route = AppGraph.auth.LOGIN) {
            LoginContent(
                onLoginClick = {
                    navController.popBackStack()
                    navController.navigate(AppGraph.home.ROOT)
                },
                onSignInClick = { navController.navigate(AppGraph.auth.SIGN_UP) },
                onForgotClick = { navController.navigate(AppGraph.auth.FORGOT_PASSWORD) }
            )
        }
        composable(route = AppGraph.auth.SIGN_UP) {
            ScreenContent(name = "SIGN UP") {}
        }
        composable(route = AppGraph.auth.FORGOT_PASSWORD) {
            ScreenContent(name = "FORGOT PASSWORD") {}
        }
    }
}

@Composable
fun HomeNavGraph(navController: NavHostController, userData: UserData?,
                 onSignOut: () -> Unit) {
    NavHost(
        navController = navController,
        route = AppGraph.home.ROOT,
        startDestination = AppGraph.home.MOVIES
    ) {
        composable(route = AppGraph.home.MOVIES) {
            MovieListScreen(navController = navController)
        }
        composable(route = AppGraph.home.SERIES) {
            SeriesListScreen(navController = navController)
        }
        composable(route = AppGraph.home.FAVORITES) {
            ProfileScreen(userData = userData, onSignOut = onSignOut)
        }
        movieDetailsNavGraph(navController = navController)
        seriesDetailsNavGraph(navController = navController)
        searchSeriesNavGraph( navController = navController )
        searchMoviesNavGraph( navController = navController )

    }
}

fun NavGraphBuilder.searchMoviesNavGraph( navController: NavController ) {
    navigation(
        route = AppGraph.search_movies.ROOT,
        startDestination = AppGraph.search_movies.SEARCH_MOVIES
    ) {
        composable(route = AppGraph.search_movies.SEARCH_MOVIES + "/{queryMovie}",
            arguments = listOf(
                navArgument( "queryMovie" ) {
                    type = NavType.StringType
                }
            )
        ) { navBackStackEntry ->

            navBackStackEntry.arguments?.getString("queryMovie").let {
                Log.d("BATATAO", "searchMovies: ${navBackStackEntry.destination}")
                SearchMoviesScreen( it!!, navController = navController )
            }
        }
    }
}

fun NavGraphBuilder.searchSeriesNavGraph( navController: NavController ) {
    navigation(
        route = AppGraph.search_series.ROOT,
        startDestination = AppGraph.search_series.SEARCH_SERIES
    ) {
        composable(route = AppGraph.search_series.SEARCH_SERIES + "/{querySeries}",
            arguments = listOf(
                navArgument("querySeries") {
                    type = NavType.StringType
                }
            )
        ) { navBackStackEntry ->

            navBackStackEntry.arguments?.getString("querySeries").let {
                Log.d("BATATAO", "searchSeries: ${navBackStackEntry.destination}")
                SearchSeriesScreen(it!!, navController = navController)
            }
        }
    }
}


fun NavGraphBuilder.movieDetailsNavGraph(navController: NavController){
    navigation(
        route = AppGraph.movies_details.ROOT,
        startDestination = AppGraph.movies_details.DETAILS
    ) {
        composable(route = AppGraph.movies_details.DETAILS + "/{movieId}",
            arguments = listOf(
                navArgument( "movieId" ) {
                    type = NavType.StringType
                }
            )
        ) { navBackStackEntry ->

            navBackStackEntry.arguments?.getString("movieId").let {
                Log.d("TRT", "movieDetailsNavGraph2: ${navBackStackEntry.destination}")
                MovieDetailScreen(navController = navController)
            }
        }
    }
}

fun NavGraphBuilder.seriesDetailsNavGraph(navController: NavController){
    navigation(
        route = AppGraph.series_details.ROOT,
        startDestination = AppGraph.series_details.DETAILS
    ) {
        composable(route = AppGraph.series_details.DETAILS + "/{seriesId}",
            arguments = listOf(
                navArgument( "seriesId" ) {
                    type = NavType.StringType
                }
            )
        ) { navBackStackEntry ->

            navBackStackEntry.arguments?.getString("seriesId").let {
                Log.d("TRT", "serieDetailsNavGraph2: ${navBackStackEntry.destination}")
                SeriesDetailScreen(navController = navController)
            }
        }
        seasonDetailsNavGraph( navController = navController )
        castDetailNavGraph(navController = navController)
    }
}

fun NavGraphBuilder.castDetailNavGraph(navController: NavController) {
    navigation(
        route = AppGraph.cast_details.ROOT,
        startDestination = AppGraph.cast_details.DETAILS
    ) {
        composable(route = AppGraph.cast_details.DETAILS + "/{castId}",
            arguments = listOf(
                navArgument( "castId" ) {
                    type = NavType.StringType
                }
            )
        ) { navBackStackEntry ->

            navBackStackEntry.arguments?.getString("castId").let {
                CastScreen(navController = navController)
            }
        }
    }

    imagePersonNavGraph( navController )
}

fun NavGraphBuilder.imagePersonNavGraph( navController: NavController ) {
    navigation(
        route = AppGraph.image_cast_details.ROOT,
        startDestination = AppGraph.image_cast_details.DETAILS
    ) {
        composable(route = AppGraph.image_cast_details.DETAILS + "/{imagePath}",
            arguments = listOf(
                navArgument("imagePath") {
                    type = NavType.StringType
                }
            )
        ) { navBackStackEntry ->

            navBackStackEntry.arguments?.getString("imagePath").let {
                ImagePersonScreen(it!!,navController)
                Log.d("IEIEIE", "imagePersonNavGraph: $it")
            }
        }
    }
}

fun NavGraphBuilder.seasonDetailsNavGraph(navController: NavController){
    navigation(
        route = AppGraph.season_details.ROOT,
        startDestination = AppGraph.season_details.DETAILS
    ) {
        composable( route = AppGraph.season_details.DETAILS + "/{seriesId}/{seasonNumber}",
            arguments = listOf(
                navArgument("seriesId") {
                    type = NavType.StringType
                },
                navArgument("seasonNumber") {
                    type = NavType.StringType
                }
            )
        ) { navBackStackEntry ->
            navBackStackEntry.arguments?.getString("seriesId").let {
                SeasonDetailScreen(  it!!, navController = navController)
            }
        }
        episodeDetailsNavGraph(navController = navController)
    }
}

fun NavGraphBuilder.episodeDetailsNavGraph(navController: NavController){
    navigation(
        route = AppGraph.episode_details.ROOT,
        startDestination = AppGraph.episode_details.DETAILS
    ) {
        composable( route = AppGraph.episode_details.DETAILS + "/{seriesId}/{seasonNumber}/{episodeNumber}",
            arguments = listOf(
                navArgument("seriesId") {
                    type = NavType.StringType
                },
                navArgument("seasonNumber") {
                    type = NavType.StringType
                },
                        navArgument("episodeNumber") {
                    type = NavType.StringType
                }
            )
        ) { navBackStackEntry ->
            navBackStackEntry.arguments?.getString("episodeNumber").let {
                EpisodeScreen(navController = navController)
            }
        }
    }}

