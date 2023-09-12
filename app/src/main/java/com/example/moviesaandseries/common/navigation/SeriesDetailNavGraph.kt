package com.example.moviesaandseries.common.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.moviesaandseries.presentation.general.ScreenContent

fun NavGraphBuilder.seriesDetailsNavGraph(navController: NavController) {
    navigation(
        route = Graph.SERIES_DETAILS,
        startDestination = SeriesDetailScreen.Details.route
    ){
        composable(route = SeriesDetailScreen.Details.route){

        }

        composable(route = SeriesDetailScreen.Cast.route ) {

        }

        composable(route = SeriesDetailScreen.Seasons.route ) {

        }

        composable(route = SeriesDetailScreen.Similar.route ) {

        }
    }
}



sealed class SeriesDetailScreen(val route: String) {
    object Details : SeriesDetailScreen(route = "series_detail_screen")
    object Cast : SeriesDetailScreen(route = "cast_screen")
    object Similar : SeriesDetailScreen(route = "similar_screen")
    object Seasons : SeriesDetailScreen(route = "seasons_detail_screen")
}
