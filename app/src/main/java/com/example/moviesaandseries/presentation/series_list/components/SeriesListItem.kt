package com.example.moviesaandseries.presentation.series_list.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
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
import com.example.moviesaandseries.data.remote.dto.series.SeriesWork
import com.example.moviesaandseries.domain.model.Series
import com.example.moviesaandseries.presentation.general.DpDimensions
import com.example.moviesaandseries.presentation.general.TextCards
import com.example.moviesaandseries.ui.theme.BlueGrey11

@Composable
fun SeriesListItem(
    series: Series,
    onClick: (Series) -> Unit,
    height: Dp = 170.dp
){
    Surface(
        shape = RoundedCornerShape(DpDimensions.Dp20),
        modifier = Modifier
            .width(120.dp)
            .height(height),
        onClick = { onClick(series) }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .paint(
                    painter = rememberAsyncImagePainter(model = if (!series.poster_path.isNullOrEmpty()) Constants.BASE_IMAGE_URL + series.poster_path else R.drawable.logo) ,
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
                    text = series.name,
                    style = MaterialTheme.typography.titleSmall,
                    color = Color.White,
                    maxLines = 1
                )
            }
        }
    }

}

@Composable
 fun MoviesAndSeriesColumnItem(nameOrTitle: String, posterPath: String?) {
    Column(
        modifier = Modifier
            .background( color = if (isSystemInDarkTheme())  BlueGrey11 else Color.White )
    ) {
        Image(
            painter = rememberAsyncImagePainter(
                model = if (!posterPath.isNullOrEmpty()) Constants.BASE_IMAGE_URL + posterPath else R.drawable.logo
            ),
            contentScale = ContentScale.Crop,
            contentDescription = "image",
            modifier = Modifier
                .width(110.dp)
                .height(150.dp)
                .clip(shape = RoundedCornerShape(15.dp))
        )
    }
    TextCards( title = nameOrTitle )
}

@Composable
fun SeriesListItemWork(
    series: SeriesWork,
    onItemClick: (SeriesWork) -> Unit
) {

    Card(
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier
            .padding(5.dp)
            .clickable { onItemClick(series) }
            .background( color = if (isSystemInDarkTheme())  BlueGrey11 else Color.White ),
    ) {
        MoviesAndSeriesColumnItem(nameOrTitle = series.name, posterPath = series.poster_path)
    }
}