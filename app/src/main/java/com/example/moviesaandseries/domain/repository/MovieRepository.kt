package com.example.moviesaandseries.domain.repository

import com.example.moviesaandseries.data.remote.dto.MovieDetailDto
import com.example.moviesaandseries.data.remote.dto.MovieDto

interface MovieRepository {

    suspend fun getPopularMovies(): List<MovieDto>
    suspend fun getUpcomingMovies(): List<MovieDto>
    suspend fun getNowPlayingMovies(): List<MovieDto>
    suspend fun getRatedMovies(): List<MovieDto>

    suspend fun getMovieInfo(movieId: String): MovieDetailDto

}