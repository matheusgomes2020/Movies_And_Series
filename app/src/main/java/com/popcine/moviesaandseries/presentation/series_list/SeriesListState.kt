package com.popcine.moviesaandseries.presentation.series_list

import com.popcine.moviesaandseries.domain.model.Series

data class SeriesListState (
    val isLoading: Boolean = false,
    val series: List<Series> = emptyList(),
    val error: String = ""
)

