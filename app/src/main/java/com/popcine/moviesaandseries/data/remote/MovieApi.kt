package com.popcine.moviesaandseries.data.remote

import com.popcine.moviesaandseries.common.Constants.API_KEY
import com.popcine.moviesaandseries.common.Constants.LANGUAGE
import com.popcine.moviesaandseries.data.remote.dto.movies.MovieDetailDto
import com.popcine.moviesaandseries.data.remote.dto.movies.Movies
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET("search/movie?language=pt-BR&api_key=$API_KEY")
    suspend fun searchMovies(

        @Query("query") query: String

    ): Movies

    @GET("trending/movie/day?language=pt-BR&api_key=$API_KEY")
    suspend fun getTrendingToday() : Movies
    @GET("movie/upcoming?language=pt-BR&api_key=$API_KEY")
    suspend fun getUpcoming() : Movies

    @GET("movie/popular?language=pt-BR&api_key=$API_KEY")
    suspend fun getPopularMovies() : Movies

    @GET("movie/now_playing?language=pt-BR&api_key=$API_KEY")
    suspend fun getNowPlaying() : Movies

    @GET("movie/top_rated?language=pt-BR&api_key=$API_KEY")
    suspend fun getRatedMovies(): Movies

    @GET("discover/movie")
    suspend fun getMoviesGenres(
        @Query("language") language: String = LANGUAGE,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("sort_by") sortBy: String = "popularity.desc",
        @Query("page") pageNumber: String,
        @Query("with_genres") genreID: String) : Movies

    @GET("movie/{movieID}?language=pt-BR&api_key=$API_KEY&append_to_response=videos,images,reviews,similar,credits,recommendations")
    suspend fun  getMovieInfo(@Path("movieID") movieId: String) : MovieDetailDto



}