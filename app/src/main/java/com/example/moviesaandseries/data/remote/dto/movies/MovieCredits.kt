package com.example.moviesaandseries.data.remote.dto.movies

import com.example.moviesaandseries.data.remote.dto.person.MovieWork

data class MovieCredits(
    val cast: List<MovieWork>,
    val crew: List<Crew>
)