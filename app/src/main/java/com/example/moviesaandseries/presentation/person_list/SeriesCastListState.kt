package com.example.moviesaandseries.presentation.person_list

import com.example.moviesaandseries.data.remote.dto.series.SeriesWork

data class SeriesCastListState (
    val isLoading: Boolean = false,
    val series: List<SeriesWork> = emptyList(),
    val error: String = ""
)
