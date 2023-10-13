package com.example.moviesaandseries.presentation.movie_list.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.moviesaandseries.common.Constants
import com.example.moviesaandseries.data.remote.dto.person.MovieWork
import com.example.moviesaandseries.domain.model.Movie
import com.example.moviesaandseries.domain.model.MovieFirebase
import com.example.moviesaandseries.presentation.series_list.components.MoviesAndSeriesColumnItem
import com.example.moviesaandseries.ui.theme.fontFamily


@Composable
fun MovieListItem(
    movie: Movie,
    onItemClick: (Movie) -> Unit
) {

Card(
    shape = RoundedCornerShape(15.dp),
    modifier = Modifier.padding(5.dp)
        .clickable { onItemClick(movie) }
        .background(color = Color.White ),
    ) {
        MoviesAndSeriesColumnItem(nameOrTitle = movie.title, posterPath = movie.poster_path)
    }
}

@Composable
fun MovieListItemWork(
    movie: MovieWork,
    onItemClick: (MovieWork) -> Unit
) {

    Card(
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier.padding(5.dp)
            .clickable { onItemClick(movie) }
            .background(color = Color.White ),
    ) {
        MoviesAndSeriesColumnItem(nameOrTitle = movie.title, posterPath = movie.poster_path )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MovieListItemFirebase(
    movie: MovieFirebase,
    deleteMovie: () -> Unit,
    onItemClick: (MovieFirebase) -> Unit
) {

    Card(
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier.padding(5.dp)
            .combinedClickable(
                onClick = {onItemClick(movie)},
                onLongClick = {deleteMovie()}

    ).background(color = Color.White )
    ){
        MoviesAndSeriesColumnItem(nameOrTitle = movie.title, posterPath = movie.posterPath )
    }
}