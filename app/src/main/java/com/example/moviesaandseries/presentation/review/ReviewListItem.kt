package com.example.moviesaandseries.presentation.review

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.moviesaandseries.R
import com.example.moviesaandseries.common.Constants
import com.example.moviesaandseries.data.remote.dto.Review
import com.example.moviesaandseries.ui.theme.fontFamily
import com.example.moviesaandseries.ui.theme.fontFamilyLato

@Composable
fun ReviewListItem(
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
            .padding(bottom = 16.dp, end = 8.dp)
            .background(color = Color.White),
    ) {

        Column(
            modifier = Modifier
                .padding(1.dp),
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
                        fontFamily = fontFamily,
                        overflow = TextOverflow.Ellipsis,
                    )
                    Text(
                        text = if (!review.created_at.isNullOrEmpty()) review.created_at else "no data",
                        fontSize = 11.sp,
                        fontFamily = fontFamilyLato,
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
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = if (!review.content.isNullOrEmpty()) review.content else "no content",
                    modifier = Modifier.width(345.dp),
                    fontSize = 12.sp,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}
