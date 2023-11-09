package com.popcine.moviesaandseries.presentation.general

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.popcine.moviesaandseries.domain.model.Response
import com.popcine.moviesaandseries.presentation.favorites.FavoriteViewModel

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
