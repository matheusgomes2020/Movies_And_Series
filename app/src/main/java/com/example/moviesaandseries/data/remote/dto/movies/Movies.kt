package com.example.moviesaandseries.data.remote.dto.movies

data class Movies(
    val page: Int,
    val results: List<MovieDto>,
    val total_pages: Int,
    val total_results: Int
)