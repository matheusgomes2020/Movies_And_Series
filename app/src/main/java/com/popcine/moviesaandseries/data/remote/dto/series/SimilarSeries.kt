package com.popcine.moviesaandseries.data.remote.dto.series

import com.popcine.moviesaandseries.domain.model.Series

data class SimilarSeries(
    val page: Int,
    val results: List<Series>,
    val total_pages: Int,
    val total_results: Int
)