package com.example.moviesaandseries.presentation.review

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.moviesaandseries.R
import com.example.moviesaandseries.common.Constants
import com.example.moviesaandseries.data.remote.dto.Review

@Composable
fun ReviewListItem(
    review: Review,
    modifier: Modifier
){
    Card(modifier = modifier) {

        Row(modifier = Modifier
            .padding(start = 2.dp, top = 2.dp)
            .fillMaxWidth(),
            //.background(Color.White),
            verticalAlignment = Alignment.CenterVertically) {
            Image(painter = rememberAsyncImagePainter(
                model = if (!review.author_details.avatar_path.isNullOrEmpty()) Constants.BASE_IMAGE_URL + review.author_details.avatar_path else R.drawable.flash
            ),
                contentScale = ContentScale.Crop,
                contentDescription = "person image",
                modifier = Modifier
                    .width(50.dp)
                    .height(50.dp)
                    .clip(RoundedCornerShape(10.dp))
            )

            Column(modifier = Modifier
                .padding(start = 5.dp)) {
                Text(text = if (!review.author.isNullOrEmpty()) review.author else "no content",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold)
                Text(text = if (!review.created_at.isNullOrEmpty()) review.created_at else "no data",
                    fontSize = 14.sp,)
            }
        }
        Column(modifier = Modifier
            .padding(5.dp)
            // .background(Color.White)
        ) {
            val average = when ( review.author_details.rating.toDouble() ) {
                in 0.0..1.9 ->  "⭐"
                in 2.0..3.9 -> "⭐⭐"
                in 4.0..5.9 ->  "⭐⭐⭐"
                in 6.0..7.9 ->  "⭐⭐⭐⭐"
                in 8.0..10.0 ->  "⭐⭐⭐⭐⭐"
                else -> {}
            }
            Text(text = average.toString(),
                modifier = Modifier
                    .padding(bottom = 3.dp),
                fontSize = 14.sp,)
            Text(text = if (!review.content.isNullOrEmpty()) review.content else "no content",
                fontSize = 12.sp,
                maxLines = 3)
        }

    }
}


@Preview
@Composable
fun ReviewListItem2(

){
    Card(modifier = Modifier.background(Color.Transparent)) {

        Row(modifier = Modifier
            .padding(start = 2.dp, top = 2.dp)
            .fillMaxWidth(),
            //.background(Color.White),
            verticalAlignment = Alignment.CenterVertically) {
            Image(
                painterResource(R.drawable.flash),
                contentDescription = "movie image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(50.dp)
                    .height(50.dp)
                    .clip(RoundedCornerShape(15.dp))
            )
            Column(modifier = Modifier
                .padding(start = 5.dp)) {
                Text(text = "Louis Silva",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold)
                Text(text = "2023/03/03",
                    fontSize = 14.sp,)
            }
        }
        Column(modifier = Modifier
            .padding(5.dp)
           // .background(Color.White)
        ) {

            Text(text = "⭐⭐⭐⭐⭐",
                modifier = Modifier
                .padding(bottom = 3.dp),
                fontSize = 14.sp,)
            Text(text = "Quando Barry usa seus superpoderes para viajar no" +
                    " tempo e mudar os eventos do passado. Mas quando" +
                    " tenta salvar sua família e acaba, sem querer," +
                    " alterando o futuro, Barry fica preso em uma" +
                    " realidade na qual o General Zod está de volta," +
                    " ameaçando colocar o mundo" +
                    " em risco, e não há super-heróis a quem recorrer.",
                fontSize = 12.sp,
                maxLines = 3)
        }

    }
}