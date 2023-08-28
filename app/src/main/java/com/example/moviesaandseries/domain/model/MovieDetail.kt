package com.example.moviesaandseries.domain.model

import com.example.moviesaandseries.data.remote.dto.BelongsToCollection
import com.example.moviesaandseries.data.remote.dto.Credits
import com.example.moviesaandseries.data.remote.dto.Genre
import com.example.moviesaandseries.data.remote.dto.Images
import com.example.moviesaandseries.data.remote.dto.Reviews
import com.example.moviesaandseries.data.remote.dto.Similar
import com.example.moviesaandseries.data.remote.dto.Videos


data class MovieDetail(
    val credits: Credits,
    val genres: List<Genre>,
    val id: Int,
    val images: Images,
    val overview: String,
    val poster_path: String,
    val release_date: String,
    val reviews: Reviews,
    val runtime: Int,
    val similar: Similar,
    val title: String,
    val videos: Videos,
    val vote_average: Double
)
