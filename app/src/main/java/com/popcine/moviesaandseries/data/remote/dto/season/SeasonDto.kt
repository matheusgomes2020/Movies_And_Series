package com.popcine.moviesaandseries.data.remote.dto.season

import com.popcine.moviesaandseries.domain.model.Season

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