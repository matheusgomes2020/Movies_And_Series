package com.example.moviesaandseries.data.remote.dto.series

data class TvCredits(
    val cast: List<SeriesWork>,
    val crew: List<CrewX>
)