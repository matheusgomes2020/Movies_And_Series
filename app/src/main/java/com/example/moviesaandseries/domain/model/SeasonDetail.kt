package com.example.moviesaandseries.domain.model

import com.example.moviesaandseries.data.remote.dto.season.Episode

data class SeasonDetail (
    val episode_count: Int,
    val episodes: List<Episode>,
    val name: String,
    val overview: String,
    val poster_path: String,
    val season_number: Int,
    val vote_average: Double
)
