package com.popcine.moviesaandseries.domain.model

import com.popcine.moviesaandseries.data.remote.dto.movies.Credits
import com.popcine.moviesaandseries.data.remote.dto.Genre
import com.popcine.moviesaandseries.data.remote.dto.Images
import com.popcine.moviesaandseries.data.remote.dto.ProductionCompany
import com.popcine.moviesaandseries.data.remote.dto.Reviews
import com.popcine.moviesaandseries.data.remote.dto.movies.Similar
import com.popcine.moviesaandseries.data.remote.dto.Videos
import com.popcine.moviesaandseries.data.remote.dto.movies.Recommendations


data class MovieDetail(
    val credits: Credits,
    val genres: List<Genre>,
    val id: Int,
    val images: Images,
    val production_companies: List<ProductionCompany>,
    val overview: String,
    val poster_path: String?,
    val backdrop_path: String?,
    val release_date: String,
    val reviews: Reviews,
    val runtime: Int,
    val similar: Similar,
    val recommendations: Recommendations,
    val title: String,
    val videos: Videos,
    val vote_average: Double
)