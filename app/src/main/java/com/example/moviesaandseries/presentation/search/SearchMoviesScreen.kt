package com.example.moviesaandseries.presentation.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.moviesaandseries.presentation.search.components.SearchMovieListItem

@Composable
fun SearchMoviesScreen(
    query: String,
    navController: NavController,
    searchViewModel: SearchViewModel = hiltViewModel()
) {
    val state = searchViewModel.state.value

    Box(modifier = Modifier.fillMaxSize()) {
        state.movies?.let { movies ->
            Column {
                Text(
                    text =  "Resultados para $query",
                     modifier = Modifier
                     .padding(12.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    maxLines = 1
                )
                LazyColumn(contentPadding = PaddingValues(12.dp),
                    //modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp)
                    //modifier = Modifier.fillMaxSize()
                ) {
                    items(state.movies) { movie ->
                        SearchMovieListItem(
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