package com.example.moviesaandseries.presentation.favorites

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.moviesaandseries.domain.model.Movie
import com.example.moviesaandseries.domain.model.MovieFirebase
import com.example.moviesaandseries.presentation.general.TextBiografia
import com.example.moviesaandseries.presentation.series_list.components.MoviesAndSeriesColumnItem

@Composable
fun MovieCard(
    movie: MovieFirebase,
    deleteMovie: () -> Unit,
    onItemClick: (MovieFirebase) -> Unit
) {
    Card(
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier.padding(5.dp)
            .clickable { onItemClick(movie) }
            .background(color = Color.White ),
    ) {
        MoviesAndSeriesColumnItem(nameOrTitle = movie.title, posterPath = movie.posterPath)
        DeleteIcon(
            deleteMovie = deleteMovie
        )
    }

//    Card(
//        shape = MaterialTheme.shapes.small,
//        modifier = Modifier
//            .padding(
//                start = 8.dp,
//                end = 8.dp,
//                top = 4.dp,
//                bottom = 4.dp
//            )
//            .fillMaxWidth(),
//        //elevation = 3.dp,
//    ) {
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(12.dp),
//            verticalAlignment = Alignment.CenterVertically,
//        ) {
//            Column {
//                TextBiografia(title = movie.title)
//                TextBiografia(title =  movie.poster_path)
//            }
//            Spacer(
//                modifier = Modifier.weight(1f)
//            )

        }
//    }
//}