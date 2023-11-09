package com.popcine.moviesaandseries.data.remote.dto.movies

import com.popcine.moviesaandseries.domain.model.Movie

data class MovieCredits(
    val cast: List<Movie>,
    val crew: List<Crew>
)