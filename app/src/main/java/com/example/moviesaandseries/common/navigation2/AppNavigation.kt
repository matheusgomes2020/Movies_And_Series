package com.example.moviesaandseries.common.navigation2

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.moviesaandseries.presentation.general.ScreenContent
import com.example.moviesaandseries.presentation.login.LoginContent2
import com.example.moviesaandseries.presentation.movie_list.MoviesScreenNewUI
import com.example.moviesaandseries.presentation.series_list.SeriesScreenNewUI
import com.example.moviesaandseries.presentation.splash.AnimatedSplashScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController,
        startDestination = Screens.SplashScreen.name) {

        composable(Screens.SplashScreen.name) {
            AnimatedSplashScreen(navController = navController)
        }

        composable(Screens.LoginScreen.name) {
            LoginContent2( navController = navController )
        }

        composable(Screens.SignInScreen.name) {
            ScreenContent(name = "Sign Up") {
            }
        }

        composable(Screens.ForgotScreen.name) {
            ScreenContent(name = "Forgot") {
            }
        }

        composable(Screens.HomeScreen.name) {
//            HomeScreen(
//
//            )
        }



    }
}


@Composable
fun HomeNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController,
        startDestination = Screens.MoviesScreen.name) {

        composable(Screens.MoviesScreen.name) {
            //MovieListScreen(navController = navController)
            MoviesScreenNewUI(navController = navController, isSystemInDarkTheme())
        }

        composable(Screens.SeriesScreen.name) {
            SeriesScreenNewUI(navController = navController, isSystemInDarkTheme = isSystemInDarkTheme() )
            //SeriesListScreen( navController = navController )
        }

        composable(Screens.FavoritesScreen.name) {
            ScreenContent(name = "Favorites") {
            }
        }
    }
}