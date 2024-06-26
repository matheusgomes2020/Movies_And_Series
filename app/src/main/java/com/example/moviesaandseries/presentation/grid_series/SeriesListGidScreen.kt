package com.example.moviesaandseries.presentation.grid_series


import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.moviesaandseries.common.navigation.AppGraph
import com.example.moviesaandseries.presentation.general.AppBarWithBack
import com.example.moviesaandseries.presentation.general.DpDimensions
import com.example.moviesaandseries.presentation.general.ShimmerMovieAndSeriesListItem
import com.example.moviesaandseries.presentation.series_list.SeriesListState
import com.example.moviesaandseries.presentation.series_list.SeriesListViewModel
import com.example.moviesaandseries.presentation.series_list.components.SeriesListItem
import com.example.moviesaandseries.ui.theme.DarkGrey11


@Composable
fun SeriesListGidScreen(
    typeSeries: String,
    navController: NavController,
    seriesListViewModel: SeriesListViewModel = hiltViewModel()
) {

    var state = SeriesListState()

    when (typeSeries) {
        "Séries em alta" -> {
            state = seriesListViewModel.statePopular.value
        }
        "No ar hoje" -> {
            state = seriesListViewModel.stateAiryngToday.value
        }
        "Séries no ar" -> {
            state = seriesListViewModel.stateOnAir.value
        }
        "Melhores Avaliadas" -> {
            state = seriesListViewModel.stateRated.value
        }
        "Séries em tendência hoje" -> {
            state = seriesListViewModel.stateTrendingToday.value
        }
    }


    Scaffold(
        topBar = {
            AppBarWithBack(title = typeSeries, backIcon = Icons.Default.ArrowBack, onBackClick = {
                navController.popBackStack()
            } )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                //.verticalScroll(rememberScrollState())
                .fillMaxSize()
                .background(
                    color = if (isSystemInDarkTheme())
                        DarkGrey11 else Color.White
                )
        ) {
            state.series?.let { series ->
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.fillMaxSize(),

                    horizontalArrangement = Arrangement.spacedBy(DpDimensions.Small),
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(DpDimensions.Small)
                ) {

                    items(series) { series ->
                        SeriesListItem(series = series, onClick = {
                            navController.navigate(AppGraph.series_details.DETAILS + "/${series.id}")
                        })
                    }

                }
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
                    //.align(Alignment.Center)
                )
            }
            if(state.isLoading) {
                LazyVerticalGrid(columns = GridCells.Fixed( 2 ),
                    modifier = Modifier.fillMaxSize(),

                    horizontalArrangement = Arrangement.spacedBy(DpDimensions.Small),
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(DpDimensions.Small)
                ) {
                    items(20) {
                        ShimmerMovieAndSeriesListItem()
                    }
                }
            }
        }
}
