package com.example.moviesaandseries.presentation.movie_list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.moviesaandseries.presentation.Screen
import com.example.moviesaandseries.presentation.movie_list.components.MovieListItem


@Composable
fun MovieListScreen(
    navController: NavController,
    viewModel: MovieListViewModel = hiltViewModel()
){

    val state = viewModel.statePopular.value
    val state2 = viewModel.stateUpcoming.value
    val state3 = viewModel.stateNowPlaying.value
    val state4 = viewModel.stateRated.value

    Box(modifier = Modifier.fillMaxSize()) {
        state.movies?.let { movies ->
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(20.dp)
            ) {
                item {
                    Column {
                        Text(text = "Popular Movies")
                        MovieListScreen2(navController = navController , state = state)
                        Text(text = "Upcoming Movies")
                        MovieListScreen2(navController = navController , state = state2)
                        Text(text = "Now Playing Movies")
                        MovieListScreen2(navController = navController , state = state3)
                        Text(text = "Rated Movies")
                        MovieListScreen2(navController = navController , state = state4)
                    }

                }

            }

        }

    }
}


@Composable
fun MovieListScreen2(
    navController: NavController,
    state: MovieListState
){
    Box(
      //  modifier = Modifier.fillMaxSize()
    ) {

        LazyRow(
            //modifier = Modifier.fillMaxSize()
        ) {
            items(state.movies) { movie ->
                MovieListItem(
                    movie = movie,
                    onItemClick = {
                        navController.navigate(Screen.MovieDetailScreen.route + "/${movie.id}")
                    }
                )
            }
        }
        if ( state.error.isNotBlank() ) {
            Text(
                text = state.error,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }
        if(state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}