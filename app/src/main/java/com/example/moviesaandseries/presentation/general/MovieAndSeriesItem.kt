package com.example.moviesaandseries.presentation.general

import androidx.compose.runtime.Composable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.moviesaandseries.R
import com.example.moviesaandseries.common.Constants.BASE_IMAGE_URL

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