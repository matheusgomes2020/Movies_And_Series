package com.example.moviesaandseries.presentation.season

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.moviesaandseries.presentation.season.components.EpisodeCell
import com.example.moviesaandseries.presentation.general.AppBarWithBack
import com.example.moviesaandseries.presentation.general.CustomPadding
import com.example.moviesaandseries.presentation.general.DpDimensions
import com.example.moviesaandseries.presentation.general.SeasonDetailShimmer
import com.example.moviesaandseries.presentation.season.components.MainContentSeason
import com.example.moviesaandseries.ui.theme.DarkGrey11
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
 fun SeasonDetailScreen(
    seriesId: String,
    navController: NavController,
    isSystemInDarkTheme: Boolean,
    viewModel: SeasonDetailViewModel = hiltViewModel()

) {
    val systemUiController = rememberSystemUiController()
    val useDarkIcons = !isSystemInDarkTheme

    SideEffect {
        systemUiController.setSystemBarsColor(
            color = if (useDarkIcons)
                Color.White else DarkGrey11,
            darkIcons = useDarkIcons
        )
    }

    val state = viewModel.state.value

        state.season?.let { season ->

            val overview = if (!season.overview.isNullOrEmpty()) season.overview else "sem overview"
            val posterPath = if (!season.poster_path.isNullOrEmpty()) season.poster_path else "sem poster"

            Scaffold(
                topBar = {
                    AppBarWithBack(title = "${season.season_number} - Temporada",
                        backIcon = Icons.Default.ArrowBack,
                        onBackClick = {
                            navController.popBackStack()
                        }
                    )
                }
            ) { paddingValues ->
                Column(
                    modifier = Modifier
                        .padding(paddingValues)
                        .verticalScroll(rememberScrollState())
                        //.horizontalScroll(rememberScrollState())
                        .fillMaxSize()
                        .background(
                            color = if (useDarkIcons)
                                Color.White else DarkGrey11
                        )
                ) {
                    CustomPadding(
                        verticalPadding = 0.dp,
                        horizontalPadding = DpDimensions.Normal
                    ) {
                        MainContentSeason(posterPath = posterPath , overview = overview )
                    }
                    EpisodeCell( navController, seriesId = seriesId, episodes = season.episodes)
                }
            }
        }
        if ( state.error.isNotBlank() ) {
            androidx.compose.material3.Text(
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
            SeasonDetailShimmer()
        }

}





