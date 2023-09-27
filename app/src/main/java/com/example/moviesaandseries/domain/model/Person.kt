package com.example.moviesaandseries.domain.model

import com.example.moviesaandseries.data.remote.dto.Images
import com.example.moviesaandseries.data.remote.dto.movies.MovieCredits
import com.example.moviesaandseries.data.remote.dto.series.TvCredits

data class Person(
    val id: Int,
    val biography: String?,
    val birthday: String?,
    val images: Images?,
    val movie_credits: MovieCredits?,
    val name: String?,
    val place_of_birth: String?,
    val profile_path: String?,
    val tv_credits: TvCredits?
)
