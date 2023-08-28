package com.example.moviesaandseries.presentation.movie_detail

import com.example.moviesaandseries.domain.model.MovieDetail

data class MovieDetailState (
    val isLoading: Boolean = false,
    val movie: MovieDetail? = null,
    val error: String = ""
)