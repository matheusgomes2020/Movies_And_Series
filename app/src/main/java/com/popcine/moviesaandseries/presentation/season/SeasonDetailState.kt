package com.popcine.moviesaandseries.presentation.season

import com.popcine.moviesaandseries.domain.model.SeasonDetail

data class SeasonDetailState (
    val isLoading: Boolean = false,
    val season: SeasonDetail? = null,
    val error: String = ""
)