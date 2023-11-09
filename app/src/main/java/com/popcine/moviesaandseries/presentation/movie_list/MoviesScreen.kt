package com.popcine.moviesaandseries.presentation.movie_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.popcine.moviesaandseries.R
import com.popcine.moviesaandseries.common.navigation.AppGraph
import com.popcine.moviesaandseries.presentation.general.DpDimensions
import com.popcine.moviesaandseries.presentation.general.MainAppBar
import com.popcine.moviesaandseries.presentation.grid_movies.MoviesGridViewModel
import com.popcine.moviesaandseries.presentation.movie_list.components.GenresCell
import com.popcine.moviesaandseries.presentation.movie_list.components.MovieListCell
import com.popcine.moviesaandseries.presentation.movie_list.components.MovieTrendingCell
import com.popcine.moviesaandseries.ui.theme.DarkGrey11
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@Composable
fun MoviesScreen(navController: NavController, isSystemInDarkTheme: Boolean, viewModel: MovieListViewModel = hiltViewModel(), moviesGridViewModel: MoviesGridViewModel
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
                moviesGridViewModel.getMovies(stateTrendingToday.movies)
                navController.navigate(AppGraph.movies_grid.MOVIES_GRID + "/em tendência hoje") })
            Spacer(modifier = Modifier.height(DpDimensions.Small))
            GenresCell(navController = navController, isGenresMovies = true, onHeaderClick =  {})
            Spacer(modifier = Modifier.height(DpDimensions.Small))
            MovieListCell(navController = navController, state = statePopular, headerTitle = "Em alta", onHeaderClick = {
                moviesGridViewModel.getMovies(statePopular.movies)
                navController.navigate(AppGraph.movies_grid.MOVIES_GRID + "/em alta") } )
            Spacer(modifier = Modifier.height(DpDimensions.Small))
            MovieListCell(navController = navController, state = stateNowPlaying, headerTitle = "Em cartaz", onHeaderClick = {
                moviesGridViewModel.getMovies(stateNowPlaying.movies)
                navController.navigate(AppGraph.movies_grid.MOVIES_GRID + "/em cartaz") } )
            Spacer(modifier = Modifier.height(DpDimensions.Small))
            MovieListCell(navController = navController, state = stateUpcoming, headerTitle = "Lançamentos", onHeaderClick = {
                moviesGridViewModel.getMovies(stateUpcoming.movies)
                navController.navigate(AppGraph.movies_grid.MOVIES_GRID + "/em lançamento") } )
            Spacer(modifier = Modifier.height(DpDimensions.Small))
               MovieListCell(navController = navController, state = stateRated, headerTitle = "Melhores avaliados", onHeaderClick = {
                   moviesGridViewModel.getMovies(stateRated.movies)
                   navController.navigate(AppGraph.movies_grid.MOVIES_GRID + "/melhores avaliados") } )
            Spacer(modifier = Modifier.height(DpDimensions.Small))
            Spacer(modifier = Modifier.height(60.dp))
        }
    }
}
