package com.example.moviesaandseries.presentation.series_detail

import com.example.moviesaandseries.domain.model.SeriesDetail

data class SeriesDetailState (
    val isLoading: Boolean = false,
    val series: SeriesDetail? = null,
    val error: String = ""
)