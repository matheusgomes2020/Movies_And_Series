package com.example.moviesaandseries.data.remote.dto

import com.example.moviesaandseries.domain.model.Movie
import com.example.moviesaandseries.domain.model.Series

data class SeriesDto (
    val poster_path: String,
    val id: Int,
    val name: String,
    /*
    val adult: Boolean,
    val aggregate_credits: Credits,

    val created_by: List<CreatedBy>,
    val episode_run_time: List<Int>,
    val first_air_date: String,
    val genres: List<Genre>,
    val homepage: String,

    val images: Images,
    val in_production: Boolean,
    val languages: List<String>,
    val last_air_date: String,
    val last_episode_to_air: LastEpisodeToAir,

    val networks: List<Network>,
    val next_episode_to_air: Any,
    val number_of_episodes: Int,
    val number_of_seasons: Int,
    val origin_country: List<String>,
    val original_language: String,
    val original_name: String,
    val overview: String,
    val popularity: Double,
    val backdrop_path: String,
    val production_companies: List<ProductionCompany>,
    val production_countries: List<ProductionCountry>,
    val reviews: Reviews,
    val seasons: List<Season>,
    val similar: SimilarSeries,
    val spoken_languages: List<SpokenLanguage>,
    val status: String,
    val tagline: String,
    val type: String,
    val videos: Videos,
    val vote_average: Double,
    val vote_count: Int

     */
)

fun SeriesDto.toSeries(): Series {
    return Series(
        id = id,
        poster_path = poster_path,
        name = name,
    )
}