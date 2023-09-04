package com.example.moviesaandseries.presentation.season

import com.example.moviesaandseries.data.remote.dto.Season
import com.example.moviesaandseries.domain.model.Series

data class SeasonListState (
    val isLoading: Boolean = false,
    val seasons: List<Season> = emptyList(),
    val error: String = ""
)
