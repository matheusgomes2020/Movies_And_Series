package com.example.moviesaandseries.data.repository

import com.example.moviesaandseries.data.remote.SeriesApi
import com.example.moviesaandseries.data.remote.dto.SeriesDetailDto
import com.example.moviesaandseries.data.remote.dto.SeriesDto
import com.example.moviesaandseries.domain.repository.SeriesRepository
import javax.inject.Inject

class SeriesRepositoryImpl @Inject constructor(
    private val api: SeriesApi
): SeriesRepository {
    override suspend fun getPopularSeries(): List<SeriesDto> {
        return api.getPopularSeries().results
    }

    override suspend fun getAiryingtodaySeries(): List<SeriesDto> {
        return api.getAiringTodaySeries().results
    }

    override suspend fun getAiryingSeries(): List<SeriesDto> {
        return api.getOnTheAirSeries().results
    }

    override suspend fun getRatedSeries(): List<SeriesDto> {
        return api.getPopularTopRated().results
    }

    override suspend fun getMovieInfo(seriesId: String): SeriesDetailDto {
        return api.getSeriesInfo( seriesId )
    }



}