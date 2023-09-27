package com.example.moviesaandseries.data.remote.dto.series

data class SeriesList(
    val page: Int,
    val results: List<SeriesDto>,
    val total_pages: Int,
    val total_results: Int
)