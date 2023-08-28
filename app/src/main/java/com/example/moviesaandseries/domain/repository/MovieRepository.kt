package com.example.moviesaandseries.domain.repository

import com.example.moviesaandseries.data.remote.dto.MovieDetailDto
import com.example.moviesaandseries.data.remote.dto.MovieDto

interface MovieRepository {

    suspend fun getMovies(): List<MovieDto>

    suspend fun getMovieInfo(movieId: String): MovieDetailDto

}