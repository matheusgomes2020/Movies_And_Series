package com.example.moviesaandseries.presentation.favorites

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.moviesaandseries.domain.model.Response
import com.example.moviesaandseries.presentation.general.ProgressBar

@Composable
fun AddMovie(
    viewModel: FavoriteViewModel = hiltViewModel()
) {
    when (val addMovieResponse = viewModel.addMovieResponse) {
        is Response.Loading -> ProgressBar()
        is Response.Success -> Unit
        is Response.Failure -> print(addMovieResponse.e)
    }
}
