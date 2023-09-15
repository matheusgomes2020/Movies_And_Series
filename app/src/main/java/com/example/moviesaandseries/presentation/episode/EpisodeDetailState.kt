package com.example.moviesaandseries.presentation.episode

import com.example.moviesaandseries.domain.model.EpisodeDetail

data class EpisodeDetailState (
    val isLoading: Boolean = false,
    val episode: EpisodeDetail? = null,
    val error: String = ""
)