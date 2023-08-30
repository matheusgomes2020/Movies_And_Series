package com.example.moviesaandseries.data.remote.dto

data class SeriesS(
    val page: Int,
    val results: List<SeriesDto>,
    val total_pages: Int,
    val total_results: Int
)