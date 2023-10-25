package com.example.moviesaandseries.presentation.series_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.moviesaandseries.R
import com.example.moviesaandseries.common.navigation.AppGraph
import com.example.moviesaandseries.presentation.general.CustomPadding
import com.example.moviesaandseries.presentation.general.DpDimensions
import com.example.moviesaandseries.presentation.general.GenreItem
import com.example.moviesaandseries.presentation.general.MainAppBar
import com.example.moviesaandseries.presentation.general.SubtitleHeader
import com.example.moviesaandseries.presentation.general.genres
import com.example.moviesaandseries.presentation.series_list.components.SeriesListCell
import com.example.moviesaandseries.presentation.series_list.components.SeriesTrendingCell
import com.example.moviesaandseries.ui.theme.DarkGrey11
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@Composable
fun SeriesScreenNewUI(navController: NavController,
                      isSystemInDarkTheme: Boolean,
                      viewModel: SeriesListViewModel = hiltViewModel()
          ){
    val systemUiController = rememberSystemUiController()
    val useDarkIcons = !isSystemInDarkTheme

    SideEffect {
        systemUiController.setSystemBarsColor(
            color = if (useDarkIcons)
                Color.White else DarkGrey11,
            darkIcons = useDarkIcons
        )
    }

    val stateTrendingToday = viewModel.stateTrendingToday.value
    val statePopular = viewModel.statePopular.value
    val stateAiringToday = viewModel.stateAiryngToday.value
    val stateOnAir = viewModel.stateOnAir.value
    val stateRated = viewModel.stateRated.value

    Scaffold(
        topBar = {
            MainAppBar(
                icon1 = R.drawable.search,
                title = "Séries",
                imageUrl = "",
                onLogoClick = {},
                onSearchClick = {
                    navController.navigate( AppGraph.search_series.SEARCH_SERIES + "/${" "}" )
                })
        }
    ) {
        paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
                .background(color =if (useDarkIcons)
                    Color.White else DarkGrey11)
        ) {
            CustomPadding(verticalPadding = 0.dp, horizontalPadding = DpDimensions.Normal) {
                SubtitleHeader(
                    title = "Tendência hoje",
                    modifier = Modifier.fillMaxWidth(),
                    isSystemInDarkTheme = true,
                    onClick = {
                        navController.navigate(AppGraph.trending_today_series.TRENDING_TODAY_SERIES)
                    }
                )
            }
            SeriesTrendingCell(navController = navController, state = stateTrendingToday)
            Spacer(modifier = Modifier.height(DpDimensions.Small))
            CustomPadding(verticalPadding = 0.dp, horizontalPadding = DpDimensions.Normal) {
                SubtitleHeader(
                    title = "Gêneros",
                    modifier = Modifier.fillMaxWidth(),
                    isSystemInDarkTheme = true,
                    onClick = {  }
                )
            }
            Spacer(modifier = Modifier.height(DpDimensions.Small))
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(DpDimensions.Small),
                contentPadding = PaddingValues(horizontal = 16.dp),
            ) {
                items(genres.filter { genre ->
                   genre.type == 2 || genre.type == 3
                }) { genre ->
                    GenreItem(genre = genre, onClick = {
                        navController.navigate( AppGraph.series_genres.GENRE_SERIES + "/${"1"}/${genre.id}/${genre.title}" )
                    }) } }
            Spacer(modifier = Modifier.height(DpDimensions.Small))
            CustomPadding(verticalPadding = 0.dp, horizontalPadding = DpDimensions.Normal) {
                SubtitleHeader(
                    title = "No ar hoje",
                    modifier = Modifier.fillMaxWidth(),
                    isSystemInDarkTheme = true,
                    onClick = {
                        navController.navigate(AppGraph.airying_today_series.AIRYING_TODAY_SERIES) }
                ) }
            SeriesListCell(navController = navController, state = stateAiringToday)
            Spacer(modifier = Modifier.height(DpDimensions.Small))
            CustomPadding(verticalPadding = 0.dp, horizontalPadding = DpDimensions.Normal) {
                SubtitleHeader(
                    title = "No ar",
                    modifier = Modifier.fillMaxWidth(),
                    isSystemInDarkTheme = true,
                    onClick = {
                        navController.navigate(AppGraph.on_air_series.ON_AIR_SERIES) }
                )
            }
            SeriesListCell(navController = navController, state = stateOnAir)
            Spacer(modifier = Modifier.height(DpDimensions.Small))
            CustomPadding(verticalPadding = 0.dp, horizontalPadding = DpDimensions.Normal) {
                SubtitleHeader(
                    title = "Em alta",
                    modifier = Modifier.fillMaxWidth(),
                    isSystemInDarkTheme = true,
                    onClick = {
                        navController.navigate(AppGraph.popular_series.POPULAR_SERIES)
                    }
                )
            }
            SeriesListCell(navController = navController, state = statePopular)
            Spacer(modifier = Modifier.height(DpDimensions.Small))
            CustomPadding(verticalPadding = 0.dp, horizontalPadding = DpDimensions.Normal) {
                SubtitleHeader(
                    title = "Melhores avaliadas",
                    modifier = Modifier.fillMaxWidth(),
                    isSystemInDarkTheme = true,
                    onClick = {
                        navController.navigate(AppGraph.rated_series.RATED_SERIES) }
                )
            }
            SeriesListCell(navController = navController, state = stateRated)
            Spacer(modifier = Modifier.height(60.dp))
        }
    }
}

