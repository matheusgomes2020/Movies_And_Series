package com.example.moviesaandseries.data.repository

import com.example.moviesaandseries.data.remote.MovieApi
import com.example.moviesaandseries.data.remote.dto.MovieDetailDto
import com.example.moviesaandseries.data.remote.dto.MovieDto
import com.example.moviesaandseries.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val api: MovieApi
): MovieRepository {
    override suspend fun getMovies(): List<MovieDto> {
        return api.getPopularMovies()
    }

    override suspend fun getMovieInfo(movieId: String): MovieDetailDto {
        return api.getMovieInfo( movieId )
    }
}