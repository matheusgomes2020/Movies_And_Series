package com.example.moviesaandseries.presentation.favorites

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.moviesaandseries.domain.model.MovieFirebase
import com.example.moviesaandseries.domain.model.Response
import com.example.moviesaandseries.domain.repository.Movies
import com.example.moviesaandseries.presentation.signIn.UserData

@Composable
fun Movies(
    userData: UserData?,
    viewModel: FavoriteViewModel = hiltViewModel(),
    moviesContent: @Composable ( movies: Movies ) -> Unit

) {
    var listOfMovies = emptyList<MovieFirebase>()
    when(val moviesResponse = viewModel.moviesResponse) {
        is Response.Loading -> ProgressBar()
        is Response.Success -> moviesResponse.data.let { movies ->
             listOfMovies = movies.filter { movie ->
                 movie.userId == userData?.userId
             }
            Log.d("FFFFIRRE", "LISTTAAAA: " + listOfMovies)
            moviesContent(listOfMovies)
        }
        is Response.Failure -> print(moviesResponse.e)
    }
}