package com.popcine.moviesaandseries.data.repository

import android.util.Log
import com.popcine.moviesaandseries.data.remote.MovieApi
import com.popcine.moviesaandseries.data.remote.dto.movies.MovieDetailDto
import com.popcine.moviesaandseries.data.remote.dto.movies.MovieDto
import com.popcine.moviesaandseries.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val api: MovieApi
): MovieRepository {

    override suspend fun searchMovies( searchQuery: String ): List<MovieDto> {
        return api.searchMovies( searchQuery ).results
    }

    override suspend fun getTrendingTodayMovies(): List<MovieDto> {
        return api.getTrendingToday().results
    }

    override suspend fun getPopularMovies(): List<MovieDto> {
        return api.getPopularMovies().results
    }

    override suspend fun getUpcomingMovies(): List<MovieDto> {
        return api.getUpcoming().results
    }

    override suspend fun getNowPlayingMovies(): List<MovieDto> {
        return api.getNowPlaying().results
    }

    override suspend fun getRatedMovies(): List<MovieDto> {
        return api.getRatedMovies().results
    }

    override suspend fun getMoviesGenre( pageNumber: String, genreId: String ): List<MovieDto> {
        Log.d("BORA", "IMPL: $pageNumber | $genreId")
        return api.getMoviesGenres( pageNumber = pageNumber, genreID = genreId ).results
    }

    override suspend fun getMovieInfo(movieId: String): MovieDetailDto {
        return api.getMovieInfo( movieId )
    }
}