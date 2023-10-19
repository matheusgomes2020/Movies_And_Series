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
import com.example.moviesaandseries.ui.theme.MoviesAandSeriesTheme

@Composable
fun GenreItem(
    modifier: Modifier = Modifier,
    genre: Genre,
    onClick: (Genre) -> Unit = {},
    height: Dp = 118.dp
) {

    Surface(
        shape = RoundedCornerShape(DpDimensions.Dp20),
        modifier = modifier
            .width(170.dp)
            .height(height),
        onClick = { onClick(genre) }
    ) {


        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(genre.color)
//                .paint(
//                    painter = painterResource(id = genre.image),
//                    contentScale = ContentScale.Crop
//                )
                .background(
                    brush = Brush.verticalGradient(
                        listOf(
                            Color.Transparent,
                            Color.Transparent,
                            Color.Black
                        )
                    ),
//                RoundedCornerShape(DpDimensions.Small)
                )
                .clip(RoundedCornerShape(DpDimensions.Small)),
            contentAlignment = Alignment.BottomStart
        ) {


            Column(
                modifier = Modifier.padding(DpDimensions.Normal)
            ) {
                Text(
                    text = genre.title,
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White
                )
            }
        }
    }
}






@Preview
@Composable
fun CollectionItemPreview() {
    MoviesAandSeriesTheme {
        GenreItem(genre = genres[0])
    }
}

