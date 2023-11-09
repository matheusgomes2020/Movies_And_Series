package com.popcine.moviesaandseries.presentation.series_detail

import com.popcine.moviesaandseries.domain.model.SeriesDetail

data class SeriesDetailState (
    val isLoading: Boolean = false,
    val series: SeriesDetail? = null,
    val error: String = ""
)