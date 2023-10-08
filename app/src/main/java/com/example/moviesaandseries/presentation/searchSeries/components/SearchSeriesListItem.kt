package com.example.moviesaandseries.presentation.searchSeries.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.moviesaandseries.domain.model.Series
import com.example.moviesaandseries.presentation.searchMovies.components.SearchItem

@Composable
fun SearchSeriesListItem(
    series: Series,
    onItemClick: (Series) -> Unit
) {


    Card(
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        modifier = Modifier
            .padding(bottom = 8.dp)
            .clickable { onItemClick( series ) }
            .background(color = Color.Transparent),
        //
    ) {

        var overview = "sem overview"
        var poster = "sem poster"
        if (!series.name.isNullOrEmpty())  overview = series.overview!!
        if (!series.poster_path.isNullOrEmpty())  poster = series.poster_path!!

        SearchItem(
            titleOrName = series.name,
            overview = overview,
            imagePath = poster,
            rating = series.vote_average!!
        )


    }
}

