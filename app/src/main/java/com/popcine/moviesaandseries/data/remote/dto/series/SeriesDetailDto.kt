package com.popcine.moviesaandseries.data.remote.dto.series

import com.popcine.moviesaandseries.data.remote.dto.CreatedBy
import com.popcine.moviesaandseries.data.remote.dto.movies.Credits
import com.popcine.moviesaandseries.data.remote.dto.Genre
import com.popcine.moviesaandseries.data.remote.dto.Images
import com.popcine.moviesaandseries.data.remote.dto.ProductionCompany
import com.popcine.moviesaandseries.data.remote.dto.ProductionCountry
import com.popcine.moviesaandseries.data.remote.dto.Reviews
import com.popcine.moviesaandseries.data.remote.dto.SpokenLanguage
import com.popcine.moviesaandseries.data.remote.dto.Videos
import com.popcine.moviesaandseries.data.remote.dto.season.SeasonDto
import com.popcine.moviesaandseries.domain.model.SeriesDetail

data class SeriesDetailDto(
    val adult: Boolean,
    val aggregate_credits: Credits,
    val backdrop_path: String?,
    val created_by: List<CreatedBy>,
    val episode_run_time: List<Int>,
    val first_air_date: String,
    val genres: List<Genre>,
    val homepage: String,
    val id: Int,
    val images: Images,
    val in_production: Boolean,
    val languages: List<String>,
    val last_air_date: String,
    val last_episode_to_air: LastEpisodeToAir,
    val name: String,
    val networks: List<Network>,
    val next_episode_to_air: Any,
    val number_of_episodes: Int,
    val number_of_seasons: Int,
    val origin_country: List<String>,
    val original_language: String,
    val original_name: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String?,
    val production_companies: List<ProductionCompany>,
    val production_countries: List<ProductionCountry>,
    val reviews: Reviews,
    val seasons: List<SeasonDto>,
    val similar: SimilarSeries,
    val recommendations: RecommendationSeries,
    val spoken_languages: List<SpokenLanguage>,
    val status: String,
    val tagline: String,
    val type: String,
    val videos: Videos,
    val vote_average: Double,
    val vote_count: Int
)

fun SeriesDetailDto.toSeriesDetail(): SeriesDetail {
    return SeriesDetail(
        id = id,
        name = name,
        aggregate_credits = aggregate_credits,
        genres = genres,
        images = images,
        overview = overview,
        reviews = reviews,
        similar = similar,
        recommendations = recommendations,
        videos = videos,
        poster_path = poster_path,
        backdrop_path = backdrop_path,
        created_by = created_by,
        episode_run_time = episode_run_time,
        first_air_date = first_air_date,
        seasons = seasons,
        vote_average = vote_average,
        production_companies = production_companies,
        number_of_seasons = number_of_seasons

    )
}
