package com.example.moviesaandseries.data.remote.dto.movies

import com.example.moviesaandseries.data.remote.dto.BelongsToCollection
import com.example.moviesaandseries.data.remote.dto.Genre
import com.example.moviesaandseries.data.remote.dto.Images
import com.example.moviesaandseries.data.remote.dto.ProductionCompany
import com.example.moviesaandseries.data.remote.dto.ProductionCountry
import com.example.moviesaandseries.data.remote.dto.Reviews
import com.example.moviesaandseries.data.remote.dto.SpokenLanguage
import com.example.moviesaandseries.data.remote.dto.Videos
import com.example.moviesaandseries.domain.model.MovieDetail

data class MovieDetailDto(
    val adult: Boolean,
    val backdrop_path: String,
    val belongs_to_collection: BelongsToCollection,
    val budget: Int,
    val credits: Credits,
    val genres: List<Genre>,
    val homepage: String,
    val id: Int,
    val images: Images,
    val imdb_id: String,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String?,
    val production_companies: List<ProductionCompany>,
    val production_countries: List<ProductionCountry>,
    val release_date: String,
    val revenue: Int,
    val reviews: Reviews,
    val runtime: Int,
    val similar: Similar,
    val spoken_languages: List<SpokenLanguage>,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    val videos: Videos,
    val vote_average: Double,
    val vote_count: Int
)

fun MovieDetailDto.toMovieDetail(): MovieDetail {
    return MovieDetail(
        id = id,
        title = title,
        credits = credits,
        genres = genres,
        images = images,
        overview = overview,
        poster_path = poster_path,
        release_date = release_date,
        reviews = reviews,
        runtime = runtime,
        similar = similar,
        videos = videos,
        vote_average = vote_average
    )
}
