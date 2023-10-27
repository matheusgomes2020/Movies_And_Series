package com.example.moviesaandseries.presentation.movie_list.components

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.moviesaandseries.R
import com.example.moviesaandseries.common.Constants.BASE_IMAGE_URL
import com.example.moviesaandseries.domain.model.Movie
import com.example.moviesaandseries.domain.model.MovieOrSeriesFirebase
import com.example.moviesaandseries.presentation.general.DeleteMovieBottomSheet
import com.example.moviesaandseries.presentation.general.DpDimensions


@Composable
fun MovieListItem(
    movie: Movie,
    onClick: (Movie) -> Unit,
    height: Dp = 170.dp
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

@Composable
fun MovieAndSeriesItem(
    title: String,
    posterPath: String
){

    Box(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = rememberAsyncImagePainter(model = if (posterPath != "sem poster") "$BASE_IMAGE_URL$posterPath" else R.drawable.logo),
                contentScale = ContentScale.Crop
            )
            .background(
                brush = Brush.verticalGradient(
                    listOf(
                        Color.Transparent,
                        Color.Transparent,
                        Color.Black
                    )
                ),
            )
            .clip(RoundedCornerShape(DpDimensions.Small)),
        contentAlignment = Alignment.BottomStart
    ) {
        Column(
            modifier = Modifier.padding(DpDimensions.Small)
        ) {
            Text(
                modifier = Modifier.width(110.dp),
                text = title,
                style = MaterialTheme.typography.titleSmall,
                color = Color.White,
                maxLines = 1
            )
        }
    }
}