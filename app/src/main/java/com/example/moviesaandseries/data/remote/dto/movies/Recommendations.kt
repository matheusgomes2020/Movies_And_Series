package com.example.moviesaandseries.data.remote.dto.movies

import com.example.moviesaandseries.domain.model.Movie

data class Recommendations(
    val page: Int,
    val results: List<Movie>,
    val total_pages: Int,
    val total_results: Int
)