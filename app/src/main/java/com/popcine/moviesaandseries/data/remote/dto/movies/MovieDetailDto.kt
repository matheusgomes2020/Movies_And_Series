package com.popcine.moviesaandseries.data.remote.dto.movies

import com.popcine.moviesaandseries.data.remote.dto.BelongsToCollection
import com.popcine.moviesaandseries.data.remote.dto.Genre
import com.popcine.moviesaandseries.data.remote.dto.Images
import com.popcine.moviesaandseries.data.remote.dto.ProductionCompany
import com.popcine.moviesaandseries.data.remote.dto.ProductionCountry
import com.popcine.moviesaandseries.data.remote.dto.Reviews
import com.popcine.moviesaandseries.data.remote.dto.SpokenLanguage
import com.popcine.moviesaandseries.data.remote.dto.Videos
import com.popcine.moviesaandseries.domain.model.MovieDetail

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
    //val revenue: Int,
    val reviews: Reviews,
    val runtime: Int,
    val similar: Similar,
    val recommendations: Recommendations,
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
        backdrop_path = backdrop_path,
        release_date = release_date,
        reviews = reviews,
        runtime = runtime,
        similar = similar,
        production_companies = production_companies,
        recommendations = recommendations,
        videos = videos,
        vote_average = vote_average
    )
}
