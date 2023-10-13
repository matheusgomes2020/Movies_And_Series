package com.example.moviesaandseries.presentation.movie_detail

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.moviesaandseries.domain.model.Response
import com.example.moviesaandseries.presentation.favorites.FavoriteViewModel
import com.example.moviesaandseries.presentation.favorites.ProgressBar

@Composable
fun AddBMovie(
    viewModel: FavoriteViewModel = hiltViewModel()
) {
    when(val addBookResponse = viewModel.addMovieResponse) {
        is Response.Loading -> ProgressBar()
        is Response.Success -> Unit
        is Response.Failure -> print(addBookResponse.e)
    }
}