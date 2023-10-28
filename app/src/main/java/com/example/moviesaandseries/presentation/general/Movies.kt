package com.example.moviesaandseries.presentation.general

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.moviesaandseries.domain.model.MovieOrSeriesFirebase
import com.example.moviesaandseries.domain.model.Response
import com.example.moviesaandseries.domain.repository.Movies
import com.example.moviesaandseries.presentation.favorites.FavoriteViewModel

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