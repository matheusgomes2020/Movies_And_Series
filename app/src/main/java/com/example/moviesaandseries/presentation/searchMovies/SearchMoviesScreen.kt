package com.example.moviesaandseries.presentation.searchMovies

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import com.example.moviesaandseries.common.navigation.AppGraph
import com.example.moviesaandseries.presentation.general.SearchResultsShimmer
import com.example.moviesaandseries.presentation.searchMovies.components.SearchMovieListItem

@Composable
fun SearchMoviesScreen(
    query: String,
    navController: NavController,
    searchViewModel: SearchMoviesViewModel = hiltViewModel()
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
                ) {
                    items(state.movies) { movie ->
                        SearchMovieListItem(
                            movie = movie,
                            onItemClick = {
                                navController.navigate(AppGraph.movies_details.DETAILS + "/${movie.id}")
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
            Column {
                Text(
                    text =  "Resultados para $query",
                    modifier = Modifier
                        .padding(12.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    maxLines = 1
                )
                SearchResultsShimmer(isLoading = true, contentAfterLoading = { /*TODO*/ })
            }
        }
    }

}