package com.example.moviesaandseries.data.remote.dto.season

import com.example.moviesaandseries.data.remote.dto.episode.EpisodeDto
import com.example.moviesaandseries.domain.model.SeasonDetail

data class SeasonDetailDto (
    val air_date: String,
    val episode_count: Int,
    val episodes: List<EpisodeDto>,
    val id: Int,
    val name: String,
    val overview: String,
    val poster_path: String?,
    val season_number: Int,
    val vote_average: Double
)

fun SeasonDetailDto.toSeasonDetail(): SeasonDetail {
    return SeasonDetail(
        season_number = season_number,
        poster_path = poster_path,
        name = name,
        vote_average = vote_average,
        episode_count = episode_count,
        episodes = episodes,
        overview = overview
        )
}


