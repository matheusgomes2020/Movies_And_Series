package com.popcine.moviesaandseries.presentation.favorites

import com.popcine.moviesaandseries.domain.model.MovieOrSeriesFirebase


data class MovieFirebaseState (
    val isLoading: Boolean = false,
    val movies: List<MovieOrSeriesFirebase> = emptyList(),
    val error: String = ""
)
