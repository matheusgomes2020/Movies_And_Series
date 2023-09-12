package com.example.moviesaandseries.data.remote

import com.example.moviesaandseries.common.Constants
import com.example.moviesaandseries.data.remote.dto.season.Episode
import com.example.moviesaandseries.data.remote.dto.season.SeasonDetailDto
import com.example.moviesaandseries.data.remote.dto.season.SeasonDto
import com.example.moviesaandseries.data.remote.dto.series.SeriesDetailDto
import com.example.moviesaandseries.data.remote.dto.series.SeriesList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SeriesApi {

    @GET("search/tv?language=pt-BR&api_key=" + Constants.API_KEY)
    suspend fun searchSeries(

        @Query("query") query: String

    ): SeriesList

    @GET("tv/airing_today?language=pt-BR&api_key=" + Constants.API_KEY)
    suspend fun getAiringTodaySeries(): SeriesList

    @GET("tv/popular?language=pt-BR&api_key=" + Constants.API_KEY)
    suspend fun getPopularSeries(): SeriesList

    @GET("tv/top_rated?language=pt-BR&api_key=" + Constants.API_KEY)
    suspend fun getPopularTopRated(): SeriesList

    @GET("tv/on_the_air?language=pt-BR&api_key=" + Constants.API_KEY)
    suspend fun getOnTheAirSeries(): SeriesList

    @GET("tv/{serieID}?language=pt-BR&api_key=" + Constants.API_KEY + "&append_to_response=videos,images,reviews,similar,aggregate_credits,episodes" )
    suspend fun  getSeriesInfo(@Path("serieID") serieId: String) : SeriesDetailDto

    @GET("tv/{series_id}/season/{season_number}?language=pt-BR&api_key=" + Constants.API_KEY)
    suspend fun getSeasonInfo(@Path("series_id") series_id: String, @Path("season_number") season_number: String ) : SeasonDetailDto

    @GET("tv/{series_id}/season/{season_number}/episode/{episode_number}?language=pt-BR&api_key=" + Constants.API_KEY + "&append_to_response=images")
    suspend fun getEpisodeInfo(@Path("series_id") series_id: String, @Path("season_number") season_number: Int, @Path("episode_number") episode_number: Int ) : Episode


}