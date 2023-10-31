package com.example.moviesaandseries.presentation.grid_movies

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.moviesaandseries.domain.model.Movie

class SharedMoviesGridViewModel: ViewModel() {

    var movies by mutableStateOf<List<Movie>?>(null)
        private set

    fun getMovies(newMovie: List<Movie>) {
        movies = newMovie
    }

}
