package com.popcine.moviesaandseries.domain.repository

import com.popcine.moviesaandseries.data.remote.dto.movies.MovieDetailDto
import com.popcine.moviesaandseries.data.remote.dto.movies.MovieDto

interface MovieRepository {
    suspend fun searchMovies( searchQuery: String ): List<MovieDto>

    suspend fun getTrendingTodayMovies(): List<MovieDto>
    suspend fun getPopularMovies(): List<MovieDto>

    suspend fun getUpcomingMovies(): List<MovieDto>

    suspend fun getNowPlayingMovies(): List<MovieDto>

    suspend fun getRatedMovies(): List<MovieDto>

    suspend fun getMovieInfo(movieId: String): MovieDetailDto

    suspend fun getMoviesGenre(pageNumber: String, genreId: String): List<MovieDto>

}