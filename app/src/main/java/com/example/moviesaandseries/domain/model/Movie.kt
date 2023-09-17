package com.example.moviesaandseries.domain.model

data class Movie(
    var id: Int,
    val poster_path: String?,
    val title: String,
    var userId: String = "",
    var overview: String?,
    var idFirebase: String = "",
    val vote_average: Double?,
)
