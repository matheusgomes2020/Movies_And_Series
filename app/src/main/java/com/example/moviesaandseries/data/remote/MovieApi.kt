package com.example.moviesaandseries.data.remote

import com.example.moviesaandseries.common.Constants
import com.example.moviesaandseries.common.Constants.API_KEY
import com.example.moviesaandseries.common.Constants.LANGUAGE
import com.example.moviesaandseries.data.remote.dto.movies.MovieDetailDto
import com.example.moviesaandseries.data.remote.dto.movies.Movies
import org.intellij.lang.annotations.Language
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET("search/movie?language=pt-BR&api_key=$API_KEY")
    suspend fun searchMovies(

        @Query("query") query: String

    ): Movies

    //https://api.themoviedb.org/3/trending/movie/day?language=pt-BR&api_key=0f5183b12ca04341d5f0f71d8bc698b5

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

    //https://api.themoviedb.org/3/discover/movie?&language=?pt-BR&api_key=0f5183b12ca04341d5f0f71d8bc698b5&page=1&sort_by=popularity.desc&with_genres=10749

    @GET("movie/{movieID}?language=pt-BR&api_key=$API_KEY&append_to_response=videos,images,reviews,similar,credits,recommendations")
    suspend fun  getMovieInfo(@Path("movieID") movieId: String) : MovieDetailDto



}