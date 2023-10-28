package com.example.moviesaandseries.data.remote.dto.episode

import com.example.moviesaandseries.data.remote.dto.Images
import com.example.moviesaandseries.data.remote.dto.movies.Cast
import com.example.moviesaandseries.data.remote.dto.movies.Crew
import com.example.moviesaandseries.domain.model.EpisodeDetail

data class EpisodeDetailDto(
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
    val still_path: String?,
    val vote_average: Double,
    val vote_count: Int
)

fun EpisodeDetailDto.toEpisodeDetail(): EpisodeDetail {
    return EpisodeDetail(
        images = images,
        overview = overview,
        name = name,
        crew = crew,
        vote_average = vote_average,
        id = id,
        air_date = air_date,
        guest_stars = guest_stars,
        runtime = runtime,
        still_path = still_path
    )
}


