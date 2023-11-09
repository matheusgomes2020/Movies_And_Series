package com.popcine.moviesaandseries.presentation.general

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.popcine.moviesaandseries.domain.model.MovieOrSeriesFirebase
import com.popcine.moviesaandseries.domain.model.Response
import com.popcine.moviesaandseries.domain.repository.Movies
import com.popcine.moviesaandseries.presentation.favorites.FavoriteViewModel

@Composable
fun Movies(
    userData: UserData?,
    type: String,
    viewModel: FavoriteViewModel = hiltViewModel(),
    moviesContent: @Composable ( movies: Movies ) -> Unit

) {

    if (type == "movies") {
        var listOfMovies = emptyList<MovieOrSeriesFirebase>()
        when(val moviesResponse = viewModel.moviesResponse) {
            is Response.Loading -> ProgressBar()
            is Response.Success -> moviesResponse.data.let { movies ->
                listOfMovies = movies.filter { movie ->
                    movie.userId == userData?.userId && movie.tipo == "movie"
                }
                moviesContent(listOfMovies)
            }
            is Response.Failure -> print(moviesResponse.e)
        }
    } else {
        var listOfMovies = emptyList<MovieOrSeriesFirebase>()
        when(val moviesResponse = viewModel.moviesResponse) {
            is Response.Loading -> ProgressBar()
            is Response.Success -> moviesResponse.data.let { movies ->
                listOfMovies = movies.filter { movie ->
                    movie.userId == userData?.userId && movie.tipo == "series"
                }
                moviesContent(listOfMovies)
            }
            is Response.Failure -> print(moviesResponse.e)
        }
    }

}