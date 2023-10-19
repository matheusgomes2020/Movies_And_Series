package com.example.moviesaandseries.presentation.newUI

import com.example.moviesaandseries.presentation.movies_genres.MoviesGenresViewModel


import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.moviesaandseries.R
import com.example.moviesaandseries.common.navigation.AppGraph
import com.example.moviesaandseries.presentation.movie_list.MovieListState
import com.example.moviesaandseries.presentation.movie_list.MovieListViewModel
import com.example.moviesaandseries.presentation.newUI.AppBarWithBack
import com.example.moviesaandseries.presentation.newUI.DpDimensions
import com.example.moviesaandseries.presentation.newUI.LogoAppBarWithTwoActions
import com.example.moviesaandseries.presentation.newUI.MovieItemNewUi
import com.example.moviesaandseries.ui.theme.DarkGrey11


@Composable
fun MoviesListGridScreen(
    typeMovies: String,
    navController: NavController,
    moviesViewModel: MovieListViewModel = hiltViewModel()
) {

    var state = MovieListState()

    when (typeMovies) {
        "Filmes em alta" -> {
            state = moviesViewModel.statePopular.value
        }
        "Filmes em cartaz" -> {
            state = moviesViewModel.stateNowPlaying.value
        }
        "Filmes melhores avaliados" -> {
            state = moviesViewModel.stateRated.value
        }
        "Filmes em lançamento" -> {
            state = moviesViewModel.stateUpcoming.value
        }
        "Filmes em tendência hoje" -> {
            state = moviesViewModel.stateTrendingToday.value
        }
    }


    Scaffold(
        topBar = {
            AppBarWithBack(title = typeMovies, backIcon = Icons.Default.ArrowBack, onBackClick = {
                navController.popBackStack()
            } )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                //.verticalScroll(rememberScrollState())
                .fillMaxSize()
                .background(
                    color = if (isSystemInDarkTheme())
                        DarkGrey11 else Color.White
                )
        ) {
            state.movies?.let { movies ->
                LazyVerticalGrid(columns = GridCells.Fixed( 2 ),
                    modifier = Modifier.fillMaxSize(),

                    horizontalArrangement = Arrangement.spacedBy(DpDimensions.Small),
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(DpDimensions.Small)
                ) {

                    items( movies ) { movie ->
                        MovieItemNewUi(movie = movie, onClick = {
                            navController.navigate(AppGraph.movies_details.DETAILS + "/${movie.id}")
                        } )
                    }

                }
            }

        }


    }
}
