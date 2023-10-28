package com.example.moviesaandseries.data.remote.dto.series

import com.example.moviesaandseries.domain.model.Series

data class TvCredits(
    val cast: List<Series>,
    val crew: List<CrewX>
)