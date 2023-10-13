package com.example.moviesaandseries.presentation.person_list

import com.example.moviesaandseries.data.remote.dto.person.MovieWork

data class MoviesCastListState (
    val isLoading: Boolean = false,
    val movies: List<MovieWork> = emptyList(),
    val error: String = ""
)