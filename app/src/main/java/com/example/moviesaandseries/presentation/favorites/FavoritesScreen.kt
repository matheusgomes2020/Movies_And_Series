package com.example.moviesaandseries.presentation.favorites

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.moviesaandseries.R
import com.example.moviesaandseries.common.navigation.AppGraph
import com.example.moviesaandseries.presentation.movie_list.components.MovieNewUICellFirebase
import com.example.moviesaandseries.presentation.general.CustomPadding
import com.example.moviesaandseries.presentation.general.DpDimensions
import com.example.moviesaandseries.presentation.general.MainAppBar
import com.example.moviesaandseries.presentation.general.Movies
import com.example.moviesaandseries.presentation.general.SubtitleHeader
import com.example.moviesaandseries.presentation.general.UserData
import com.example.moviesaandseries.ui.theme.DarkGrey11
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    navController: NavController,
    isSystemInDarkTheme: Boolean,
    userData: UserData?,
    onSignOut: () -> Unit,
    viewModel: FavoriteViewModel = hiltViewModel(),
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

    Scaffold(
        topBar = {
            MainAppBar(icon1 = R.drawable.ic_star,
                isProfileScreen = true,
                title = "Favoritos",
                imageUrl = if (userData?.profilePictureUrl != null) userData.profilePictureUrl else "sem imagem",
                onLogoClick = {},
                onSearchClick = {
                    navController.navigate(AppGraph.account_details.ACCOUNT_DETAILS)
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
                    title = "Filmes favoritos",
                    modifier = Modifier.fillMaxWidth(),
                    isSystemInDarkTheme = true,
                    onClick = {
                        //navController.navigate(AppGraph.trending_today_movies.TRENDING_TODAY_MOVIES)
                    }
                )
            }
                Movies(userData = userData,
                    type = "movies"
                ) { movies ->
                    MovieNewUICellFirebase(
                        navController = navController,
                        movies = movies,
                        tipo = "movie",
                    ) { idFirebase ->
                        viewModel.deleteMovie(idFirebase)
                    }
            }
            Spacer(modifier = Modifier.height(DpDimensions.Small))
            CustomPadding(verticalPadding = 0.dp, horizontalPadding = DpDimensions.Normal) {
                SubtitleHeader(
                    title = "Séries favoritas",
                    modifier = Modifier.fillMaxWidth(),
                    isSystemInDarkTheme = true,
                    onClick = {  }
                )
            }
                Movies(userData = userData,
                    type = "series"
                ) { movies ->
                    MovieNewUICellFirebase(
                        navController = navController,
                        movies = movies,
                        tipo = "series",
                    ) { idFirebase ->
                        viewModel.deleteMovie(idFirebase)
                    }
                }
        }
    }
}


