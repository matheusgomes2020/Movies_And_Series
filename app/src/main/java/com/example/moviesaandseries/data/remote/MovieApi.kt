package com.example.moviesaandseries.data.remote

import com.example.moviesaandseries.common.Constants
import com.example.moviesaandseries.data.remote.dto.MovieDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET("search/movie?language=pt-BR&api_key=" + Constants.API_KEY)
    suspend fun searchMovies(

        @Query("query") query: String

    ): MovieDto

    @GET("movie/upcoming?language=pt-BR&api_key=" + Constants.API_KEY)
    suspend fun getUpcoming() : List<MovieDto>

    @GET("movie/popular?language=pt-BR&api_key=" + Constants.API_KEY)
    suspend fun getPopularMovies() : List<MovieDto>

    @GET("movie/now_playing?language=pt-BR&api_key=" + Constants.API_KEY)
    suspend fun getNowPlaying() : List<MovieDto>

    @GET("movie/top_rated?language=pt-BR&api_key=" + Constants.API_KEY)
    suspend fun getRatedMovies(): List<MovieDto>

    @GET("movie/{movieID}?language=pt-BR&api_key=" + Constants.API_KEY + "&append_to_response=videos,images,reviews,similar,credits")
    suspend fun  getMovieInfo(@Path("movieID") movieId: String) : MovieDto


}