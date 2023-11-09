package com.popcine.moviesaandseries.presentation.movie_list

import com.popcine.moviesaandseries.domain.model.Movie

data class MovieListState (
    val isLoading: Boolean = false,
    val movies: List<Movie> = emptyList(),
    val error: String = ""
)