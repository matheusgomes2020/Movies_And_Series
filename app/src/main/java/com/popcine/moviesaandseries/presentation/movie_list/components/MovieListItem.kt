package com.popcine.moviesaandseries.presentation.movie_list.components

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.popcine.moviesaandseries.domain.model.Movie
import com.popcine.moviesaandseries.domain.model.MovieOrSeriesFirebase
import com.popcine.moviesaandseries.presentation.general.DeleteMovieBottomSheet
import com.popcine.moviesaandseries.presentation.general.DpDimensions
import com.popcine.moviesaandseries.presentation.general.MovieAndSeriesItem


@Composable
fun MovieListItem(
    movie: Movie,
    onClick: (Movie) -> Unit,
    height: Dp
){
    Surface(
        shape = RoundedCornerShape(DpDimensions.Dp20),
        modifier = Modifier
            .width(118.dp)
            .height(height),
        onClick = { onClick(movie) }
    ) {
        MovieAndSeriesItem( title = movie.title, posterPath = if (!movie.poster_path.isNullOrEmpty()) movie.poster_path else "sem poster" )

    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun MovieListItemFirebase(
    movie: MovieOrSeriesFirebase,
    deleteMovie: () -> Unit,
    onItemClick: (MovieOrSeriesFirebase) -> Unit,
    height: Dp = 170.dp

) {
    val context = LocalContext.current
    val bottomSheetState = rememberModalBottomSheetState()
    var isLogoutSheetOpen by rememberSaveable {
        mutableStateOf(false)
    }

    Surface(
        shape = RoundedCornerShape(DpDimensions.Dp20),
        modifier = Modifier
            .width(120.dp)
            .height(height)
            .combinedClickable(
                onClick = { onItemClick(movie) },
                onLongClick = {
                    isLogoutSheetOpen = true
                }
            ),
    ) {
        MovieAndSeriesItem( title = movie.title, posterPath = if (!movie.posterPath.isNullOrEmpty()) movie.posterPath else "sem poster" )
    }
    if (isLogoutSheetOpen) {
        DeleteMovieBottomSheet(
            name = movie.title,
            bottomSheetState = bottomSheetState,
            onDismiss = { isLogoutSheetOpen = false }, onLogout = {
                deleteMovie().let {
                    Toast.makeText(context, "${movie.title} removido com sucesso!!!", Toast.LENGTH_SHORT).show()
                }
            },
            onCancel = {
                isLogoutSheetOpen = false
            })
    }
}