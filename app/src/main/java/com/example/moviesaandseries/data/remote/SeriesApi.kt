package com.example.moviesaandseries.data.remote

import com.example.moviesaandseries.common.Constants
import com.example.moviesaandseries.common.Constants.API_KEY
import com.example.moviesaandseries.common.Constants.LANGUAGE
import com.example.moviesaandseries.data.remote.dto.episode.EpisodeDetailDto
import com.example.moviesaandseries.data.remote.dto.episode.EpisodeDto
import com.example.moviesaandseries.data.remote.dto.season.SeasonDetailDto
import com.example.moviesaandseries.data.remote.dto.series.SeriesDetailDto
import com.example.moviesaandseries.data.remote.dto.series.SeriesList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SeriesApi {

    @GET("search/tv?language=pt-BR&api_key=$API_KEY")
    suspend fun searchSeries(

        @Query("query") query: String

    ): SeriesList

    @GET("trending/tv/day?language=$LANGUAGE&api_key=$API_KEY")
    suspend fun getTrendingToday() : SeriesList

    @GET("tv/airing_today")
    suspend fun getAiringTodaySeries(
        @Query("language") language: String = LANGUAGE,
        @Query("api_key") apiKey: String = API_KEY
    ): SeriesList

    @GET("tv/popular?language=$LANGUAGE&api_key=$API_KEY")
    suspend fun getPopularSeries(): SeriesList

    @GET("tv/top_rated?language=$LANGUAGE&api_key=$API_KEY")
    suspend fun getPopularTopRated(): SeriesList

    @GET("tv/on_the_air?language=$LANGUAGE&api_key=$API_KEY")
    suspend fun getOnTheAirSeries(): SeriesList

    @GET("tv/{serieID}?language=$LANGUAGE&api_key=$API_KEY&append_to_response=videos,images,reviews,similar,aggregate_credits,episodes,recommendations")
    suspend fun  getSeriesInfo(@Path("serieID") serieId: String) : SeriesDetailDto

    @GET("tv/{series_id}/season/{season_number}?language=pt-BR&api_key=" + API_KEY)
    suspend fun getSeasonInfo(@Path("series_id") series_id: String, @Path("season_number") season_number: String ) : SeasonDetailDto

    @GET("discover/tv")
    suspend fun getSeriesGenres(
        @Query("language") language: String = LANGUAGE,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("sort_by") sortBy: String = "popularity.desc",
        @Query("page") pageNumber: String,
        @Query("with_genres") genreID: String) : SeriesList

    @GET("tv/{series_id}/season/{season_number}/episode/{episode_number}?language=pt-BR&api_key=$API_KEY&append_to_response=images")
    suspend fun getEpisodeInfo(@Path("series_id") series_id: String, @Path("season_number") season_number: String, @Path("episode_number") episode_number: String ) : EpisodeDetailDto


}
