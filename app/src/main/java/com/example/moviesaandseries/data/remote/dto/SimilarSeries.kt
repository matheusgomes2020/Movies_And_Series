package com.example.moviesaandseries.data.remote.dto

import com.example.moviesaandseries.domain.model.Movie
import com.example.moviesaandseries.domain.model.Series

data class SimilarSeries(
    val page: Int,
    val results: List<Series>,
    val total_pages: Int,
    val total_results: Int
)