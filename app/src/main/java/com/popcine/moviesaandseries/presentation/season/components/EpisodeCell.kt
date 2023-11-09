package com.popcine.moviesaandseries.presentation.season.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.popcine.moviesaandseries.common.navigation.AppGraph
import com.popcine.moviesaandseries.data.remote.dto.episode.EpisodeDto
import com.popcine.moviesaandseries.presentation.general.CustomPadding
import com.popcine.moviesaandseries.presentation.general.DpDimensions
import com.popcine.moviesaandseries.presentation.general.SubtitleHeader

@Composable
fun EpisodeCell(navController: NavController, seriesId: String, episodes: List<EpisodeDto>){
    CustomPadding(verticalPadding = 0.dp, horizontalPadding = DpDimensions.Normal) {
        SubtitleHeader(
            title = "Episódios",
            modifier = Modifier.fillMaxWidth(),
            isSystemInDarkTheme = true,
            isIconVisible = false,
            onClick = {
            }
        )

        LazyColumn(
            modifier = Modifier.fillMaxWidth()
                .height(300.dp),
            contentPadding = PaddingValues()){
            items(episodes) { episode->
                EpisodeListItem(
                    episode = episode,
                    onItemClick = {
                        navController.navigate(AppGraph.episode_details.DETAILS + "/${seriesId}/${episode.season_number}/${episode.episode_number}")
                    }
                )
            }
        }
    }
}