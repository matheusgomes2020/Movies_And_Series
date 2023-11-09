package com.popcine.moviesaandseries.presentation.episode

import com.popcine.moviesaandseries.domain.model.EpisodeDetail

data class EpisodeDetailState (
    val isLoading: Boolean = false,
    val episode: EpisodeDetail? = null,
    val error: String = ""
)