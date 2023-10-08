package com.example.moviesaandseries.presentation.person_Image

data class PersonImageState(
    val isLoading: Boolean = false,
    val image: String? = null,
    val error: String = ""
)
