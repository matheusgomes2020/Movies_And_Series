package com.popcine.moviesaandseries.data.remote.dto.series

import com.popcine.moviesaandseries.domain.model.Series

data class TvCredits(
    val cast: List<Series>,
    val crew: List<CrewX>
)