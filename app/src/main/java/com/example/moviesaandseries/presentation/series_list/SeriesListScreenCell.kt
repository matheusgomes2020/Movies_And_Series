package com.example.moviesaandseries.presentation.series_list

import androidx.compose.foundation.layout.Box
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
import com.example.moviesaandseries.presentation.cast.SeriesCastListState
import com.example.moviesaandseries.presentation.general.ShimmerListItem
import com.example.moviesaandseries.presentation.series_list.components.SeriesListItem
import com.example.moviesaandseries.presentation.series_list.components.SeriesListItemWork

@Composable
fun SeriesListScreenCell(
    navController: NavController,
    state: SeriesListState
) {
    Box(
        //  modifier = Modifier.fillMaxSize()
    ) {

        LazyRow(
            //modifier = Modifier.fillMaxSize()
        ) {
            items(state.series) { series ->
                SeriesListItem(
                    series = series,
                    onItemClick = {
                        navController.navigate(AppGraph.series_details.DETAILS +"/${series.id}")
                    }
                )
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
            LazyRow {
                items(20) {
                    ShimmerListItem(isLoading = true,
                        contentAfterLoading = { /*TODO*/ })
                }
            }
        }
    }
}

@Composable
fun SeriesListScreenCellPerson(
    navController: NavController,
    state: SeriesCastListState
) {
    Box(
        //  modifier = Modifier.fillMaxSize()
    ) {

        LazyRow(
            //modifier = Modifier.fillMaxSize()
        ) {
            items(state.series) { series ->
                SeriesListItemWork(
                    series = series,
                    onItemClick = {
                        try {
                            navController.navigate(AppGraph.series_details.DETAILS +"/${series.id}")
                        }catch (e: Exception){
                            e.printStackTrace()
                        }
                    }
                )
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
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}