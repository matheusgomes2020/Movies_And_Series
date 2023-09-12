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
import androidx.navigation.compose.rememberNavController
import com.example.moviesaandseries.common.navigation.DetailsScreen
import com.example.moviesaandseries.common.navigation.Graph
import com.example.moviesaandseries.common.navigation2.AppGraph
import com.example.moviesaandseries.presentation.movie_list.components.MovieListItem
import com.example.moviesaandseries.presentation.series_list.components.SeriesListItem

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
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}