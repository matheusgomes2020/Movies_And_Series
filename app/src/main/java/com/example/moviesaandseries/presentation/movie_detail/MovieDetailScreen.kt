package com.example.moviesaandseries.presentation.movie_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import com.example.moviesaandseries.presentation.movie_list.MovieListState
import com.example.moviesaandseries.presentation.general.CastCell
import com.example.moviesaandseries.presentation.general.CrewCell
import com.example.moviesaandseries.presentation.general.MainContent
import com.example.moviesaandseries.presentation.general.ReviewsCell
import com.example.moviesaandseries.presentation.general.ShimmerDetail
import com.example.moviesaandseries.presentation.general.SimilarSeriesCell
import com.example.moviesaandseries.presentation.general.SimilarsMoviesCell


@Composable
fun MovieDetailScreen(
    navController: NavController,
    viewModel: MovieDetailViewModel = hiltViewModel()
){
    val state = viewModel.state.value
    var stateSimilar: MovieListState
    Box(modifier = Modifier.fillMaxSize()) {
        state.movie?.let { movie ->
            stateSimilar = MovieListState(movies = movie.similar.results)
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.White),
                contentPadding = PaddingValues(start = 15.dp, end = 15.dp, top = 15.dp)
            ) {
                val title = if (!movie.title.isNullOrEmpty()) movie.title else "sem título"
                val overview = if (!movie.overview.isNullOrEmpty()) movie.overview else "sem overview"
                val posterPath = if (!movie.poster_path.isNullOrEmpty()) movie.poster_path else "sem poster"
                val data = if (!movie.release_date.isNullOrEmpty()) movie.release_date else "null"
                var director = ""
                if (!movie.credits.crew.isNullOrEmpty()) {
                    for (i in movie.credits.crew) if ( i.job == "Director" ) director = i.name
                } else director = "Ninguém"
                val urlVideo = if ( !movie.videos.results.isNullOrEmpty() )  movie.videos.results[0].key else "sem trailer"
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
                    MainContent(isVideo,title, overview, url, data, movie.runtime.toString(), movie.vote_average, movie.genres)
                    if ( !movie.credits.cast.isNullOrEmpty() ) {
                        Spacer(modifier = Modifier.height( 16.dp ))
                        CastCell(navController, cast = movie.credits.cast, "Elenco")
                    }
                    if (!movie.credits.crew.isNullOrEmpty() ) {
                        Spacer(modifier = Modifier.height( 16.dp ))
                        CrewCell( director, crew = movie.credits.crew )
                    }
                    if (!movie.similar.results.isNullOrEmpty()) {
                        Spacer(modifier = Modifier.height( 16.dp ))
                        SimilarsMoviesCell(navController = navController, state = stateSimilar )
                    }
                    if (!movie.reviews.results.isNullOrEmpty()) {
                        Spacer(modifier = Modifier.height( 16.dp ))
                        ReviewsCell(reviews = movie.reviews.results)
                    }
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
            ShimmerDetail(isLoading = true, contentAfterLoading = { /*TODO*/ })
        }
    }
}