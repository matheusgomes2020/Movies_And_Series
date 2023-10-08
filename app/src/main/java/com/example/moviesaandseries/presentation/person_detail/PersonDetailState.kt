package com.example.moviesaandseries.presentation.person_detail

import com.example.moviesaandseries.domain.model.Person

data class PersonDetailState(
    val isLoading: Boolean = false,
    val person: Person? = null,
    val error: String = ""
)