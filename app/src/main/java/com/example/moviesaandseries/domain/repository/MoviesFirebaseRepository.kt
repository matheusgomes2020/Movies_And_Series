package com.example.moviesaandseries.domain.repository

import com.example.moviesaandseries.domain.model.MovieOrSeriesFirebase
import com.example.moviesaandseries.domain.model.Response
import kotlinx.coroutines.flow.Flow

typealias Movies = List<MovieOrSeriesFirebase>
typealias MoviesResponse = Response<Movies>
typealias AddMovieResponse = Response<Boolean>
typealias DeleteMovieResponse = Response<Boolean>



interface MoviesFirebaseRepository {

    fun getMoviesFromFirestore(): Flow<MoviesResponse>

    suspend fun addMovieToFirestore(id: Int, title: String, posterPath: String, tipo: String, userId: String  ): AddMovieResponse

    suspend fun deleteMovieToFirestore(idFirebase: String): DeleteMovieResponse

}

