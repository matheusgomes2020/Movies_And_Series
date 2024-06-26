package com.example.moviesaandseries.presentation.movie_list

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.moviesaandseries.R
import com.example.moviesaandseries.common.navigation.AppGraph
import com.example.moviesaandseries.presentation.general.CustomPadding
import com.example.moviesaandseries.presentation.general.DpDimensions
import com.example.moviesaandseries.presentation.general.GenreItem
import com.example.moviesaandseries.presentation.general.MainAppBar
import com.example.moviesaandseries.presentation.general.SubtitleHeader
import com.example.moviesaandseries.presentation.general.genres
import com.example.moviesaandseries.presentation.movie_list.components.GenresCell
import com.example.moviesaandseries.presentation.movie_list.components.MovieListCell
import com.example.moviesaandseries.presentation.movie_list.components.MovieTrendingCell
import com.example.moviesaandseries.ui.theme.DarkGrey11
import com.example.moviesaandseries.ui.theme.MoviesAandSeriesTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@Composable
fun MoviesScreenNewUI(navController: NavController, isSystemInDarkTheme: Boolean, viewModel: MovieListViewModel = hiltViewModel()
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
    val stateUpcoming = viewModel.stateUpcoming.value
    val stateNowPlaying = viewModel.stateNowPlaying.value
    val stateRated = viewModel.stateRated.value

    Scaffold(
        topBar = {
            MainAppBar(
                icon1 = R.drawable.search,
                title = "Filmes",
                imageUrl = "",
                onLogoClick = {},
                onSearchClick = {
                    navController.navigate( AppGraph.search_movies.SEARCH_MOVIES + "/${" "}" )
                })
        }
    ) {
        paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
                .background(
                    color = if (useDarkIcons)
                        Color.White else DarkGrey11
                )
        ) {
            MovieTrendingCell(navController = navController, state = stateTrendingToday, "Tendência hoje", onHeaderClick = {
                navController.navigate(AppGraph.trending_today_movies.TRENDING_TODAY_MOVIES)
            })
            Spacer(modifier = Modifier.height(DpDimensions.Small))
            GenresCell(navController = navController, isGenresMovies = true, onHeaderClick =  {})
            Spacer(modifier = Modifier.height(DpDimensions.Small))
            MovieListCell(navController = navController, state = statePopular, headerTitle = "Em alta", onHeaderClick = {
                navController.navigate(AppGraph.popular_movies.POPULAR_MOVIES) } )
            Spacer(modifier = Modifier.height(DpDimensions.Small))
            MovieListCell(navController = navController, state = stateNowPlaying, headerTitle = "Em cartaz", onHeaderClick = {
                navController.navigate(AppGraph.now_Playing_movies.NOW_PLAYING_MOVIES) } )
            Spacer(modifier = Modifier.height(DpDimensions.Small))
            MovieListCell(navController = navController, state = stateUpcoming, headerTitle = "Lançamentos", onHeaderClick = {
                navController.navigate(AppGraph.upcoming_movies.UPCOMING_MOVIES) } )
            Spacer(modifier = Modifier.height(DpDimensions.Small))
               MovieListCell(navController = navController, state = stateRated, headerTitle = "Melhores avaliados", onHeaderClick = {
                   navController.navigate(AppGraph.rated_movies.RATED_MOVIES) } )
            Spacer(modifier = Modifier.height(DpDimensions.Small))
            Spacer(modifier = Modifier.height(60.dp))
        }
    }
}
