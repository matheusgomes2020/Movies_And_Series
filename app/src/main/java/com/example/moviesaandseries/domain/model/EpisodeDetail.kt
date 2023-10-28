package com.example.moviesaandseries.domain.model

import com.example.moviesaandseries.data.remote.dto.Images
import com.example.moviesaandseries.data.remote.dto.movies.Cast
import com.example.moviesaandseries.data.remote.dto.movies.Crew

data class EpisodeDetail(
    val air_date: String,
    val crew: List<Crew>,
    val guest_stars: List<Cast>,
    val id: Int,
    val images: Images,
    val name: String,
    val overview: String,
    val runtime: Int,
    val still_path: String?,
    val vote_average: Double,
)
