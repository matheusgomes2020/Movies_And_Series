package com.popcine.moviesaandseries.domain.use_case.movies_firestore

import com.popcine.moviesaandseries.domain.repository.MoviesFirebaseRepository

class AddMovie(
    private val repo: MoviesFirebaseRepository
) {
    suspend operator fun invoke(

        id: Int,
        title: String,
        posterPath: String,
        tipo: String,
        userId: String
    ) = repo.addMovieToFirestore(id, title, posterPath, tipo, userId)
}