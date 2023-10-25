package com.example.moviesaandseries.presentation.season_list

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.moviesaandseries.common.navigation.AppGraph
import com.example.moviesaandseries.presentation.general.DpDimensions

import com.example.moviesaandseries.presentation.season.components.SeasonListItem
import com.example.moviesaandseries.ui.theme.BlueGrey11

@Composable
fun SeasonListScreenCell(
    seriesId: String,
    navController: NavController,
    state: SeasonListState
) {
    Box(
    ) {
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(DpDimensions.Small),
            contentPadding = PaddingValues(horizontal = 16.dp),
        ) {
            items(state.seasons) { season ->
                SeasonListItem(
                    season = season,
                    onItemClick = {
                        navController.navigate(AppGraph.season_details.DETAILS + "/${seriesId}/${season.season_number}")
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

