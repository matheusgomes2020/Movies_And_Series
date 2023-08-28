package com.example.moviesaandseries.data.remote.dto

import com.example.filmes.model.general.SpokenLanguage
import com.example.moviesaandseries.domain.model.Movie

data class MovieDto (
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
    val poster_path: String,
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

fun MovieDto.toMovie(): Movie {
    return Movie(
        id = id,
        poster_path = poster_path,
        title = title,
    )
}