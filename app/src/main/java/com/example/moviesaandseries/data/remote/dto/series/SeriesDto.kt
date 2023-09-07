package com.example.moviesaandseries.data.remote.dto.series

import com.example.moviesaandseries.domain.model.Series

data class SeriesDto (
    val poster_path: String,
    val id: Int,
    val name: String,
)

fun SeriesDto.toSeries(): Series {
    return Series(
        id = id,
        poster_path = poster_path,
        name = name,
    )
}