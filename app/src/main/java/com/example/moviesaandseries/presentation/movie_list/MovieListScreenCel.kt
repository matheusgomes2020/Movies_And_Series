package com.example.moviesaandseries.presentation.movie_list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.navigation.NavController
import com.example.moviesaandseries.common.navigation.DetailsScreen
import com.example.moviesaandseries.common.navigation2.AppGraph
import com.example.moviesaandseries.presentation.cast.MoviesCastListState
//import com.example.moviesaandseries.common.navigation.DetailsScreen
import com.example.moviesaandseries.presentation.movie_list.components.MovieListItem
import com.example.moviesaandseries.presentation.movie_list.components.MovieListItemWork

@Composable
fun MovieListScreenCell(
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
                       // navController.navigate(DetailsScreen.MoviesDetails.route + "/${movie.id}")
                        navController.navigate(AppGraph.movies_details.DETAILS + "/${movie.id}")
                        //navController.navigate(Graph.DETAILS)
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

@Composable
fun MovieListScreenCellWork(
    navController: NavController,
    state: MoviesCastListState
){
    Box(
        //  modifier = Modifier.fillMaxSize()
    ) {

        LazyRow(
            //modifier = Modifier.fillMaxSize()
        ) {
            items(state.movies) { movie ->
                MovieListItemWork(
                    movie = movie,
                    onItemClick = {
                        try {
                            navController.navigate(AppGraph.movies_details.DETAILS + "/${movie.id}")
                        }catch (e: Exception){
                            e.printStackTrace()
                        }
                        // navController.navigate(DetailsScreen.MoviesDetails.route + "/${movie.id}")

                        //navController.navigate(Graph.DETAILS)
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