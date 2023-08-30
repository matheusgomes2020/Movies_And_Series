package com.example.moviesaandseries.presentation.movie_list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController


@Composable
fun MovieListScreen(
    navController: NavController,
    viewModel: MovieListViewModel = hiltViewModel()
){

    val state = viewModel.statePopular.value
    val state2 = viewModel.stateUpcoming.value
    val state3 = viewModel.stateNowPlaying.value
    val state4 = viewModel.stateRated.value

    Box(modifier = Modifier.fillMaxSize()) {
        state.movies?.let { movies ->
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(start = 25.dp, end = 25.dp)
            ) {
                item {
                    Column {
                        Text(text = "Filmes em alta",
                            modifier = Modifier
                                .padding(top = 15.dp, bottom = 15.dp),
                            fontSize = 18.sp,
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Bold
                        )
                        //Spacer(modifier = Modifier.height(10.dp))
                        MovieListScreenCell(navController = navController , state = state)
                        Text(text = "Filmes em cartaz",
                            modifier = Modifier
                                .padding(top = 15.dp, bottom = 15.dp),
                            fontSize = 18.sp,
                            style = MaterialTheme.typography.labelLarge,
                            fontWeight = FontWeight.Bold
                        )
                        //Spacer(modifier = Modifier.height(10.dp))
                        MovieListScreenCell(navController = navController , state = state3)
                        Text(text = "Filmes em lançamentos",
                            modifier = Modifier
                                .padding(top = 15.dp, bottom = 15.dp),
                            fontSize = 18.sp,
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold
                        )
                        //Spacer(modifier = Modifier.height(10.dp))
                        MovieListScreenCell(navController = navController , state = state2)
                        Text(text = "Filmes melhores avaliados",
                            modifier = Modifier
                                .padding(top = 15.dp, bottom = 15.dp),
                            fontSize = 18.sp,
                            style = MaterialTheme.typography.displayMedium,
                            fontWeight = FontWeight.Bold
                        )
                        MovieListScreenCell(navController = navController , state = state4)
                    }

                }

            }

        }

    }
}


