package com.example.moviesaandseries.common.navigation2

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.moviesaandseries.common.navigation.seriesDetailsNavGraph
import com.example.moviesaandseries.presentation.general.ScreenContent
import com.example.moviesaandseries.presentation.general.ScreenContentSeason
import com.example.moviesaandseries.presentation.general.ScreenContentSeries
import com.example.moviesaandseries.presentation.home.HomeScreen
import com.example.moviesaandseries.presentation.login.LoginContent
import com.example.moviesaandseries.presentation.movie_list.MovieListScreen
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
            //MovieListScreen(navController = navController)
            ScreenContent(name = "Lista Movies") {
                navController.navigate(AppGraph.movies_details.ROOT)
            }
        }
        composable(route = AppGraph.home.SERIES) {
            //SeriesListScreen(navController = navController)
            ScreenContent(name = "Lista Séries") {
               navController.navigate(AppGraph.series_details.ROOT)
            }
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
        composable(route = AppGraph.movies_details.DETAILS) {
            ScreenContent(name = "Movie Details") {
                navController.navigate(AppGraph.movies_details.CAST)
            }
        }
    }
}

fun NavGraphBuilder.seriesDetailsNavGraph2(navController: NavController){
    navigation(
        route = AppGraph.series_details.ROOT,
        startDestination = AppGraph.series_details.DETAILS
    ) {
        composable(route = AppGraph.series_details.DETAILS) {
            ScreenContentSeries(
                name = "Séries Details",
                onClick = { navController.navigate(AppGraph.series_details.CAST) },
                onClick2 = { navController.navigate(AppGraph.series_details.SIMILAR) },
                onClick3 = { navController.navigate(AppGraph.series_details.SEASON)} )
        }
        composable(route = AppGraph.series_details.CAST) {
            ScreenContent(name = "Cast") {}
        }
        composable(route = AppGraph.series_details.SIMILAR) {
            ScreenContent(name = "Similar") {}
        }
        composable(route = AppGraph.series_details.SEASON) {
            ScreenContent(name = "Season 435454") {
                navController.navigate( AppGraph.season_details.ROOT )
            }
        }
        seasonDetailsNavGraph( navController = navController )
    }
}

fun NavGraphBuilder.seasonDetailsNavGraph(navController: NavController){
    navigation(
        route = AppGraph.season_details.ROOT,
        startDestination = AppGraph.season_details.DETAILS
    ) {
        composable( route = AppGraph.season_details.DETAILS ) {
            ScreenContentSeason(name = "4343434343") {
                navController.navigate(AppGraph.season_details.EPISODE)
            }
        }
    }
}

