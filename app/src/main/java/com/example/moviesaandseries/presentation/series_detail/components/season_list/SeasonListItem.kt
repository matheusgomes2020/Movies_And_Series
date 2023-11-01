package com.example.moviesaandseries.presentation.series_detail.components.season_list


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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.moviesaandseries.R
import com.example.moviesaandseries.common.Constants
import com.example.moviesaandseries.data.remote.dto.season.SeasonDto
import com.example.moviesaandseries.presentation.general.DpDimensions


@Composable
fun SeasonListItem(
    season: SeasonDto,
    onItemClick: (SeasonDto) -> Unit,
    height: Dp = 170.dp
) {

    Surface(
        shape = RoundedCornerShape(DpDimensions.Dp20),
        modifier = Modifier
            .width(120.dp)
            .height(height),
        onClick = { onItemClick(season) }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .paint(
                    painter = rememberAsyncImagePainter(model = if (!season.poster_path.isNullOrEmpty()) Constants.BASE_IMAGE_URL + season.poster_path else R.drawable.logo) ,
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
                modifier = Modifier.padding(vertical = DpDimensions.Small, horizontal = DpDimensions.Smallest ),
                //horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier.width(110.dp),
                    text ="${season.season_number}° temporada",
                    style = MaterialTheme.typography.titleSmall,
                    color = Color.White,
                    maxLines = 1
                )
            }
        }
    }
}