package com.example.moviesaandseries.presentation.favorites

import com.example.moviesaandseries.domain.model.Movie
import com.example.moviesaandseries.domain.model.MovieFirebase


data class MovieFirebaseState (
    val isLoading: Boolean = false,
    val movies: List<MovieFirebase> = emptyList(),
    val error: String = ""
)
