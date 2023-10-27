package com.example.moviesaandseries.presentation.series_list.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.moviesaandseries.common.navigation.AppGraph
import com.example.moviesaandseries.presentation.general.DpDimensions
import com.example.moviesaandseries.presentation.person_list.SeriesCastListState
import com.example.moviesaandseries.presentation.general.ShimmerMovieAndSeriesListItem
import com.example.moviesaandseries.presentation.general.ShimmerTrending
import com.example.moviesaandseries.presentation.series_list.SeriesListState

@Composable
fun SeriesTrendingCell(
    navController: NavController,
    state: SeriesListState,
){
    Box(
    ) {

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(DpDimensions.Small),
            contentPadding = PaddingValues(horizontal = 16.dp),
        ) {
            items(state.series) { series ->
                TrendingCardSeries(series = series, onClick = {
                    navController.navigate(AppGraph.series_details.DETAILS + "/${series.id}")
                } )
            }
        }
        if ( state.error.isNotBlank() ) {
            Text(
                text = state.error,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }
        if(state.isLoading) {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(DpDimensions.Small),
                contentPadding = PaddingValues(horizontal = 16.dp),
            ) {
                items(3) {
                    ShimmerTrending()
                }
            }
        }
    }
}

@Composable
fun SeriesListCell(
    navController: NavController,
    state: SeriesListState,
){
    Box(
    ) {
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(DpDimensions.Small),
            contentPadding = PaddingValues(horizontal = 16.dp),
        ) {
            items(state.series) { series ->
                SeriesListItem(series = series, onClick = {
                    navController.navigate(AppGraph.series_details.DETAILS + "/${series.id}")
                } )
            }
        }
        if ( state.error.isNotBlank() ) {
            Text(
                text = state.error,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }
        if(state.isLoading) {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(DpDimensions.Small),
                contentPadding = PaddingValues(horizontal = 16.dp),
            ) {
                items(3) {
                    ShimmerMovieAndSeriesListItem()
                }
            }
        }
    }
}
