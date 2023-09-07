package com.example.moviesaandseries.data.repository

import com.example.moviesaandseries.data.remote.MovieApi
import com.example.moviesaandseries.data.remote.dto.movies.MovieDetailDto
import com.example.moviesaandseries.data.remote.dto.movies.MovieDto
import com.example.moviesaandseries.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val api: MovieApi
): MovieRepository {
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

    override suspend fun getMovieInfo(movieId: String): MovieDetailDto {
        return api.getMovieInfo( movieId )
    }
}