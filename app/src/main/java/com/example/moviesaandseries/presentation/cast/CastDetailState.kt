package com.example.moviesaandseries.presentation.cast

import com.example.moviesaandseries.domain.model.Person

data class CastDetailState(
    val isLoading: Boolean = false,
    val person: Person? = null,
    val error: String = ""
)