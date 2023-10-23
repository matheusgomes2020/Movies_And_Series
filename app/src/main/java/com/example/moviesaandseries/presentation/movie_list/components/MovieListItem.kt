package com.example.moviesaandseries.presentation.movie_list.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.moviesaandseries.data.remote.dto.person.MovieWork
import com.example.moviesaandseries.domain.model.Movie
import com.example.moviesaandseries.domain.model.MovieOrSeriesFirebase
import com.example.moviesaandseries.presentation.series_list.components.MoviesAndSeriesColumnItem
import com.example.moviesaandseries.ui.theme.BlueGrey11


@Composable
fun MovieListItem(
    movie: Movie,
    onItemClick: (Movie) -> Unit
) {

Card(
    shape = RoundedCornerShape(15.dp),
    modifier = Modifier.padding(5.dp)
        .clickable { onItemClick(movie) }
        .background( color = if (isSystemInDarkTheme())  BlueGrey11 else Color.White ),
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
            .background( color = if (isSystemInDarkTheme())  BlueGrey11 else Color.White ),
    ) {
        MoviesAndSeriesColumnItem(nameOrTitle = movie.title, posterPath = movie.poster_path )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MovieListItemFirebase(
    movie: MovieOrSeriesFirebase,
    deleteMovie: () -> Unit,
    onItemClick: (MovieOrSeriesFirebase) -> Unit
) {

    Card(
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier.padding(5.dp)
            .combinedClickable(
                onClick = {onItemClick(movie)},
                onLongClick = {deleteMovie()}

    )
            .background( color = if (isSystemInDarkTheme())  BlueGrey11 else Color.White ),
    ){
        MoviesAndSeriesColumnItem(nameOrTitle = movie.title, posterPath = movie.posterPath )
    }
}