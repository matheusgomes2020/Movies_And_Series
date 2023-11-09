package com.popcine.moviesaandseries.presentation.favorites

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.popcine.moviesaandseries.R
import com.popcine.moviesaandseries.common.navigation.AppGraph
import com.popcine.moviesaandseries.domain.model.Movie
import com.popcine.moviesaandseries.domain.model.Series
import com.popcine.moviesaandseries.presentation.movie_list.components.MovieAndSeriesCellFirebase
import com.popcine.moviesaandseries.presentation.general.CustomPadding
import com.popcine.moviesaandseries.presentation.general.DpDimensions
import com.popcine.moviesaandseries.presentation.general.MainAppBar
import com.popcine.moviesaandseries.presentation.general.Movies
import com.popcine.moviesaandseries.presentation.general.SubtitleHeader
import com.popcine.moviesaandseries.presentation.general.UserData
import com.popcine.moviesaandseries.presentation.grid_movies.MoviesGridViewModel
import com.popcine.moviesaandseries.presentation.grid_series.SeriesGridViewModel
import com.popcine.moviesaandseries.ui.theme.DarkGrey11
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    navController: NavController,
    isSystemInDarkTheme: Boolean,
    userData: UserData?,
    onSignOut: () -> Unit,
    viewModel: FavoriteViewModel = hiltViewModel(),
    moviesGridViewModel: MoviesGridViewModel,
    seriesGridViewModel: SeriesGridViewModel
) {

    val systemUiController = rememberSystemUiController()
    val useDarkIcons = !isSystemInDarkTheme

    SideEffect {
        systemUiController.setSystemBarsColor(
            color = if (useDarkIcons)
                Color.White else DarkGrey11,
            darkIcons = useDarkIcons
        )
    }
    Scaffold(
        topBar = {
            MainAppBar(icon1 = R.drawable.ic_star,
                isProfileScreen = true,
                title = "Favoritos",
                imageUrl = if (userData?.profilePictureUrl != null) userData.profilePictureUrl else "sem imagem",
                onLogoClick = {},
                onSearchClick = {
                    navController.navigate(AppGraph.account_details.ACCOUNT_DETAILS)
                })
        }
    ) {
        paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
                .background(
                    color = if (useDarkIcons)
                        Color.White else DarkGrey11
                )
        ) {
                Movies(userData = userData,
                    type = "movies"
                ) { movies ->
                    CustomPadding(verticalPadding = 0.dp, horizontalPadding = DpDimensions.Normal) {
                        SubtitleHeader(
                            title = "Filmes favoritos",
                            modifier = Modifier.fillMaxWidth(),
                            isSystemInDarkTheme = true,
                            isIconVisible = true,
                            onClick = {
                                var moviesList = mutableListOf<Movie>()
                                movies.forEach{
                                    var movie = Movie(
                                        id = it.id.toInt(),
                                        title = it.title,
                                        poster_path = it.posterPath,
                                        overview = "",
                                        vote_average = 0.0
                                    )
                                    moviesList.add(movie)
                                }
                                moviesGridViewModel.getMovies(moviesList)
                                navController.navigate(AppGraph.movies_grid.MOVIES_GRID + "/favoritos")
                            }
                        )
                    }
                    if (!movies.isNullOrEmpty()) {
                        MovieAndSeriesCellFirebase(
                            navController = navController,
                            movies = movies,
                            tipo = "movie",
                        ) { idFirebase ->
                            viewModel.deleteMovie(idFirebase)
                        }
                    } else {
                        CustomPadding(
                            verticalPadding = 0.dp,
                            horizontalPadding = DpDimensions.Normal
                        ) {
                            Text(text = "Favorite filmes para vê-los aqui.")
                        }
                    }
                }
            Spacer(modifier = Modifier.height(DpDimensions.Small))
                Movies(userData = userData,
                    type = "series"
                ) { movies ->
                    CustomPadding(verticalPadding = 0.dp, horizontalPadding = DpDimensions.Normal) {
                        SubtitleHeader(
                            title = "Séries favoritas",
                            modifier = Modifier.fillMaxWidth(),
                            isSystemInDarkTheme = true,
                            isIconVisible = true,
                            onClick = {
                                var seriesList = mutableListOf<Series>()
                                movies.forEach{
                                    var series = Series(
                                        id = it.id.toInt(),
                                        name = it.title,
                                        poster_path = it.posterPath,
                                        overview = "",
                                        vote_average = 0.0
                                    )
                                    seriesList.add(series)
                                }
                                seriesGridViewModel.getSeries(seriesList)
                                navController.navigate(AppGraph.series_grid.SERIES_GRID + "/favoritas")
                            }
                        )
                    }
                    if (!movies.isNullOrEmpty()) {
                        MovieAndSeriesCellFirebase(
                            navController = navController,
                            movies = movies,
                            tipo = "series",
                        ) { idFirebase ->
                            viewModel.deleteMovie(idFirebase)
                        }
                    } else {
                        CustomPadding(verticalPadding = 0.dp, horizontalPadding = DpDimensions.Normal) {
                        Text(text = "Favorite séries para vê-las aqui.")}
                    }
                    
                }
        }
    }
}


