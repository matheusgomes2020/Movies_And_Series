package com.example.moviesaandseries.domain.repository

import com.example.moviesaandseries.data.remote.dto.season.SeasonDetailDto
import com.example.moviesaandseries.data.remote.dto.series.SeriesDetailDto
import com.example.moviesaandseries.data.remote.dto.series.SeriesDto

interface SeriesRepository {

    suspend fun getPopularSeries(): List<SeriesDto>
    suspend fun getAiryingtodaySeries(): List<SeriesDto>
    suspend fun getAiryingSeries(): List<SeriesDto>
    suspend fun getRatedSeries(): List<SeriesDto>

    suspend fun getSeriesInfo(seriesId: String): SeriesDetailDto

    suspend fun getSeasonInfo(seriesId: String, seasonNumber: Int): SeasonDetailDto
}