package com.example.moviesaandseries.presentation.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.navigation.NavController
import com.example.moviesaandseries.common.navigation.AppGraph
import com.example.moviesaandseries.presentation.movie_list.components.MovieListItem

@Composable
fun SearchMoviesScreen(
    navController: NavController,
    searchViewModel: SearchViewModel = hiltViewModel()
) {
    val state = searchViewModel.state.value

    Box(modifier = Modifier.fillMaxSize()) {
        state.movies?.let { movies ->
            LazyColumn(
                //modifier = Modifier.fillMaxSize()
            ) {
                items(state.movies) { movie ->
                    MovieListItem(
                        movie = movie,
                        onItemClick = {
                            // navController.navigate(DetailsScreen.MoviesDetails.route + "/${movie.id}")
                            //navController.navigate(AppGraph.movies_details.DETAILS + "/${movie.id}")
                            //navController.navigate(Graph.DETAILS)
                        }
                    )
                }
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