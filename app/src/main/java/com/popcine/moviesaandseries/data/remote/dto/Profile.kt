package com.popcine.moviesaandseries.data.remote.dto

data class Profile(
    val aspect_ratio: Double,
    val file_path: String,
    val height: Int,
    val iso_639_1: Any,
    val vote_average: Double,
    val vote_count: Int,
    val width: Int
)