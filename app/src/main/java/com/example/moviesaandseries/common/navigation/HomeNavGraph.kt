package com.example.moviesaandseries.common.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.moviesaandseries.presentation.general.ScreenContent
import com.example.moviesaandseries.presentation.home.BottomBarScreen
import com.example.moviesaandseries.presentation.movie_detail.MovieDetailScreen
import com.example.moviesaandseries.presentation.movie_list.MovieListScreen
import com.example.moviesaandseries.presentation.series_detail.SeriesDetailScreen
import com.example.moviesaandseries.presentation.series_list.SeriesListScreen

@Composable
fun HomeNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.HOME,
        startDestination = BottomBarScreen.Movies.route
    ) {
        composable(route = BottomBarScreen.Movies.route) {
            MovieListScreen(navController = navController)
        }
        composable(route = BottomBarScreen.Series.route) {
            (
                SeriesListScreen(navController = navController)
            )
            
        }
        composable(route = BottomBarScreen.Favorites.route) {
            SeriesListScreen(navController = navController)
        }
        movieDetailsNavGraph(navController = navController)
        seriesDetailsNavGraph(navController = navController)
    }
}

fun NavGraphBuilder.seriesDetailsNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.SERIES_DETAILS,
        startDestination = DetailsScreen.SeriesDetails.route
    ) {
        composable(route = DetailsScreen.SeriesDetails.route + "/{seriesId}",
            arguments = listOf(
                navArgument( "seriesId") {
                    type = NavType.StringType
                }
            )
        ) { navBackStackEntry ->

            navBackStackEntry.arguments?.getString("seriesId").let {
                SeriesDetailScreen(navController = navController )
            }

        }
        composable(route = DetailsScreen.Cast.route) {
            ScreenContent(name = DetailsScreen.Cast.route) {
                navController.popBackStack(
                    route = DetailsScreen.MoviesDetails.route,
                    inclusive = false
                )
            }
        }
    }
}

fun NavGraphBuilder.movieDetailsNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.MOVIE_DETAILS,
        startDestination = DetailsScreen.MoviesDetails.route
    ) {
        composable(route = DetailsScreen.MoviesDetails.route + "/{movieId}",
            arguments = listOf(
                navArgument( "movieId") {
                    type = NavType.StringType
                }
            )
        ) { navBackStackEntry ->

navBackStackEntry.arguments?.getString("movieId").let {
    MovieDetailScreen(navController = navController )
}

        }
        composable(route = DetailsScreen.Cast.route) {
            ScreenContent(name = DetailsScreen.Cast.route) {
                navController.popBackStack(
                    route = DetailsScreen.MoviesDetails.route,
                    inclusive = false
                )
            }
        }
    }
}

sealed class DetailsScreen(val route: String) {

    object SeriesDetails : DetailsScreen(route = "series_detail_screen")
    object MoviesDetails : DetailsScreen(route = "movie_detail_screen")

    object Cast : DetailsScreen(route = "CAST")
}
