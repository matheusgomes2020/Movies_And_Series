package com.example.moviesaandseries.presentation.series_list.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.moviesaandseries.domain.model.Series
import com.example.moviesaandseries.presentation.general.DpDimensions
import com.example.moviesaandseries.presentation.general.MovieAndSeriesItem
@Composable
fun SeriesListItem(
    series: Series,
    onClick: (Series) -> Unit,
    height: Dp
){
    Surface(
        shape = RoundedCornerShape(DpDimensions.Dp20),
        modifier = Modifier
            .width(120.dp)
            .height(height),
        onClick = { onClick(series) }
    ) {
        MovieAndSeriesItem(title = series.name, posterPath = if (!series.poster_path.isNullOrEmpty()) series.poster_path else "sem poster")
    }
}
