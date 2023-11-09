package com.popcine.moviesaandseries.domain.model

import com.popcine.moviesaandseries.data.remote.dto.episode.EpisodeDto

data class SeasonDetail (
    val episode_count: Int,
    val episodes: List<EpisodeDto>,
    val name: String,
    val overview: String,
    val poster_path: String?,
    val season_number: Int,
    val vote_average: Double
)
