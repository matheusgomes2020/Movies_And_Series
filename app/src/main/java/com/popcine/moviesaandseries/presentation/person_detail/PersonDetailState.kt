package com.popcine.moviesaandseries.presentation.person_detail

import com.popcine.moviesaandseries.domain.model.Person

data class PersonDetailState(
    val isLoading: Boolean = false,
    val person: Person? = null,
    val error: String = ""
)