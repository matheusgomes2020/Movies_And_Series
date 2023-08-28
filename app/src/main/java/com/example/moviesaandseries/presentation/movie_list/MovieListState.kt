package com.example.moviesaandseries.presentation.movie_list

import com.example.moviesaandseries.domain.model.Movie

data class MovieListState (
    val isLoading: Boolean = false,
    val movies: List<Movie> = emptyList(),
    val error: String = ""
)