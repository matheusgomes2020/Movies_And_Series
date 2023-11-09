package com.popcine.moviesaandseries.domain.use_case.movies_firestore

import com.popcine.moviesaandseries.domain.repository.MoviesFirebaseRepository

class DeleteMovie(
    private val repo: MoviesFirebaseRepository
) {
    suspend operator fun invoke(idFirebase: String) = repo.deleteMovieToFirestore( idFirebase )
}