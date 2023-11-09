package com.popcine.moviesaandseries.presentation.movie_detail

import com.popcine.moviesaandseries.domain.model.MovieDetail

data class MovieDetailState (
    val isLoading: Boolean = false,
    val movie: MovieDetail? = null,
    val error: String = ""
)