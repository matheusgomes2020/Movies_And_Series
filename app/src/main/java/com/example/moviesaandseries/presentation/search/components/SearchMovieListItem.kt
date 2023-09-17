package com.example.moviesaandseries.presentation.search.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.CardElevation
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
import com.example.moviesaandseries.common.Constants
import com.example.moviesaandseries.domain.model.Movie

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
            .background(color = Color.Transparent),
        //
    ) {

        Row(
            modifier =
            Modifier
                //.padding( 1.dp )
                .background(color = Color.White)
        ) {
            Image(painter = rememberAsyncImagePainter(
                model = Constants.BASE_IMAGE_URL + movie.poster_path),
                contentScale = ContentScale.Crop,
                contentDescription = "movie search image",
                modifier = Modifier
                    .width(110.dp)
                    .height(110.dp)
                    .clip(shape = RoundedCornerShape(15.dp)))
            Column(
                modifier = Modifier
                    .padding(start = 15.dp, end = 10.dp)
                    //.background(color = Color.Green)
            ) {

                val star = when (movie.vote_average!!) {
                    in 0.0..1.9 ->  "⭐"
                    in 2.0..3.9 -> "⭐⭐"
                    in 4.0..5.9 ->  "⭐⭐⭐"
                    in 6.0..7.9 ->  "⭐⭐⭐⭐"
                    in 8.0..10.0 ->  "⭐⭐⭐⭐⭐"
                    else -> {}
                }
                Text(
                    text = "${movie.title}",
                   // modifier = Modifier,
                        //.width(110.dp),
                       // .background(Color.White),
                       // .padding(start = 3.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    maxLines = 1
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = "$star",
                   // modifier = Modifier
                        //.width(110.dp)
                       // .background(Color.White),
                       // .padding(start = 3.dp),
                    fontSize = 14.sp,
                    maxLines = 1
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = "${movie.overview}",
                    modifier = Modifier
                        .fillMaxWidth(),
                        //.background(Color.Cyan),
                        //.padding(start = 3.dp),
                    fontSize = 12.sp,
                    maxLines = 3,
                    lineHeight = 18.sp

                )

            }


        }


    }
}