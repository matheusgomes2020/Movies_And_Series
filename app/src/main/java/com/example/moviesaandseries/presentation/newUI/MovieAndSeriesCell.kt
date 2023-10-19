package com.example.moviesaandseries.presentation.newUI

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.moviesaandseries.R
import com.example.moviesaandseries.common.Constants
import com.example.moviesaandseries.domain.model.Movie
import com.example.moviesaandseries.ui.theme.MoviesAandSeriesTheme

@Composable
fun MovieItemNewUi(
    movie: Movie,
    onClick: (Movie) -> Unit,
    height: Dp = 170.dp
){

    Surface(
        shape = RoundedCornerShape(DpDimensions.Dp20),
        modifier = Modifier
            .width(120.dp)
            .height(height),
        onClick = { onClick(movie) }
    ) {


        Box(
            modifier = Modifier
                .fillMaxSize()
                .paint(
                    painter = rememberAsyncImagePainter(model = if (!movie.poster_path.isNullOrEmpty()) Constants.BASE_IMAGE_URL + movie.poster_path else R.drawable.logo) ,
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
                    text = movie.title,
                    style = MaterialTheme.typography.titleSmall,
                    color = Color.White,
                    maxLines = 1
                )
            }
        }
    }

}
@Preview
@Composable
fun MoviesAndSeriesTItemPreview() {
    MoviesAandSeriesTheme {
        //MovieItemNewUi(movies = moviesAndSeriesT[2])
    }
}