package com.popcine.moviesaandseries.presentation.series_detail

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
import com.popcine.moviesaandseries.R
import com.popcine.moviesaandseries.common.navigation.AppGraph
import com.popcine.moviesaandseries.domain.model.MovieOrSeriesFirebase
import com.popcine.moviesaandseries.domain.model.Response
import com.popcine.moviesaandseries.presentation.cast_grid.CastGridViewModel
import com.popcine.moviesaandseries.presentation.episode.ImagesCell
import com.popcine.moviesaandseries.presentation.favorites.FavoriteViewModel
import com.popcine.moviesaandseries.presentation.general.CrewCell
import com.popcine.moviesaandseries.presentation.general.MainContent
import com.popcine.moviesaandseries.presentation.general.ReviewsCell
import com.popcine.moviesaandseries.presentation.general.ShimmerDetail
import com.popcine.moviesaandseries.presentation.general.AppBarWithBackAndIcon
import com.popcine.moviesaandseries.presentation.general.CastCell
import com.popcine.moviesaandseries.presentation.general.CustomPadding
import com.popcine.moviesaandseries.presentation.general.DpDimensions
import com.popcine.moviesaandseries.presentation.series_detail.components.season_list.SeasonListState
import com.popcine.moviesaandseries.presentation.series_list.SeriesListState
import com.popcine.moviesaandseries.presentation.general.UserData
import com.popcine.moviesaandseries.presentation.grid_series.SeriesGridViewModel
import com.popcine.moviesaandseries.presentation.series_detail.components.SeasonsCell
import com.popcine.moviesaandseries.presentation.series_list.components.SeriesListCell
import com.popcine.moviesaandseries.ui.theme.DarkGrey11
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun SeriesDetailScreen(
    navController: NavController,
    isSystemInDarkTheme: Boolean,
    userData: UserData?,
    viewModel: SeriesDetailViewModel = hiltViewModel(),
    favoriteViewModel: FavoriteViewModel = hiltViewModel(),
    castGridViewModel: CastGridViewModel,
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
    var stateSimilar: SeriesListState
    var stateRecommendations: SeriesListState
    var stateSeasons: SeasonListState
    val context = LocalContext.current
    var isFavorite: Boolean

    state.series?.let { series ->
        stateSimilar = SeriesListState(series = series.similar.results)
        stateRecommendations = SeriesListState(series = series.recommendations.results)
        stateSeasons = SeasonListState(seasons = series.seasons)

        val title = if (!series.name.isNullOrEmpty()) series.name else "sem título"
        val overview = if (!series.overview.isNullOrEmpty()) series.overview else "sem overview"
        val posterPath =
            if (!series.backdrop_path.isNullOrEmpty()) series.backdrop_path else "sem poster"
        val data = if (!series.first_air_date.isNullOrEmpty()) series.first_air_date else "null"
        var runtime =
            if (!series.episode_run_time.isNullOrEmpty()) series.episode_run_time[0].toString() else "null"
        var createdBy = ""
        var logo = "sem logo"
        if (!series.production_companies.isNullOrEmpty()) {
            if (!series.production_companies.get(0).logo_path.isNullOrEmpty()) {
                logo = series.production_companies.get(0).logo_path
            }
        }
        if (!series.created_by.isNullOrEmpty()) {
            for (i in series.created_by) {
                createdBy += i.name + "\n"
            }
        } else createdBy = "Ninguém"
        val urlVideo =
            if (!series.videos.results.isNullOrEmpty()) series.videos.results[0].key else "sem trailer"
        var url = ""
        var isVideo: Boolean
        if (urlVideo == "sem trailer") {
            isVideo = false
            url = posterPath
        } else {
            isVideo = true
            url = urlVideo
        }

        var listOfMovies = emptyList<MovieOrSeriesFirebase>()
        when (val moviesResponse = favoriteViewModel.moviesResponse) {
            is Response.Loading -> ShimmerDetail()
            is Response.Success -> moviesResponse.data.let { movies ->
                listOfMovies = movies.filter { movieFirebase ->
                    movieFirebase.userId == userData?.userId && movieFirebase.title == series.name
                }
                isFavorite = !listOfMovies.isNullOrEmpty()

                Scaffold(
                    topBar = {
                        AppBarWithBackAndIcon(title = series.name,
                            backIcon = Icons.Default.ArrowBack,
                            icon = if (isFavorite) R.drawable.ic_boomarkfilled else R.drawable.ic_action_name,
                            onBackClick = {
                                navController.popBackStack()
                            },
                            onIconClick = {
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
                            MainContent(isVideo, logo, overview, posterPath, data, runtime, series.vote_average, series.genres)
                        }
                            if ( !series.seasons.isNullOrEmpty() ) {
                                SeasonsCell(navController = navController,series.id.toString(), series.number_of_seasons, series.seasons, stateSeasons )
                            }
                            if ( !series.aggregate_credits.cast.isNullOrEmpty() ) {
                                CastCell( navController = navController, cast = series.aggregate_credits.cast, text = "Elenco", castGridViewModel = castGridViewModel)
                            }
                            if (!createdBy.isNullOrEmpty() && !series.aggregate_credits.crew.isNullOrEmpty() ) {
                                    CrewCell(createdBy, isDirector = false, crew = series.aggregate_credits.crew)
                            }
                            if (!series.recommendations.results.isNullOrEmpty()) {
                                SeriesListCell(navController = navController, state = stateRecommendations, "Séries Recomendadas", onHeaderClick = {
                                    seriesGridViewModel.getSeries(stateRecommendations.series)
                                    navController.navigate(AppGraph.series_grid.SERIES_GRID + "/recomendadas") } )
                            }
                            if (!series.similar.results.isNullOrEmpty()) {
                                SeriesListCell(navController = navController, state = stateSimilar, "Séries Similares", onHeaderClick = {
                                    seriesGridViewModel.getSeries(stateSimilar.series)
                                    navController.navigate(AppGraph.series_grid.SERIES_GRID + "/similares") } )
                            }
                            if (!series.reviews.results.isNullOrEmpty()) {
                                ReviewsCell(reviews = series.reviews.results)
                            }
                            if (!series.images.stills.isNullOrEmpty()) {
                                ImagesCell(images = series.images.stills)
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