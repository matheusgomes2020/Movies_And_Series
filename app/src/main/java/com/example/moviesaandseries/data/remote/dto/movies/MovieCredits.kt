package com.example.moviesaandseries.data.remote.dto.movies

import com.example.moviesaandseries.data.remote.dto.person.MovieWork
import com.example.moviesaandseries.domain.model.Movie

data class MovieCredits(
    val cast: List<Movie>,
    val crew: List<Crew>
)