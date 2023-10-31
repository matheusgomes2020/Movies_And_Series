package com.example.moviesaandseries.presentation.grid_movies

import android.util.Log
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.moviesaandseries.common.navigation.AppGraph
import com.example.moviesaandseries.presentation.general.AppBarWithBack
import com.example.moviesaandseries.presentation.general.DpDimensions
import com.example.moviesaandseries.presentation.general.PersonListItem
import com.example.moviesaandseries.presentation.movie_list.components.MovieListItem
import com.example.moviesaandseries.ui.theme.DarkGrey11

@Composable
fun MoviesGridScreen(
    navController: NavController,
    sharedMoviesGridViewModel: SharedMoviesGridViewModel

) {

    val movies = sharedMoviesGridViewModel.movies

    Scaffold(
        topBar = {
            AppBarWithBack(title = "Filmes", backIcon = Icons.Default.ArrowBack, onBackClick = {
                navController.popBackStack()
            })
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


            if (movies!=null) {


                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        modifier = Modifier.fillMaxSize(),

                        horizontalArrangement = Arrangement.spacedBy(DpDimensions.Small),
                        contentPadding = PaddingValues(horizontal = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(DpDimensions.Small)
                    ) {

                        items(movies!!) { movie ->
                            MovieListItem(movie = movie, onClick = {
                                navController.navigate(AppGraph.movies_details.DETAILS + "/${movie.id}")
                            },
                                height = 240.dp)
                        }
                    }

            } else {
                Text(text = "erro")
            }
        }
    }

}