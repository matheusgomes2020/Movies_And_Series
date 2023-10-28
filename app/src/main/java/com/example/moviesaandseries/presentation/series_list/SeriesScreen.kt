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
import com.example.moviesaandseries.presentation.movie_list.components.GenresCell
import com.example.moviesaandseries.presentation.series_list.components.SeriesListCell
import com.example.moviesaandseries.presentation.series_list.components.SeriesTrendingCell
import com.example.moviesaandseries.ui.theme.DarkGrey11
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@Composable
fun SeriesScreenNewUI(navController: NavController, isSystemInDarkTheme: Boolean, viewModel: SeriesListViewModel = hiltViewModel()
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
            SeriesTrendingCell(navController = navController, state = stateTrendingToday, "Tendência hoje", onHeaderClick = {
                navController.navigate(AppGraph.trending_today_series.TRENDING_TODAY_SERIES)
            } )
            Spacer(modifier = Modifier.height(DpDimensions.Small))
            GenresCell(navController = navController, isGenresMovies = false, onHeaderClick =  {})
            Spacer(modifier = Modifier.height(DpDimensions.Small))
            SeriesListCell(navController = navController, state = stateAiringToday, "No ar hoje", onHeaderClick = {
                navController.navigate(AppGraph.airying_today_series.AIRYING_TODAY_SERIES)
            })
            Spacer(modifier = Modifier.height(DpDimensions.Small))
            SeriesListCell(navController = navController, state = stateOnAir, "No ar", onHeaderClick = {
                navController.navigate(AppGraph.on_air_series.ON_AIR_SERIES)
            })
            Spacer(modifier = Modifier.height(DpDimensions.Small))
            SeriesListCell(navController = navController, state = statePopular, "Em alta", onHeaderClick = {
                navController.navigate(AppGraph.popular_series.POPULAR_SERIES)
            })
            Spacer(modifier = Modifier.height(DpDimensions.Small))
            SeriesListCell(navController = navController, state = stateRated,"Melhores avaliadas", onHeaderClick = {
                navController.navigate(AppGraph.rated_series.RATED_SERIES)
            })
            Spacer(modifier = Modifier.height(DpDimensions.Small))
            Spacer(modifier = Modifier.height(60.dp))
        }
    }
}

