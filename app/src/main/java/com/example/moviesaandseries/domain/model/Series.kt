package com.example.moviesaandseries.domain.model

data class Series(
    var id: Int,
    val poster_path: String?,
    val name: String,
    var overview: String?,
    var userId: String = "",
    var idFirebase: String = "",
    val vote_average: Double?,
)
