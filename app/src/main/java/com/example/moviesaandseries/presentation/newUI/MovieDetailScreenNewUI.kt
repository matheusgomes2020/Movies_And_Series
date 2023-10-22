package com.example.moviesaandseries.presentation.newUI

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.moviesaandseries.R
import com.example.moviesaandseries.domain.model.MovieFirebase
import com.example.moviesaandseries.domain.model.Response
import com.example.moviesaandseries.presentation.favorites.FavoriteViewModel
import com.example.moviesaandseries.presentation.favorites.ProgressBar
import com.example.moviesaandseries.presentation.movie_detail.MovieDetailViewModel
import com.example.moviesaandseries.presentation.movie_list.MovieListState
import com.example.moviesaandseries.presentation.signIn.UserData
import com.example.moviesaandseries.ui.theme.DarkGrey11
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun MovieDetailScreenNewUI(
    navController: NavController,
    isSystemInDarkTheme: Boolean,
    userData: UserData?,
    viewModel: MovieDetailViewModel = hiltViewModel(),
    favoriteViewModel: FavoriteViewModel = hiltViewModel(),
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

    val state = viewModel.state.value
    var stateSimilar: MovieListState
    var stateRecommendations: MovieListState
    val context = LocalContext.current
    var isFavorite: Boolean

    state.movie?.let { movie ->

        var listOfMovies = emptyList<MovieFirebase>()
        when(val moviesResponse = favoriteViewModel.moviesResponse) {
            is Response.Loading -> ProgressBar()
            is Response.Success -> moviesResponse.data.let { movies ->
                listOfMovies = movies.filter { movieFirebase ->
                    movieFirebase.userId == userData?.userId && movieFirebase.title == movie.title
                }
            }
            is Response.Failure -> print(moviesResponse.e)
        }
        isFavorite = !listOfMovies.isNullOrEmpty()
        Scaffold(
            topBar = {
                MainAppBar(icon1 = if (isFavorite) R.drawable.ic_boomarkfilled else R.drawable.ic_boomark, title = movie.title )
            }
        ) {
            paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .verticalScroll(rememberScrollState())
                    .fillMaxSize()
                    .background(color =if (useDarkIcons)
                        Color.White else DarkGrey11
                    )
            ) {

            }
        }
    }
}