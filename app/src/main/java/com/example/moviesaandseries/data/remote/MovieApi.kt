package com.example.moviesaandseries.data.remote

import com.example.moviesaandseries.common.Constants
import com.example.moviesaandseries.data.remote.dto.movies.MovieDetailDto
import com.example.moviesaandseries.data.remote.dto.movies.Movies
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET("search/movie?language=pt-BR&api_key=" + Constants.API_KEY)
    suspend fun searchMovies(

        @Query("query") query: String

    ): Movies

    @GET("movie/upcoming?language=pt-BR&api_key=" + Constants.API_KEY)
    suspend fun getUpcoming() : Movies

    @GET("movie/popular?language=pt-BR&api_key=" + Constants.API_KEY)
    suspend fun getPopularMovies() : Movies

    @GET("movie/now_playing?language=pt-BR&api_key=" + Constants.API_KEY)
    suspend fun getNowPlaying() : Movies

    @GET("movie/top_rated?language=pt-BR&api_key=" + Constants.API_KEY)
    suspend fun getRatedMovies(): Movies

    @GET("movie/{movieID}?language=pt-BR&api_key=" + Constants.API_KEY + "&append_to_response=videos,images,reviews,similar,credits,recommendations")
    suspend fun  getMovieInfo(@Path("movieID") movieId: String) : MovieDetailDto


}