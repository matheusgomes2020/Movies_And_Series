package com.example.moviesaandseries.presentation.cast.castImage

import com.example.moviesaandseries.domain.model.Person

data class PersonImageState(
    val isLoading: Boolean = false,
    val image: String? = null,
    val error: String = ""
)
