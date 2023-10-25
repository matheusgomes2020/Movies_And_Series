package com.example.moviesaandseries.presentation.movie_list.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
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
import com.example.moviesaandseries.common.navigation.AppGraph
import com.example.moviesaandseries.domain.repository.Movies
import com.example.moviesaandseries.presentation.person_list.MoviesCastListState
import com.example.moviesaandseries.presentation.general.ShimmerMovieAndSeriesListItem
import com.example.moviesaandseries.presentation.general.ShimmerTrending
import com.example.moviesaandseries.presentation.general.DpDimensions
import com.example.moviesaandseries.presentation.movie_list.MovieListState

@Composable
fun MovieTrendingCell(
    navController: NavController,
    state: MovieListState,
    ){
    Box(
    ) {
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(DpDimensions.Small),
                contentPadding = PaddingValues(horizontal = 16.dp),
        ) {
            items(state.movies) { movie ->
                TrendingCard(movie = movie, onClick = {
                    navController.navigate(AppGraph.movies_details.DETAILS + "/${movie.id}")
                } )
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
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(DpDimensions.Small),
                contentPadding = PaddingValues(horizontal = 16.dp),
            ) {
                items(3) {
                    ShimmerTrending()
                }
            }
        }
    }
}



@Composable
fun MovieListCell(
    navController: NavController,
    state: MovieListState,
){
    Box(
    ) {
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(DpDimensions.Small),
            contentPadding = PaddingValues(horizontal = 16.dp),
        ) {
            items(state.movies) { movie ->
                MovieListItem(movie = movie, onClick = {
                    navController.navigate(AppGraph.movies_details.DETAILS + "/${movie.id}")
                } )
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
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(DpDimensions.Small),
                contentPadding = PaddingValues(horizontal = 16.dp),
            ) {
                items(3) {
                    ShimmerMovieAndSeriesListItem()
                }
            }
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

@Composable
fun MovieNewUICellFirebase(
    navController: NavController,
    movies: Movies,
    tipo: String,
    deleteMovie: (idFirebase: String) -> Unit
){

    Box(
    ) {

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(DpDimensions.Small),
            contentPadding = PaddingValues(horizontal = 16.dp),
        ) {
            items(movies) { movie ->
                MovieListItemFirebase(
                    movie = movie,
                    onItemClick = {
                        try {
                            if ( tipo == "movie" ) navController.navigate(AppGraph.movies_details2.DETAILS + "/${movie.id}")
                            else navController.navigate(AppGraph.series_details.DETAILS + "/${movie.id}")
                        }catch (e: Exception){
                            e.printStackTrace()
                        }
                    },
                    deleteMovie = {
                        movie.idFirebase?.let { idFirebase ->
                            deleteMovie( idFirebase )
                        }
                    }
                )
            }
        }
    }
}