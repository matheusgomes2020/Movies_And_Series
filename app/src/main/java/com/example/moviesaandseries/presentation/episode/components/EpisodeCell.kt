package com.example.moviesaandseries.presentation.episode.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.moviesaandseries.common.navigation.AppGraph
import com.example.moviesaandseries.data.remote.dto.episode.EpisodeDto
import com.example.moviesaandseries.presentation.episode_list.EpisodeListItem
import com.example.moviesaandseries.presentation.general.CustomPadding
import com.example.moviesaandseries.presentation.general.DpDimensions
import com.example.moviesaandseries.presentation.general.SubtitleHeader
import com.example.moviesaandseries.presentation.general.TextSubTitulos

@Composable
fun EpisodeCell(navController: NavController, seriesId: String, episodes: List<EpisodeDto>){
    CustomPadding(verticalPadding = 0.dp, horizontalPadding = DpDimensions.Normal) {
        SubtitleHeader(
            title = "Episódios",
            modifier = Modifier.fillMaxWidth(),
            isSystemInDarkTheme = true,
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