package com.example.moviesaandseries.presentation.favorites

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.moviesaandseries.common.navigation.AppGraph
import com.example.moviesaandseries.domain.repository.Movies

@Composable
fun MoviesContent(
   // padding: PaddingValues,
    navController: NavController,
    movies: Movies,
    deleteMovie: (idFirebase: String) -> Unit
) {
    LazyRow(
        modifier = Modifier
            .fillMaxSize()
            //.padding(padding)
    ) {
        items(
            items = movies
        ) { movie ->
            MovieCard(
                movie = movie,
                deleteMovie = {
                    movie.idFirebase?.let { idFirebase ->
                        deleteMovie( idFirebase )
                    }
                },
                onItemClick = {
                    navController.navigate(AppGraph.movies_details.DETAILS + "/${movie.id}")
                }
                )
        }
    }

}