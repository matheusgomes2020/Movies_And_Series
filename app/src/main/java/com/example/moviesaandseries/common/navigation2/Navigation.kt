package com.example.moviesaandseries.common.navigation2

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.moviesaandseries.common.navigation.seriesDetailsNavGraph
import com.example.moviesaandseries.presentation.cast.CastScreen
import com.example.moviesaandseries.presentation.general.ScreenContent
import com.example.moviesaandseries.presentation.general.ScreenContentSeason
import com.example.moviesaandseries.presentation.general.ScreenContentSeries
import com.example.moviesaandseries.presentation.home.HomeScreen
import com.example.moviesaandseries.presentation.login.LoginContent
import com.example.moviesaandseries.presentation.movie_detail.MovieDetailScreen
import com.example.moviesaandseries.presentation.movie_list.MovieListScreen
import com.example.moviesaandseries.presentation.season.SeasonDetailScreen
import com.example.moviesaandseries.presentation.series_detail.SeriesDetailScreen
import com.example.moviesaandseries.presentation.series_list.SeriesListScreen

@Composable
fun RootNavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = AppGraph.initial.ROOT,
        startDestination = AppGraph.auth.ROOT
    ) {
        authNavGraph2(navController = navController)
        composable(route = AppGraph.home.ROOT) {
           HomeScreen()
        }
    }
}

fun NavGraphBuilder.authNavGraph2(navController: NavHostController) {
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
fun HomeNavGraph2(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = AppGraph.home.ROOT,
        startDestination = AppGraph.home.MOVIES
    ) {
        composable(route = AppGraph.home.MOVIES) {
            MovieListScreen(navController = navController)
            //ScreenContent(name = "Lista Movies") {
               // navController.navigate(AppGraph.movies_details.ROOT)
           // }
        }
        composable(route = AppGraph.home.SERIES) {
            SeriesListScreen(navController = navController)
        }
        composable(route = AppGraph.home.FAVORITES) {
            ScreenContent(name = "Lista Favoritos") {
                navController.navigate(AppGraph.series_details.ROOT)
            }
        }
        movieDetailsNavGraph2(navController = navController)
        seriesDetailsNavGraph2(navController = navController)
    }
}

fun NavGraphBuilder.movieDetailsNavGraph2(navController: NavController){
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
                MovieDetailScreen(navController = navController)
            }
        }
        castDetailNavGraph(navController = navController)
    }
}

fun NavGraphBuilder.seriesDetailsNavGraph2(navController: NavController){
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
                SeasonDetailScreen(navController = navController)
            }
        }
    }
}

