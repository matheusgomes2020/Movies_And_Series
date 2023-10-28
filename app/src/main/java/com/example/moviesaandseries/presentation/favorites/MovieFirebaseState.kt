package com.example.moviesaandseries.presentation.favorites

import com.example.moviesaandseries.domain.model.MovieOrSeriesFirebase


data class MovieFirebaseState (
    val isLoading: Boolean = false,
    val movies: List<MovieOrSeriesFirebase> = emptyList(),
    val error: String = ""
)
