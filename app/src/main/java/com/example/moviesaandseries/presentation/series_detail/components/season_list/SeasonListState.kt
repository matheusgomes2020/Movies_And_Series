package com.example.moviesaandseries.presentation.series_detail.components.season_list

import com.example.moviesaandseries.data.remote.dto.season.SeasonDto

data class SeasonListState (
    val isLoading: Boolean = false,
    val seasons: List<SeasonDto> = emptyList(),
    val error: String = ""
)
