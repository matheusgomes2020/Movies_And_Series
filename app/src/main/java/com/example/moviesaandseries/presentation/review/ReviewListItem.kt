package com.example.moviesaandseries.presentation.review

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.moviesaandseries.R
import com.example.moviesaandseries.common.Constants
import com.example.moviesaandseries.data.remote.dto.Review
import com.example.moviesaandseries.data.remote.dto.episode.EpisodeDto

@Composable
fun ItemReview(
    review: Review
) {
    val average = when (review.author_details.rating.toDouble()) {
        in 0.0..1.9 -> "⭐"
        in 2.0..3.9 -> "⭐⭐"
        in 4.0..5.9 -> "⭐⭐⭐"
        in 6.0..7.9 -> "⭐⭐⭐⭐"
        in 8.0..10.0 -> "⭐⭐⭐⭐⭐"
        else -> {}
    }

    Card(
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        modifier = Modifier
            .padding(bottom = 8.dp)
            // .clickable { onItemClick( series ) }
            .background(color = Color.White),
        //
    ) {

        Column(
            modifier = Modifier
                //.fillMaxWidth()
                .padding(1.dp),
            //.background(Color.LightGray)
            // .clip(shape = RoundedCornerShape(5.dp)
            //  .copy(CornerSize(5.dp))),

            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(5.dp)

            ) {
                Image(
                    painter = rememberAsyncImagePainter(
                        model = if (!review.author_details.avatar_path.isNullOrEmpty()) Constants.BASE_IMAGE_URL + review.author_details.avatar_path else R.drawable.flash
                    ),
                    contentScale = ContentScale.Crop,
                    contentDescription = null,
                    modifier = Modifier
                        .size(56.dp)
                        .clip(RoundedCornerShape(15.dp))
                )

                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(
                        start = 5.dp
                    )
                ) {
                    Text(
                        text = if (!review.author.isNullOrEmpty()) review.author else "no name",
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                    Text(
                        text = if (!review.created_at.isNullOrEmpty()) review.created_at else "no data",
                        fontSize = 11.sp,
                    )
                }
            }

            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.padding(5.dp)
            ) {
                Text(
                    text = average.toString(),
                    fontSize = 14.sp,
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = if (!review.content.isNullOrEmpty()) review.content else "no content",
                    modifier = Modifier.width(365.dp),
                    fontSize = 12.sp,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                )

            }
        }

    }
}

@Preview
@Composable
fun Item() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(1.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,

            ) {
            Image(
                painter = painterResource(R.drawable.ic_launcher_background),
                contentDescription = null,
                modifier = Modifier
                    .size(56.dp)
                    .clip(RoundedCornerShape(15.dp))
            )

            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.padding(
                    start = 5.dp
                )
            ) {
                Text(
                    text = "Lucas B",
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Text(
                    text = "2023/03/03",
                    fontSize = 11.sp,
                )
            }
        }

        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding( 5.dp )
        ) {
            Text(
                text = "⭐⭐⭐⭐⭐",
                fontSize = 14.sp,
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = "O príncipe T'Challa retorna a Wakanda para ser coroado rei. Assumindo o manto de Pantera Negra, ele vai à caça de um vilão que roubou um precioso metal de seu país.",
                fontSize = 12.sp,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
            )

        }
    }
}