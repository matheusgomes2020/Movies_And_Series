package com.popcine.moviesaandseries.data.remote.dto.movies

import com.popcine.moviesaandseries.domain.model.Movie

data class MovieDto (
    val id: Int,
    val poster_path: String?,
    val overview: String?,
    val title: String,
    val vote_average: Double?,
)

fun MovieDto.toMovie(): Movie {
    return Movie(
        id = id,
        poster_path = poster_path,
        title = title,
        overview = overview,
        vote_average = vote_average
    )
}