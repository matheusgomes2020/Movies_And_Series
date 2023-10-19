package com.example.moviesaandseries.presentation.movies_genres


import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.moviesaandseries.R
import com.example.moviesaandseries.common.navigation.AppGraph
import com.example.moviesaandseries.presentation.newUI.AppBarWithBack
import com.example.moviesaandseries.presentation.newUI.DpDimensions
import com.example.moviesaandseries.presentation.newUI.LogoAppBarWithTwoActions
import com.example.moviesaandseries.presentation.newUI.MovieItemNewUi
import com.example.moviesaandseries.ui.theme.DarkGrey11


@Composable
fun MovieGenresScreen(
    genreName: String,
    navController: NavController,
    moviesGenresViewModel: MoviesGenresViewModel = hiltViewModel()
) {
    val state = moviesGenresViewModel.state.value

    Scaffold(
        topBar = {
            AppBarWithBack(title = genreName, backIcon = Icons.Default.ArrowBack, onBackClick = {
                navController.popBackStack()
            } )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                //.verticalScroll(rememberScrollState())
                .fillMaxSize()
                .background(
                    color = if (isSystemInDarkTheme())
                        DarkGrey11 else Color.White
                )
        ) {
            state.movies?.let { movies ->
                LazyVerticalGrid(columns = GridCells.Fixed( 2 ),
                    modifier = Modifier.fillMaxSize(),

                    horizontalArrangement = Arrangement.spacedBy(DpDimensions.Small),
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(DpDimensions.Small)
                ) {

                    items( movies ) { movie ->
                        MovieItemNewUi(movie = movie, onClick = {
                            navController.navigate(AppGraph.movies_details.DETAILS + "/${movie.id}")
                        } )
                    }

                }
            }

        }


    }
}