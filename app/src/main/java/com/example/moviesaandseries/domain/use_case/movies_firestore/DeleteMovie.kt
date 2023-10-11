package com.example.moviesaandseries.domain.use_case.movies_firestore

import com.example.moviesaandseries.domain.repository.MoviesFirebaseRepository

class DeleteMovie(
    private val repo: MoviesFirebaseRepository
) {
    suspend operator fun invoke(idFirebase: String) = repo.deleteMovieToFirestore( idFirebase )
}