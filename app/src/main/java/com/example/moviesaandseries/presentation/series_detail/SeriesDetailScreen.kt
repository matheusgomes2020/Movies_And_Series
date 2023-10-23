package com.example.moviesaandseries.presentation.series_detail

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.moviesaandseries.domain.model.MovieOrSeriesFirebase
import com.example.moviesaandseries.domain.model.Response
import com.example.moviesaandseries.presentation.episode.ImagesCell
import com.example.moviesaandseries.presentation.favorites.FavoriteViewModel
import com.example.moviesaandseries.presentation.favorites.ProgressBar
import com.example.moviesaandseries.presentation.general.CastCell
import com.example.moviesaandseries.presentation.general.CrewCell
import com.example.moviesaandseries.presentation.general.MainContent
import com.example.moviesaandseries.presentation.general.RecommendationSeriesCell
import com.example.moviesaandseries.presentation.general.ReviewsCell
import com.example.moviesaandseries.presentation.general.SeasonsCell
import com.example.moviesaandseries.presentation.general.ShimmerDetail
import com.example.moviesaandseries.presentation.general.SimilarSeriesCell
import com.example.moviesaandseries.presentation.season_list.SeasonListState
import com.example.moviesaandseries.presentation.series_list.SeriesListState
import com.example.moviesaandseries.presentation.signIn.UserData
import com.example.moviesaandseries.ui.theme.BlueGrey11

@Composable
fun SeriesDetailScreen(
    navController: NavController,
    userData: UserData?,
    viewModel: SeriesDetailViewModel = hiltViewModel(),
    favoriteViewModel: FavoriteViewModel = hiltViewModel(),
){
    val state = viewModel.state.value
    var stateSimilar: SeriesListState
    var stateRecommendations: SeriesListState
    var stateSeasons: SeasonListState
    val context = LocalContext.current
    var isFavorite: Boolean
    Box(modifier = Modifier.fillMaxSize()) {
        state.series?.let { series ->
            stateSimilar = SeriesListState(series = series.similar.results)
            stateRecommendations = SeriesListState(series = series.recommendations.results)
            stateSeasons = SeasonListState(seasons = series.seasons)

            var listOfMovies = emptyList<MovieOrSeriesFirebase>()
            when(val moviesResponse = favoriteViewModel.moviesResponse) {
                is Response.Loading -> ProgressBar()
                is Response.Success -> moviesResponse.data.let { movies ->
                    listOfMovies = movies.filter { movieFirebase ->
                        movieFirebase.userId == userData?.userId && movieFirebase.title == series.name
                    }
                }
                is Response.Failure -> print(moviesResponse.e)
            }
            isFavorite = !listOfMovies.isNullOrEmpty()

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = if (isSystemInDarkTheme())  BlueGrey11 else Color.White),
                contentPadding = PaddingValues(start = 15.dp, end = 15.dp, top = 15.dp)
            ) {
                val title = if (!series.name.isNullOrEmpty()) series.name else "sem título"
                val overview = if (!series.overview.isNullOrEmpty()) series.overview else "sem overview"
                val posterPath = if (!series.backdrop_path.isNullOrEmpty()) series.backdrop_path else "sem poster"
                val data = if (!series.first_air_date.isNullOrEmpty()) series.first_air_date else "null"
                var runtime = if (!series.episode_run_time.isNullOrEmpty())  series.episode_run_time[0].toString() else "null"
                var createdBy = ""
                var logo = "sem logo"
                if ( !series.production_companies.isNullOrEmpty() ) {
                    if (!series.production_companies.get(0).logo_path.isNullOrEmpty()) {
                        logo = series.production_companies.get(0).logo_path
                    }
                }
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
                    MainContent(isVideo, isFavorite, logo, title, overview, posterPath, data, runtime, series.vote_average, series.genres, onCLickFavoriteButton = {

                        try {
                            if (isFavorite) {
                                favoriteViewModel.deleteMovie(listOfMovies[0].idFirebase)
                                Toast.makeText(context, "$title deletada com sucesso!!!", Toast.LENGTH_LONG).show()

                            } else {
                                favoriteViewModel.addMovie(series.id, series.name, series.poster_path!!, "series", userData!!.userId)
                                Toast.makeText(context, "$title salva com sucesso!!!", Toast.LENGTH_LONG).show()
                            }


                        }catch (e: Exception) {
                            Toast.makeText(context, "erro ao salvar $title!!!", Toast.LENGTH_LONG).show()
                        }



                    } )
                    if ( !series.seasons.isNullOrEmpty() ) {
                        Spacer(modifier = Modifier.height( 16.dp ))
                        SeasonsCell(navController = navController,series.id.toString(), series.number_of_seasons, series.seasons, stateSeasons )
                    }
                    if ( !series.aggregate_credits.cast.isNullOrEmpty() ) {
                        Spacer(modifier = Modifier.height( 16.dp ))
                        CastCell( navController = navController, cast = series.aggregate_credits.cast, "Elenco" )
                    }
                    if (!createdBy.isNullOrEmpty() && !series.aggregate_credits.crew.isNullOrEmpty() ) {
                        Spacer(modifier = Modifier.height( 16.dp ))
                        CrewCell(createdBy, crew = series.aggregate_credits.crew)
                    }
                    if (!series.recommendations.results.isNullOrEmpty()) {
                        Spacer(modifier = Modifier.height( 16.dp ))
                        RecommendationSeriesCell(navController = navController, state = stateRecommendations )
                    }
                    if (!series.similar.results.isNullOrEmpty()) {
                        Spacer(modifier = Modifier.height( 16.dp ))
                        SimilarSeriesCell(navController = navController, state = stateSimilar )
                    }
                    if (!series.reviews.results.isNullOrEmpty()) {
                        Spacer(modifier = Modifier.height( 16.dp ))
                        ReviewsCell(reviews = series.reviews.results)
                    }
                    if (!series.images.stills.isNullOrEmpty()) {
                        Spacer(modifier = Modifier.height( 16.dp ))
                        ImagesCell(images = series.images.stills)
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