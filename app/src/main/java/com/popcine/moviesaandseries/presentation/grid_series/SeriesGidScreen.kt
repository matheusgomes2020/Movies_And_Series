package com.popcine.moviesaandseries.presentation.grid_series


import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.popcine.moviesaandseries.common.navigation.AppGraph
import com.popcine.moviesaandseries.presentation.general.AppBarWithBack
import com.popcine.moviesaandseries.presentation.general.DpDimensions
import com.popcine.moviesaandseries.presentation.series_list.components.SeriesListItem
import com.popcine.moviesaandseries.ui.theme.DarkGrey11


@Composable
fun SeriesGidScreen(
    title: String,
    navController: NavController,
    viewModel: SeriesGridViewModel

) {

    val series = viewModel.series
    Scaffold(
        topBar = {
            AppBarWithBack(title = "Séries $title", backIcon = Icons.Default.ArrowBack, onBackClick = {
                navController.popBackStack()
            })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(
                    color = if (isSystemInDarkTheme())
                        DarkGrey11 else Color.White
                )
        ) {
            if (series!=null) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.fillMaxSize(),

                    horizontalArrangement = Arrangement.spacedBy(DpDimensions.Small),
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(DpDimensions.Small)
                ) {
                    items(series!!) { series ->
                        SeriesListItem(series = series, onClick = {
                            navController.navigate(AppGraph.series_details.DETAILS + "/${series.id}")
                        },
                            height = 240.dp)
                    }
                }
            } else {
                Text(text = "erro")
            }
        }
    }
}