package com.example.moviesaandseries.common.navigation

import android.util.Log
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.moviesaandseries.presentation.account_details.AccountDetailsScreen
import com.example.moviesaandseries.presentation.cast_grid.CastGridScreen
import com.example.moviesaandseries.presentation.cast_grid.CastGridViewModel
import com.example.moviesaandseries.presentation.person_detail.PersonDetailScreen
import com.example.moviesaandseries.presentation.person_Image.ImagePersonScreen
import com.example.moviesaandseries.presentation.episode.EpisodeScreen
import com.example.moviesaandseries.presentation.searchMovies.SearchMoviesScreen
import com.example.moviesaandseries.presentation.searchSeries.SearchSeriesScreen
import com.example.moviesaandseries.presentation.season.SeasonDetailScreen
import com.example.moviesaandseries.presentation.favorites.ProfileScreen
import com.example.moviesaandseries.presentation.movies_genres.MovieGenresScreen
import com.example.moviesaandseries.presentation.series_genres.SeriesGenresScreen
import com.example.moviesaandseries.presentation.movie_detail.MovieDetailScreen
import com.example.moviesaandseries.presentation.movie_list.MoviesScreen
import com.example.moviesaandseries.presentation.series_detail.SeriesDetailScreen
import com.example.moviesaandseries.presentation.series_list.SeriesScreen
import com.example.moviesaandseries.presentation.general.UserData
import com.example.moviesaandseries.presentation.grid_movies.MoviesGridScreen
import com.example.moviesaandseries.presentation.grid_movies.MoviesGridViewModel
import com.example.moviesaandseries.presentation.grid_series.SeriesGidScreen
import com.example.moviesaandseries.presentation.grid_series.SeriesGridViewModel

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
        }
        composable(route = AppGraph.auth.SIGN_UP) {
           // ScreenContent(name = "SIGN UP") {}
        }
        composable(route = AppGraph.auth.FORGOT_PASSWORD) {
           // ScreenContent(name = "FORGOT PASSWORD") {}
        }
    }
}

@Composable
fun HomeNavGraph(navController: NavHostController, userData: UserData?,
                 onSignOut: () -> Unit) {

    val castGridViewModel: CastGridViewModel = viewModel()
    val moviesGridViewModel: MoviesGridViewModel = viewModel()
    val seriesGridViewModel: SeriesGridViewModel = viewModel()

    NavHost(
        navController = navController,
        route = AppGraph.home.ROOT,
        startDestination = AppGraph.home.MOVIES
    ) {
        composable(route = AppGraph.home.MOVIES) {
            MoviesScreen(navController = navController, isSystemInDarkTheme(), moviesGridViewModel = moviesGridViewModel)
        }
        composable(route = AppGraph.home.SERIES) {
            SeriesScreen(navController = navController, isSystemInDarkTheme = isSystemInDarkTheme(), seriesGridViewModel = seriesGridViewModel )
        }
        composable(route = AppGraph.home.FAVORITES) {
            ProfileScreen(navController = navController, isSystemInDarkTheme(), userData = userData, onSignOut = onSignOut, moviesGridViewModel = moviesGridViewModel, seriesGridViewModel = seriesGridViewModel)
        }
        movieDetailsNavGraph( navController = navController, userData = userData, castGridViewModel = castGridViewModel, moviesGridViewModel = moviesGridViewModel, seriesGridViewModel = seriesGridViewModel )
        seriesDetailsNavGraph( navController = navController, userData = userData, castGridViewModel = castGridViewModel, seriesGridViewModel = seriesGridViewModel, moviesGridViewModel = moviesGridViewModel )
        searchSeriesNavGraph( navController = navController )
        searchMoviesNavGraph( navController = navController )
        moviesGenresNavGraph( navController = navController )
        seriesGenresNavGraph( navController = navController )
        accountDetailsNavGraph( navController = navController, userData = userData, onSignOut = onSignOut )
        castGridNavGraph( navController = navController, viewModel = castGridViewModel )
        moviesGridNavGraph( navController = navController, viewModel = moviesGridViewModel )
        seriesGridNavGraph( navController = navController, viewModel = seriesGridViewModel )

    }
}

fun NavGraphBuilder.moviesGenresNavGraph( navController: NavController ) {
    navigation(
        route = AppGraph.movie_genres.ROOT,
        startDestination = AppGraph.movie_genres.GENRE_MOVIES
    ) {
        composable(route = AppGraph.movie_genres.GENRE_MOVIES + "/{pageNumber}/{genreID}/{genreTitle}",
            arguments = listOf(
                navArgument("pageNumber") {
                    type = NavType.StringType
                },
                navArgument("genreID") {
                    type = NavType.StringType
                },
                navArgument("genreTitle") {
                    type = androidx.navigation.NavType.StringType
                }
            )
        ) { navBackStackEntry ->

            navBackStackEntry.arguments?.getString("genreTitle").let {
                Log.d("BATATAO", "GENREMOVie: ${navBackStackEntry.destination}")
                MovieGenresScreen( it!!, navController = navController )
            }
        }
    }
}

fun NavGraphBuilder.castGridNavGraph(navController: NavController, viewModel: CastGridViewModel ) {
    navigation(
        route = AppGraph.cast_grid.ROOT,
        startDestination = AppGraph.cast_grid.GRID
    ) {
        composable(route = AppGraph.cast_grid.GRID,
        ) {
                CastGridScreen(navController = navController, viewModel = viewModel
                )
        }
    }
}

fun NavGraphBuilder.moviesGridNavGraph(navController: NavController, viewModel: MoviesGridViewModel ) {
    navigation(
        route = AppGraph.movies_grid.ROOT,
        startDestination = AppGraph.movies_grid.MOVIES_GRID
    ) {
        composable(route = AppGraph.movies_grid.MOVIES_GRID+"/{title}",
            arguments = listOf(
                navArgument("title") {
                    type = NavType.StringType
                }
            )
        ) { navBackStackEntry ->

            navBackStackEntry.arguments?.getString("title").let {
                MoviesGridScreen(
                    it!!,
                    navController = navController,
                    sharedMoviesGridViewModel = viewModel
                )
            }
        }
    }
}

fun NavGraphBuilder.seriesGridNavGraph( navController: NavController, viewModel: SeriesGridViewModel ) {
    navigation(
        route = AppGraph.series_grid.ROOT,
        startDestination = AppGraph.series_grid.SERIES_GRID
    ) {
        composable(route = AppGraph.series_grid.SERIES_GRID+"/{title}",
            arguments = listOf(
                navArgument("title") {
                    type = NavType.StringType
                }
            )
        ) { navBackStackEntry ->

            navBackStackEntry.arguments?.getString("title").let {
                SeriesGidScreen(
                    it!!,
                    navController = navController,
                    viewModel = viewModel
                )
            }
        }
    }
}

fun NavGraphBuilder.accountDetailsNavGraph( navController: NavController, userData: UserData?, onSignOut: () -> Unit ) {
    navigation(
        route = AppGraph.account_details.ROOT,
        startDestination = AppGraph.account_details.ACCOUNT_DETAILS
    ) {
        composable(route = AppGraph.account_details.ACCOUNT_DETAILS,
        ) {
            AccountDetailsScreen(navController = navController, isSystemInDarkTheme = isSystemInDarkTheme() , userData = userData, onSignOut = onSignOut )
        }
    }
}

fun NavGraphBuilder.seriesGenresNavGraph( navController: NavController ) {
    navigation(
        route = AppGraph.series_genres.ROOT,
        startDestination = AppGraph.series_genres.GENRE_SERIES
    ) {
        composable(route = AppGraph.series_genres.GENRE_SERIES + "/{pageNumber}/{genreID}/{genreTitle}",
            arguments = listOf(
                navArgument("pageNumber") {
                    type = NavType.StringType
                },
                navArgument("genreID") {
                    type = NavType.StringType
                },
                navArgument("genreTitle") {
                    type = androidx.navigation.NavType.StringType
                }
            )
        ) { navBackStackEntry ->

            navBackStackEntry.arguments?.getString("genreTitle").let {
                Log.d("BATATAO", "GENREMOVie: ${navBackStackEntry.destination}")
                SeriesGenresScreen( it!!, navController = navController )
            }
        }
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
                SearchMoviesScreen( it!!, navController = navController, isSystemInDarkTheme() )
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
                SearchSeriesScreen(it!!, navController = navController, isSystemInDarkTheme())
            }
        }
    }
}

fun NavGraphBuilder.movieDetailsNavGraph(navController: NavController, userData: UserData?, castGridViewModel: CastGridViewModel, moviesGridViewModel: MoviesGridViewModel, seriesGridViewModel: SeriesGridViewModel ){
    navigation(
        route = AppGraph.movies_details2.ROOT,
        startDestination = AppGraph.movies_details2.DETAILS
    ) {
        composable(route = AppGraph.movies_details2.DETAILS + "/{movieId}",
            arguments = listOf(
                navArgument( "movieId" ) {
                    type = NavType.StringType
                }
            )
        ) { navBackStackEntry ->

            navBackStackEntry.arguments?.getString("movieId").let {
                Log.d("TRT", "movieDetailsNavGraph2: ${navBackStackEntry.destination}")
                MovieDetailScreen(navController = navController, userData = userData, isSystemInDarkTheme = isSystemInDarkTheme(),
                    castGridViewModel = castGridViewModel, moviesGridViewModel = moviesGridViewModel, seriesGridViewModel = seriesGridViewModel )
            }
        }
    }
}

fun NavGraphBuilder.seriesDetailsNavGraph(navController: NavController, userData: UserData?, castGridViewModel: CastGridViewModel, seriesGridViewModel: SeriesGridViewModel, moviesGridViewModel: MoviesGridViewModel ){
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
                SeriesDetailScreen(navController = navController, isSystemInDarkTheme = isSystemInDarkTheme(), userData = userData, castGridViewModel = castGridViewModel, seriesGridViewModel = seriesGridViewModel)
            }
        }
        seasonDetailsNavGraph( navController = navController, sharedViewModel = castGridViewModel )
        castDetailNavGraph(navController = navController, moviesGridViewModel = moviesGridViewModel, seriesGridViewModel = seriesGridViewModel )
    }
}

fun NavGraphBuilder.castDetailNavGraph(navController: NavController, moviesGridViewModel: MoviesGridViewModel, seriesGridViewModel: SeriesGridViewModel) {
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
                PersonDetailScreen(navController = navController, isSystemInDarkTheme(), moviesGridViewModel = moviesGridViewModel, seriesGridViewModel = seriesGridViewModel)
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
            }
        }
    }
}

fun NavGraphBuilder.seasonDetailsNavGraph(navController: NavController, sharedViewModel: CastGridViewModel){
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
                SeasonDetailScreen(  it!!, navController = navController, isSystemInDarkTheme())
            }
        }
        episodeDetailsNavGraph( navController = navController, sharedViewModel = sharedViewModel )
    }
}

fun NavGraphBuilder.episodeDetailsNavGraph(navController: NavController, sharedViewModel: CastGridViewModel){
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
                EpisodeScreen( navController = navController, isSystemInDarkTheme(), sharedViewModel = sharedViewModel )
            }
        }
    }}

