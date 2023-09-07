package com.example.moviesaandseries.presentation.season

import com.example.moviesaandseries.domain.model.SeasonDetail

data class SeasonDetailState (
    val isLoading: Boolean = false,
    val season: SeasonDetail? = null,
    val error: String = ""
)