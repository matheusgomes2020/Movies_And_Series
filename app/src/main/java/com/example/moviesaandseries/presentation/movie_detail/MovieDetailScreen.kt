package com.example.moviesaandseries.presentation.movie_detail

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
import com.example.moviesaandseries.presentation.movie_list.MovieListState
import com.example.moviesaandseries.presentation.general.CastCell
import com.example.moviesaandseries.presentation.general.CrewCell
import com.example.moviesaandseries.presentation.general.MainContent
import com.example.moviesaandseries.presentation.general.RecommendationMoviesCell
import com.example.moviesaandseries.presentation.general.ReviewsCell
import com.example.moviesaandseries.presentation.general.ShimmerDetail
import com.example.moviesaandseries.presentation.general.SimilarsMoviesCell
import com.example.moviesaandseries.presentation.signIn.UserData
import com.example.moviesaandseries.ui.theme.BlueGrey11


@Composable
fun MovieDetailScreen(
    navController: NavController,
    userData: UserData?,
    viewModel: MovieDetailViewModel = hiltViewModel(),
    favoriteViewModel: FavoriteViewModel = hiltViewModel(),
    //addMovie:(id: Int, title: String, posterPath: String, userId: String) -> Unit
){
    val state = viewModel.state.value
    var stateSimilar: MovieListState
    var stateRecommendations: MovieListState
    val context = LocalContext.current
    var isFavorite: Boolean
    Box(modifier = Modifier.fillMaxSize()
    ) {
        state.movie?.let { movie ->
            stateSimilar = MovieListState(movies = movie.similar.results)
            stateRecommendations = MovieListState(movies = movie.recommendations.results)

            var listOfMovies = emptyList<MovieOrSeriesFirebase>()
            when(val moviesResponse = favoriteViewModel.moviesResponse) {
                is Response.Loading -> ProgressBar()
                is Response.Success -> moviesResponse.data.let { movies ->
                    listOfMovies = movies.filter { movieFirebase ->
                        movieFirebase.userId == userData?.userId && movieFirebase.title == movie.title
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
                val title = if (!movie.title.isNullOrEmpty()) movie.title else "sem título"
                val overview = if (!movie.overview.isNullOrEmpty()) movie.overview else "sem overview"
                val posterPath = if (!movie.backdrop_path.isNullOrEmpty()) movie.backdrop_path else "sem poster"
                val data = if (!movie.release_date.isNullOrEmpty()) movie.release_date else "null"
                var director = ""
                var logo = "sem logo"
                if ( !movie.production_companies.isNullOrEmpty() ) {
                    if (!movie.production_companies.get(0).logo_path.isNullOrEmpty()) {
                        logo = movie.production_companies.get(0).logo_path
                    }
                }
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
                    MainContent(isVideo, isFavorite, logo, title, overview, url, data, movie.runtime.toString(), movie.vote_average, movie.genres, onCLickFavoriteButton = {
                        try {
                            if (isFavorite) {
                                favoriteViewModel.deleteMovie(listOfMovies[0].idFirebase)
                                Toast.makeText(context, "$title deletado com sucesso!!!", Toast.LENGTH_LONG).show()

                            } else {
                                favoriteViewModel.addMovie(movie.id, movie.title, movie.poster_path!!, "movie", userData!!.userId)
                                Toast.makeText(context, "$title salvo com sucesso!!!", Toast.LENGTH_LONG).show()
                            }


                        }catch (e: Exception) {
                            Toast.makeText(context, "erro ao salvar $title!!!", Toast.LENGTH_LONG).show()
                        }
                    })

                    if ( !movie.credits.cast.isNullOrEmpty() ) {
                        Spacer(modifier = Modifier.height( 16.dp ))
                        CastCell(navController, cast = movie.credits.cast, "Elenco")
                    }
                    if (!movie.credits.crew.isNullOrEmpty() ) {
                        Spacer(modifier = Modifier.height( 16.dp ))
                        CrewCell( director, crew = movie.credits.crew )
                    }
                    if (!movie.recommendations.results.isNullOrEmpty()) {
                        Spacer(modifier = Modifier.height( 16.dp ))
                        RecommendationMoviesCell(navController = navController, state = stateRecommendations )
                    }
                    if (!movie.similar.results.isNullOrEmpty()) {
                        Spacer(modifier = Modifier.height( 16.dp ))
                        SimilarsMoviesCell(navController = navController, state = stateSimilar )
                    }
                    if (!movie.reviews.results.isNullOrEmpty()) {
                        Spacer(modifier = Modifier.height( 16.dp ))
                        ReviewsCell(reviews = movie.reviews.results)
                    }
                    if (!movie.images.stills.isNullOrEmpty()) {
                        Spacer(modifier = Modifier.height( 16.dp ))
                        ImagesCell(images = movie.images.stills)
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