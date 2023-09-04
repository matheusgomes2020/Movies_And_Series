package com.example.moviesaandseries.presentation.series_list

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
fun SeriesListScreen(
    navController: NavController,
    viewModel: SeriesListViewModel = hiltViewModel()
){

    val statePopular = viewModel.statePopular.value
    val stateOnAir = viewModel.stateOnAir.value
    val stateAiringToday = viewModel.stateAiryngToday.value
    val stateRated = viewModel.stateRated.value

    Box(modifier = Modifier.fillMaxSize()) {
        statePopular.series?.let { series ->
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(start = 25.dp, end = 25.dp)
            ) {
                item {
                    Column {

                        Text(text = "Séries no ar hoje",
                            modifier = Modifier
                                .padding(top = 15.dp, bottom = 15.dp),
                            fontSize = 18.sp,
                            style = MaterialTheme.typography.labelLarge,
                            fontWeight = FontWeight.Bold
                        )
                        //Spacer(modifier = Modifier.height(10.dp))
                        SeriesListScreenCell(navController = navController , state = stateAiringToday)
                        Text(text = "Séries no ar",
                            modifier = Modifier
                                .padding(top = 15.dp, bottom = 15.dp),
                            fontSize = 18.sp,
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold
                        )
                        //Spacer(modifier = Modifier.height(10.dp))
                        SeriesListScreenCell(navController = navController , state = stateOnAir)
                        Text(text = "Séries populares",
                            modifier = Modifier
                                .padding(top = 15.dp, bottom = 15.dp),
                            fontSize = 18.sp,
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Bold
                        )
                        //Spacer(modifier = Modifier.height(10.dp))
                        SeriesListScreenCell(navController = navController , state = statePopular)
                        Text(text = "Séries melhores avaliadoa",
                            modifier = Modifier
                                .padding(top = 15.dp, bottom = 15.dp),
                            fontSize = 18.sp,
                            style = MaterialTheme.typography.displayMedium,
                            fontWeight = FontWeight.Bold
                        )
                        SeriesListScreenCell(navController = navController , state = stateRated)
                    }

                }

            }

        }

    }
}




