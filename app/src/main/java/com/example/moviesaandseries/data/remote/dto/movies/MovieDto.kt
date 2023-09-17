package com.example.moviesaandseries.data.remote.dto.movies

import com.example.moviesaandseries.data.remote.dto.BelongsToCollection
import com.example.moviesaandseries.data.remote.dto.Genre
import com.example.moviesaandseries.data.remote.dto.Images
import com.example.moviesaandseries.data.remote.dto.ProductionCompany
import com.example.moviesaandseries.data.remote.dto.ProductionCountry
import com.example.moviesaandseries.data.remote.dto.Reviews
import com.example.moviesaandseries.data.remote.dto.SpokenLanguage
import com.example.moviesaandseries.data.remote.dto.Videos
import com.example.moviesaandseries.domain.model.Movie

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