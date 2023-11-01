package com.example.moviesaandseries.presentation.movie_detail

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.moviesaandseries.R
import com.example.moviesaandseries.common.navigation.AppGraph
import com.example.moviesaandseries.domain.model.MovieOrSeriesFirebase
import com.example.moviesaandseries.domain.model.Response
import com.example.moviesaandseries.presentation.cast_grid.CastGridViewModel
import com.example.moviesaandseries.presentation.episode.ImagesCell
import com.example.moviesaandseries.presentation.favorites.FavoriteViewModel
import com.example.moviesaandseries.presentation.general.CrewCell
import com.example.moviesaandseries.presentation.general.MainContent
import com.example.moviesaandseries.presentation.general.ReviewsCell
import com.example.moviesaandseries.presentation.general.ShimmerDetail
import com.example.moviesaandseries.presentation.movie_list.MovieListState
import com.example.moviesaandseries.presentation.general.AppBarWithBackAndIcon
import com.example.moviesaandseries.presentation.general.CastCell
import com.example.moviesaandseries.presentation.general.CustomPadding
import com.example.moviesaandseries.presentation.general.DpDimensions
import com.example.moviesaandseries.presentation.general.UserData
import com.example.moviesaandseries.presentation.grid_movies.MoviesGridViewModel
import com.example.moviesaandseries.presentation.grid_series.SeriesGridViewModel
import com.example.moviesaandseries.presentation.movie_list.components.MovieListCell
import com.example.moviesaandseries.ui.theme.DarkGrey11
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun MovieDetailScreen(
    navController: NavController,
    isSystemInDarkTheme: Boolean,
    userData: UserData?,
    viewModel: MovieDetailViewModel = hiltViewModel(),
    favoriteViewModel: FavoriteViewModel = hiltViewModel(),
    castGridViewModel: CastGridViewModel,
    moviesGridViewModel: MoviesGridViewModel,
    seriesGridViewModel: SeriesGridViewModel
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

    val state = viewModel.state.value
    var stateSimilar: MovieListState
    var stateRecommendations: MovieListState
    val context = LocalContext.current
    var isFavorite: Boolean

    state.movie?.let { movie ->

        stateSimilar = MovieListState(movies = movie.similar.results)
        stateRecommendations = MovieListState(movies = movie.recommendations.results)

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

        var listOfMovies = emptyList<MovieOrSeriesFirebase>()
        when(val moviesResponse = favoriteViewModel.moviesResponse) {
            is Response.Loading -> ShimmerDetail()
            is Response.Success -> moviesResponse.data.let { movies ->
                listOfMovies = movies.filter { movieFirebase ->
                    movieFirebase.userId == userData?.userId && movieFirebase.title == movie.title
                }
                isFavorite = !listOfMovies.isNullOrEmpty()

                Scaffold(
                    topBar = {
                        AppBarWithBackAndIcon(title = movie.title,
                            backIcon = Icons.Default.ArrowBack,
                            icon = if (isFavorite) R.drawable.ic_boomarkfilled else R.drawable.ic_action_name,
                            onBackClick = {
                                navController.popBackStack()
                            },
                            onIconClick = {
                                try {
                                    if (isFavorite) {
                                        favoriteViewModel.deleteMovie(listOfMovies[0].idFirebase)
                                        Toast.makeText(context, "$title deletado com sucesso!!!", Toast.LENGTH_LONG).show()
                                    } else {
                                        favoriteViewModel.addMovie(movie.id, movie.title, movie.poster_path!!, "movie", userData!!.userId)
                                        Toast.makeText(context, "$title salvo com sucesso!!!", Toast.LENGTH_LONG).show()
                                    }
                                } catch (e: Exception) {
                                    Toast.makeText(context, "erro ao salvar $title!!!", Toast.LENGTH_LONG).show()
                                }
                            }
                        )
                    }
                ) { paddingValues ->
                    Column(
                        modifier = Modifier
                            .padding(paddingValues)
                            .verticalScroll(rememberScrollState())
                            .fillMaxSize()
                            .background(
                                color = if (useDarkIcons)
                                    Color.White else DarkGrey11
                            )
                    ) {
                        CustomPadding(
                            verticalPadding = 0.dp,
                            horizontalPadding = DpDimensions.Normal
                        ) {
                            MainContent(isVideo, logo, overview, url, data, movie.runtime.toString(), movie.vote_average, movie.genres)
                        }
                            if (!movie.credits.cast.isNullOrEmpty()) {
                                CastCell(navController = navController, cast = movie.credits.cast,  text =  "Elenco", castGridViewModel = castGridViewModel)
                            }
                            if (!movie.credits.crew.isNullOrEmpty()) {
                                CrewCell(director, isDirector = true, crew = movie.credits.crew)
                            }
                            if (!movie.recommendations.results.isNullOrEmpty()) {
                                MovieListCell(navController = navController, state = stateRecommendations, headerTitle = "Filmes Recomendados", onHeaderClick = {
                                    moviesGridViewModel.getMovies(stateRecommendations.movies)
                                    navController.navigate(AppGraph.movies_grid.MOVIES_GRID + "/recomendados") } )
                            }
                            if (!movie.similar.results.isNullOrEmpty()) {
                                MovieListCell(navController = navController, state = stateSimilar, headerTitle = "Filmes Similares", onHeaderClick = {
                                    moviesGridViewModel.getMovies(stateSimilar.movies)
                                    navController.navigate(AppGraph.movies_grid.MOVIES_GRID + "/similares") } )
                            }
                            if (!movie.reviews.results.isNullOrEmpty()) {
                                ReviewsCell(reviews = movie.reviews.results)
                            }
                            if (!movie.images.stills.isNullOrEmpty()) {
                                ImagesCell(images = movie.images.stills)
                            }
                    }
                }
            }
            is Response.Failure -> print(moviesResponse.e)
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
        )
    }
    if(state.isLoading) {
        ShimmerDetail()
    }
}