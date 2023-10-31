package com.example.moviesaandseries.presentation.cast_grid

import com.example.moviesaandseries.data.remote.dto.movies.Cast

data class CastGridState (
    val isLoading: Boolean = false,
    val cast: List<Cast> = emptyList(),
    val error: String = ""
)
