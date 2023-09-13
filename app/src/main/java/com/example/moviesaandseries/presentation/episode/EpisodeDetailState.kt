package com.example.moviesaandseries.presentation.episode

import com.example.moviesaandseries.data.remote.dto.episode.EpisodeDto

data class EpisodeDetailState (
    val isLoading: Boolean = false,
    val episode: EpisodeDto? = null,
    val error: String = ""
)