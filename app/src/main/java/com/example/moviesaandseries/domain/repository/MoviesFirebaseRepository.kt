package com.example.moviesaandseries.domain.repository

import com.example.moviesaandseries.domain.model.Movie
import com.example.moviesaandseries.domain.model.MovieFirebase
import com.example.moviesaandseries.domain.model.Response
import com.google.firebase.firestore.CollectionReference
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

typealias Movies = List<MovieFirebase>
typealias MoviesResponse = Response<Movies>
typealias AddMovieResponse = Response<Boolean>
typealias DeleteMovieResponse = Response<Boolean>



interface MoviesFirebaseRepository {

    fun getMoviesFromFirestore(): Flow<MoviesResponse>

    suspend fun addMovieToFirestore(id: Int, title: String, posterPath: String, tipo: String, userId: String  ): AddMovieResponse

    suspend fun deleteMovieToFirestore(idFirebase: String): DeleteMovieResponse

}

