package com.example.moviesaandseries.presentation.newUI

import android.util.Log
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
import androidx.compose.material.MaterialTheme
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
import com.example.moviesaandseries.presentation.movie_list.MovieTrendingCell
import com.example.moviesaandseries.presentation.movie_list.MovieListViewModel
import com.example.moviesaandseries.presentation.movie_list.MovieNewUICell
import com.example.moviesaandseries.ui.theme.DarkGrey11
import com.example.moviesaandseries.ui.theme.MoviesAandSeriesTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@Composable
fun MoviesScreenNewUI(navController: NavController,
                      isSystemInDarkTheme: Boolean,
                      viewModel: MovieListViewModel = hiltViewModel()
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
            LogoAppBarWithTwoActions(
                icon1 = R.drawable.search,
                title = "Filmes",
                onLogoClick = {},
                onSearchClick = {
                    navController.navigate( AppGraph.search_movies.SEARCH_MOVIES + "/${"gameN"}" )
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
                        navController.navigate(AppGraph.trending_today_movies.TRENDING_TODAY_MOVIES)
                    }
                )
            }
            MovieTrendingCell(navController = navController, state = stateTrendingToday)


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
                items(genres) { genre ->
                    GenreItem(genre = genre, onClick = {
                        navController.navigate( AppGraph.movie_genres.GENRE_MOVIES + "/${"1"}/${genre.id}/${genre.title}" )
                    })
                }
            }
            Spacer(modifier = Modifier.height(DpDimensions.Small))
            CustomPadding(verticalPadding = 0.dp, horizontalPadding = DpDimensions.Normal) {
                SubtitleHeader(
                    title = "Em alta",
                    modifier = Modifier.fillMaxWidth(),
                    isSystemInDarkTheme = true,
                    onClick = {
                        navController.navigate(AppGraph.popular_movies.POPULAR_MOVIES)
                    }
                )
            }
            MovieNewUICell(navController = navController, state = statePopular)
            Spacer(modifier = Modifier.height(DpDimensions.Small))
            CustomPadding(verticalPadding = 0.dp, horizontalPadding = DpDimensions.Normal) {
                SubtitleHeader(
                    title = "Em cartaz",
                    modifier = Modifier.fillMaxWidth(),
                    isSystemInDarkTheme = true,
                    onClick = {
                        navController.navigate(AppGraph.now_Playing_movies.NOW_PLAYING_MOVIES)

                    }
                )
            }
            MovieNewUICell(navController = navController, state = stateNowPlaying)
            Spacer(modifier = Modifier.height(DpDimensions.Small))
            CustomPadding(verticalPadding = 0.dp, horizontalPadding = DpDimensions.Normal) {
                SubtitleHeader(
                    title = "Lançamentos",
                    modifier = Modifier.fillMaxWidth(),
                    isSystemInDarkTheme = true,
                    onClick = {
                        navController.navigate(AppGraph.upcoming_movies.UPCOMING_MOVIES)

                    }
                )
            }
            MovieNewUICell(navController = navController, state = stateUpcoming)
            Spacer(modifier = Modifier.height(DpDimensions.Small))
            CustomPadding(verticalPadding = 0.dp, horizontalPadding = DpDimensions.Normal) {
                SubtitleHeader(
                    title = "Melhores avaliados",
                    modifier = Modifier.fillMaxWidth(),
                    isSystemInDarkTheme = true,
                    onClick = {
                        navController.navigate(AppGraph.rated_movies.RATED_MOVIES)

                    }
                )
            }
            MovieNewUICell(navController = navController, state = stateRated)
            Spacer(modifier = Modifier.height(50.dp))
        }
    }
}


@Preview
@Composable
fun PreviewC(){
    MoviesAandSeriesTheme {
        MoviesScreenNewUI(rememberNavController(), false)
    }
}