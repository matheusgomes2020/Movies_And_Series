package com.example.moviesaandseries.data.remote.dto.person

import com.example.moviesaandseries.data.remote.dto.Images
import com.example.moviesaandseries.data.remote.dto.movies.MovieCredits
import com.example.moviesaandseries.data.remote.dto.series.TvCredits
import com.example.moviesaandseries.domain.model.Person

data class PersonDetailDto(
    val adult: Boolean?,
    val also_known_as: List<String>?,
    val biography: String?,
    val birthday: String?,
    val deathday: Any?,
    val gender: Int?,
    val homepage: Any?,
    val id: Int,
    val images: Images?,
    val imdb_id: String?,
    val known_for_department: String?,
    val movie_credits: MovieCredits?,
    val name: String?,
    val place_of_birth: String?,
    val popularity: Double?,
    val profile_path: String?,
    val tv_credits: TvCredits?
)

fun PersonDetailDto.toPerson(): Person {
    return Person(
        name = name,
        biography = biography,
        birthday = birthday,
        id = id,
        images = images,
        movie_credits = movie_credits,
        place_of_birth = place_of_birth,
        profile_path = profile_path,
        tv_credits = tv_credits
    )
}
