package com.example.moviesaandseries.presentation.series_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.moviesaandseries.presentation.general.CastCell
import com.example.moviesaandseries.presentation.general.CrewCell
import com.example.moviesaandseries.presentation.general.MainContent
import com.example.moviesaandseries.presentation.general.ReviewsCell
import com.example.moviesaandseries.presentation.general.SeasonsCell
import com.example.moviesaandseries.presentation.general.ShimmerDetail
import com.example.moviesaandseries.presentation.general.SimilarSeriesCell
import com.example.moviesaandseries.presentation.season.SeasonListState
import com.example.moviesaandseries.presentation.series_list.SeriesListState

@Composable
fun SeriesDetailScreen(
    navController: NavController,
    viewModel: SeriesDetailViewModel = hiltViewModel()
){
    val state = viewModel.state.value
    var stateSimilar: SeriesListState
    var stateSeasons: SeasonListState
    Box(modifier = Modifier.fillMaxSize()) {
        state.series?.let { series ->
            stateSimilar = SeriesListState(series = series.similar.results)
            stateSeasons = SeasonListState(seasons = series.seasons)
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.White),
                contentPadding = PaddingValues(start = 15.dp, end = 15.dp, top = 15.dp)
            ) {
                val title = if (!series.name.isNullOrEmpty()) series.name else "sem título"
                val overview = if (!series.overview.isNullOrEmpty()) series.overview else "sem overview"
                val posterPath = if (!series.poster_path.isNullOrEmpty()) series.poster_path else "sem poster"
                val data = if (!series.first_air_date.isNullOrEmpty()) series.first_air_date else "null"
                var runtime = if (!series.episode_run_time.isNullOrEmpty())  series.episode_run_time[0].toString() else "null"
                var createdBy = ""
                if (!series.created_by.isNullOrEmpty()) {
                    for (i in series.created_by) {
                        createdBy += i.name + "\n"
                    }
                } else createdBy = "Ninguém"
                val urlVideo = if ( !series.videos.results.isNullOrEmpty() )  series.videos.results[0].key else "sem trailer"
                var url = ""
                var isVideo: Boolean
                if (urlVideo == "sem trailer"){
                    isVideo = false
                    url = posterPath
                } else {
                    isVideo = true
                    url = urlVideo
                }
                item {
                    MainContent(
                        isVideo,
                        title,
                        overview,
                        posterPath,
                        data,
                        runtime,
                        series.vote_average,
                        series.genres,
                    )
                    Spacer(modifier = Modifier.height( 15.dp ))
                    CastCell( navController = navController, cast = series.aggregate_credits.cast )
                    Spacer(modifier = Modifier.height( 15.dp ))
                    CrewCell(createdBy, crew = series.aggregate_credits.crew)
                    Spacer(modifier = Modifier.height( 15.dp ))
                    SeasonsCell(navController = navController,series.id.toString(), series.number_of_seasons, series.seasons, stateSeasons )
                    Spacer(modifier = Modifier.height( 15.dp ))
                    SimilarSeriesCell(navController = navController, state = stateSimilar )
                    Spacer(modifier = Modifier.height( 15.dp ))
                    if (!series.reviews.results.isNullOrEmpty()) ReviewsCell(reviews = series.reviews.results)
                }

            }
        }

        if ( state.error.isNotBlank() ) {
            Text(
                text = state.error,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }
        if(state.isLoading) {
            Column {
                Text(text = "Nada")
            }
            ShimmerDetail(isLoading = true, contentAfterLoading = { /*TODO*/ })
        }
    }
}