package com.example.moviesaandseries.domain.repository

import com.example.moviesaandseries.data.remote.dto.SeriesDetailDto
import com.example.moviesaandseries.data.remote.dto.SeriesDto

interface SeriesRepository {

    suspend fun getPopularSeries(): List<SeriesDto>
    suspend fun getAiryingtodaySeries(): List<SeriesDto>
    suspend fun getAiryingSeries(): List<SeriesDto>
    suspend fun getRatedSeries(): List<SeriesDto>

    suspend fun getSeriesInfo(seriesId: String): SeriesDetailDto

}