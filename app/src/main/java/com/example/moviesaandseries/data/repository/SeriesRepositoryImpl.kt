package com.example.moviesaandseries.data.repository

import android.util.Log
import com.example.moviesaandseries.data.remote.SeriesApi
import com.example.moviesaandseries.data.remote.dto.episode.EpisodeDetailDto
import com.example.moviesaandseries.data.remote.dto.season.SeasonDetailDto
import com.example.moviesaandseries.data.remote.dto.series.SeriesDetailDto
import com.example.moviesaandseries.data.remote.dto.series.SeriesDto
import com.example.moviesaandseries.domain.repository.SeriesRepository
import javax.inject.Inject

class SeriesRepositoryImpl @Inject constructor(
    private val api: SeriesApi
): SeriesRepository {
    override suspend fun searchSeries(searchQuery: String): List<SeriesDto> {
        return api.searchSeries( searchQuery ).results
    }

    override suspend fun getTrendingTodaySeries(): List<SeriesDto> {
        return api.getTrendingToday().results
    }

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

    override suspend fun getSeriesInfo(seriesId: String): SeriesDetailDto {
        return api.getSeriesInfo( seriesId )
    }

    override suspend fun getSeasonInfo(seriesId: String, seasonNumber: String): SeasonDetailDto {
        return api.getSeasonInfo(seriesId, seasonNumber)
    }

    override suspend fun getSeriesGenre(pageNumber: String, genreId: String): List<SeriesDto> {
        return api.getSeriesGenres(pageNumber = pageNumber, genreID = genreId).results
    }

    override suspend fun getEpisodeInfo( seriesId: String, seasonNumber: String, episodeNumber: String): EpisodeDetailDto {
        return api.getEpisodeInfo(seriesId, seasonNumber, episodeNumber)
    }


}