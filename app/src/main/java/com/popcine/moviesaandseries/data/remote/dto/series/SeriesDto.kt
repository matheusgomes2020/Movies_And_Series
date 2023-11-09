package com.popcine.moviesaandseries.data.remote.dto.series

import com.popcine.moviesaandseries.domain.model.Series

data class SeriesDto (
    val id: Int,
    val name: String,
    val poster_path: String?,
    var overview: String?,
    val vote_average: Double?,


)

fun SeriesDto.toSeries(): Series {
    return Series(
        id = id,
        name = name,
        poster_path = poster_path,
        overview = overview,
        vote_average = vote_average

    )
}