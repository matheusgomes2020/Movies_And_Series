package com.example.moviesaandseries.presentation.searchMovies.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.moviesaandseries.R
import com.example.moviesaandseries.common.Constants
import com.example.moviesaandseries.domain.model.Movie
import com.example.moviesaandseries.ui.theme.BlueGrey11

@Composable
fun SearchMovieListItem(
    movie: Movie,
    onItemClick: (Movie) -> Unit
) {


    Card(
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        modifier = Modifier
            .padding(bottom = 8.dp)
            .clickable { onItemClick(movie) }
            //.background(color = Color.Transparent),
        //
    ) {

        var overview = "sem overview"
        var poster = "sem poster"
        if (!movie.title.isNullOrEmpty())  overview = movie.overview!!
        if (!movie.poster_path.isNullOrEmpty())  poster = movie.poster_path!!

        SearchItem(
            titleOrName = movie.title,
            overview = overview,
            imagePath = poster,
            rating = movie.vote_average!!
        )


    }
}

@Composable
fun SearchItem(
    titleOrName: String,
    imagePath: String?,
    rating: Double,
    overview: String,
) {
    Row(
        modifier =
        Modifier
            //.padding( 1.dp )
            .background(color = if (isSystemInDarkTheme()) BlueGrey11 else Color.White),
    ) {
        Image(
            painter = rememberAsyncImagePainter(
                model = if (imagePath == "sem poster")R.drawable.logo  else Constants.BASE_IMAGE_URL + imagePath
            ),
            contentScale = ContentScale.Crop,
            contentDescription = "movie search image",
            modifier = Modifier
                .width(110.dp)
                .height(110.dp)
                .clip(shape = RoundedCornerShape(15.dp))
        )
        Column(
            modifier = Modifier
                .padding(start = 15.dp, end = 10.dp)
            //.background(color = Color.Green)
        ) {

            val star = when (rating!!) {
                in 0.0..1.9 -> "⭐"
                in 2.0..3.9 -> "⭐⭐"
                in 4.0..5.9 -> "⭐⭐⭐"
                in 6.0..7.9 -> "⭐⭐⭐⭐"
                in 8.0..10.0 -> "⭐⭐⭐⭐⭐"
                else -> {"Sem nota"}
            }
            Text(
                text = if (!titleOrName.isNullOrEmpty()) titleOrName else "sem título",
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                maxLines = 1
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = "$star",
                fontSize = 14.sp,
                maxLines = 1
            )
            if (overview != "sem overview" ) {
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = overview ,
                    modifier = Modifier
                        .fillMaxWidth(),
                    fontSize = 12.sp,
                    maxLines = 3,
                    lineHeight = 18.sp
                )
            }
        }


    }
}