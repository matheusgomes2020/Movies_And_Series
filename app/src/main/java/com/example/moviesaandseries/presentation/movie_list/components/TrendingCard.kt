package com.example.moviesaandseries.presentation.movie_list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.moviesaandseries.R
import com.example.moviesaandseries.common.Constants
import com.example.moviesaandseries.domain.model.Movie
import com.example.moviesaandseries.domain.model.Series
import com.example.moviesaandseries.presentation.general.DpDimensions

@Composable
fun TrendingCard(
    movie: Movie,
    onClick: (Movie) -> Unit

){
    Surface(
        shape = RoundedCornerShape(DpDimensions.Dp20),
        modifier = Modifier
            .width(370.dp)
            .height(190.dp)
            .clickable {
                onClick(movie)
            }
    ) {
        Box(modifier = Modifier
            .paint(
            painter = rememberAsyncImagePainter(model = if (!movie.poster_path.isNullOrEmpty()) Constants.BASE_IMAGE_URL + movie.poster_path else R.drawable.logo) ,
            contentScale = ContentScale.Crop
        ),
                contentAlignment = Alignment.BottomCenter) {

            Column(
                modifier = Modifier
                    .padding(DpDimensions.Normal)
                    .fillMaxWidth()
            ) {
                Text(
                    text = movie.title,
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.White
                )

            }
        }
    }
}



