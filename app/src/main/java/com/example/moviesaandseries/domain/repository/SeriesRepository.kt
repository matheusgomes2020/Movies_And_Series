package com.example.moviesaandseries.domain.repository

import com.example.moviesaandseries.data.remote.dto.episode.EpisodeDetailDto
import com.example.moviesaandseries.data.remote.dto.season.SeasonDetailDto
import com.example.moviesaandseries.data.remote.dto.series.SeriesDetailDto
import com.example.moviesaandseries.data.remote.dto.series.SeriesDto

interface SeriesRepository {

    suspend fun searchSeries( searchQuery: String ): List<SeriesDto>

    suspend fun getPopularSeries(): List<SeriesDto>

    suspend fun getAiryingtodaySeries(): List<SeriesDto>

    suspend fun getAiryingSeries(): List<SeriesDto>

    suspend fun getRatedSeries(): List<SeriesDto>

    suspend fun getSeriesInfo(seriesId: String): SeriesDetailDto

    suspend fun getSeasonInfo(seriesId: String, seasonNumber: String): SeasonDetailDto

    suspend fun getEpisodeInfo(seriesId: String, seasonNumber: String, episodeNumber: String): EpisodeDetailDto
}