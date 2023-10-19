package com.example.moviesaandseries.presentation.newUI

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.moviesaandseries.common.navigation.AppGraph

@Composable
fun GC(
    navController: NavController,
){
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(DpDimensions.Small),
        contentPadding = PaddingValues(horizontal = 16.dp),
    ) {
        items(genres) { genre ->
            GenreItem(genre = genre, onClick = { genre ->
                Log.d("3434434343", "MoviesScreenNewUI: ${genre.id}")

                navController.navigate( AppGraph.movie_genres.GENRE_MOVIES +  "/${"1"}/${genre.id}" )
            })
        }
    }
}