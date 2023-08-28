package com.example.moviesaandseries.presentation.movie_list.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import coil.compose.rememberAsyncImagePainter
import com.example.moviesaandseries.common.Constants
import com.example.moviesaandseries.domain.model.Movie


@Composable
fun MovieListItem(
    movie: Movie,
    onItemClick: (Movie) -> Unit
) {

Card(
    shape = RoundedCornerShape(15.dp),
    modifier = Modifier.padding(3.dp)
        .clickable { onItemClick(movie) }
        .background(color = Color.White ),
    ) {

    Column(
        modifier = Modifier
            .background(color = Color.White)
    ) {
        Image(painter = rememberAsyncImagePainter(
            model = Constants.BASE_IMAGE_URL + movie.poster_path),
            contentScale = ContentScale.Crop,
            contentDescription = "movie image",
            modifier = Modifier
                .width(110.dp)
                .height(150.dp)
                .clip(shape = RoundedCornerShape(15.dp)))
    }

    Text(
        text = "${movie.title}",
        modifier = Modifier.width(110.dp)
            .background(Color.White),
        maxLines = 1
    )
}




}