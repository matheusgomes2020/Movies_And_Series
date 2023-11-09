package com.popcine.moviesaandseries.domain.repository

import com.popcine.moviesaandseries.data.remote.dto.episode.EpisodeDetailDto
import com.popcine.moviesaandseries.data.remote.dto.season.SeasonDetailDto
import com.popcine.moviesaandseries.data.remote.dto.series.SeriesDetailDto
import com.popcine.moviesaandseries.data.remote.dto.series.SeriesDto

interface SeriesRepository {

    suspend fun searchSeries( searchQuery: String ): List<SeriesDto>

    suspend fun getTrendingTodaySeries(): List<SeriesDto>


    suspend fun getPopularSeries(): List<SeriesDto>

    suspend fun getAiryingtodaySeries(): List<SeriesDto>

    suspend fun getAiryingSeries(): List<SeriesDto>

    suspend fun getRatedSeries(): List<SeriesDto>

    suspend fun getSeriesInfo(seriesId: String): SeriesDetailDto

    suspend fun getSeasonInfo(seriesId: String, seasonNumber: String): SeasonDetailDto

    suspend fun getSeriesGenre( pageNumber: String, genreId: String ): List<SeriesDto>


    suspend fun getEpisodeInfo(seriesId: String, seasonNumber: String, episodeNumber: String): EpisodeDetailDto
}