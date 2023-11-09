package com.popcine.moviesaandseries.presentation.series_detail.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.popcine.moviesaandseries.data.remote.dto.season.SeasonDto
import com.popcine.moviesaandseries.presentation.general.CustomPadding
import com.popcine.moviesaandseries.presentation.general.DpDimensions
import com.popcine.moviesaandseries.presentation.general.SubtitleHeader
import com.popcine.moviesaandseries.presentation.series_detail.components.season_list.SeasonListScreenCell
import com.popcine.moviesaandseries.presentation.series_detail.components.season_list.SeasonListState

@Composable
fun SeasonsCell(
    navController: NavController, seriesId: String, numeroTemporadas: Int, seasons: List<SeasonDto>, state: SeasonListState
) {
    CustomPadding(verticalPadding = 0.dp, horizontalPadding = DpDimensions.Normal) {
        SubtitleHeader(
            title = if (numeroTemporadas > 1) "$numeroTemporadas - temporadas" else "$numeroTemporadas - temporada",
            modifier = Modifier.fillMaxWidth(),
            isSystemInDarkTheme = true,
            isIconVisible = false,
            onClick = {
            }
        )
    }
    SeasonListScreenCell(navController = navController, seriesId = seriesId , state = state )
    Spacer(modifier = Modifier.height(DpDimensions.Small))
}