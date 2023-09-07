package com.example.moviesaandseries.data.remote.dto.season

import com.example.moviesaandseries.data.remote.dto.Images
import com.example.moviesaandseries.data.remote.dto.movies.Cast
import com.example.moviesaandseries.data.remote.dto.movies.Crew

data class Episode(
    val air_date: String,
    val crew: List<Crew>,
    val episode_number: Int,
    val guest_stars: List<Cast>,
    val id: Int,
    val images: Images,
    val name: String,
    val overview: String,
    val production_code: String,
    val runtime: Int,
    val season_number: Int,
    val still_path: String,
    val vote_average: Double,
    val vote_count: Int
)



