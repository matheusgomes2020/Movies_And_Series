package com.example.moviesaandseries.common.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.moviesaandseries.presentation.login.LoginContent

/*
@Composable
fun RootNavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = AppGraph.initial.ROOT,
        startDestination = AppGraph.auth.ROOT
    ) {
        authNavGraph(navController = navController)
        composable(route = AppGraph.home.ROOT) {
            HomeViewContent()
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
            ViewContent(name = "SIGN UP") {}
        }
        composable(route = AppGraph.auth.FORGOT_PASSWORD) {
            ViewContent(name = "FORGOT PASSWORD") {}
        }
    }
}

@Composable
fun HomeNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = AppGraph.home.ROOT,
        startDestination = AppGraph.home.MOVIES
    ) {
        composable(route = AppGraph.home.MOVIES) {
            ViewContent(
                name = "Home",
                onClick = {
                    navController.navigate(AppGraph.moviesDetails.ROOT)
                }
            )
        }
        composable(route = AppGraph.home.SERIES) {
            ViewContent(
                name = "Profile",
                onClick = {
                    navController.navigate(AppGraph.seriesDetails.ROOT)
                }
            )
        }
        composable(route = AppGraph.home.FAVORITES) {
            ViewContent(
                name = "Favorites",
                onClick = { }
            )
        }
        moviesDetailsNavGraph(navController = navController)
    }
}

    fun NavGraphBuilder.moviesDetailsNavGraph(navController: NavHostController) {
        navigation(
            route = AppGraph.moviesDetails.ROOT,
            startDestination = AppGraph.moviesDetails.ROOT
        ) {
            composable(route = AppGraph.moviesDetails.CAST) {
                ViewContent(name = "CAST") {
                    navController.navigate(AppGraph.moviesDetails.CAST)
                }
            }
            composable(route = AppGraph.moviesDetails.REVIEW) {
                ViewContent(name = "REVIEW") {
                    navController.navigate(AppGraph.moviesDetails.ROOT)
                }
            }
            composable(route = AppGraph.moviesDetails.SIMILAR) {
                ViewContent(name = "SIMILAR") {
                    navController.popBackStack(
                        route = AppGraph.moviesDetails.ROOT,
                        inclusive = false
                    )
                }
            }
        }
    }

fun NavGraphBuilder.seriesDetailsNavGraph(navController: NavHostController) {
    navigation(
        route = AppGraph.seriesDetails.ROOT,
        startDestination = AppGraph.seriesDetails.ROOT
    ) {
        composable(route = AppGraph.seriesDetails.CAST) {
            ViewContent(name = "CAST") {
                navController.navigate(AppGraph.seriesDetails.CAST)
            }
        }
        composable(route = AppGraph.seriesDetails.REVIEW) {
            ViewContent(name = "REVIEW") {
                navController.navigate(AppGraph.seriesDetails.ROOT)
            }
        }
        composable(route = AppGraph.seriesDetails.SIMILAR) {
            ViewContent(name = "SIMILAR") {
                navController.popBackStack(
                    route = AppGraph.seriesDetails.ROOT,
                    inclusive = false
                )
            }
        }
    }
}



 */