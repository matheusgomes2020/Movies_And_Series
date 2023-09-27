package com.example.moviesaandseries.data.remote.dto.season

import com.example.moviesaandseries.data.remote.dto.movies.MovieDto
import com.example.moviesaandseries.domain.model.Movie
import com.example.moviesaandseries.domain.model.Season

data class SeasonDto(
    val poster_path: String,
    val season_number: Int
)
fun SeasonDto.toSeason(): Season {
    return Season(
        season_number = season_number,
        poster_path = poster_path,

    )
}
