package com.example.moviesaandseries.domain.model

import com.example.moviesaandseries.data.remote.dto.CreatedBy
import com.example.moviesaandseries.data.remote.dto.movies.Credits
import com.example.moviesaandseries.data.remote.dto.Genre
import com.example.moviesaandseries.data.remote.dto.Images
import com.example.moviesaandseries.data.remote.dto.ProductionCompany
import com.example.moviesaandseries.data.remote.dto.Reviews
import com.example.moviesaandseries.data.remote.dto.season.SeasonDto
import com.example.moviesaandseries.data.remote.dto.series.SimilarSeries
import com.example.moviesaandseries.data.remote.dto.Videos
import com.example.moviesaandseries.data.remote.dto.series.RecommendationSeries

data class SeriesDetail(
    val aggregate_credits: Credits,
    val genres: List<Genre>,
    val id: Int,
    val images: Images,
    val overview: String,
    val poster_path: String?,
    val backdrop_path: String?,
    val created_by: List<CreatedBy>,
    val episode_run_time: List<Int>,
    val first_air_date: String,
    val number_of_seasons: Int,
    val reviews: Reviews,
    val production_companies: List<ProductionCompany>,
    val similar: SimilarSeries,
    val recommendations: RecommendationSeries,
    val name: String,
    val videos: Videos,
    val vote_average: Double,
    val seasons: List<SeasonDto>
)
