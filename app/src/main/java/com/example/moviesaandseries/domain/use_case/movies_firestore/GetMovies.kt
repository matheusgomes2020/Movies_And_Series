package com.example.moviesaandseries.domain.use_case.movies_firestore

import com.example.moviesaandseries.domain.repository.MoviesFirebaseRepository

class GetMovies (
    private val repo: MoviesFirebaseRepository
) {
    operator fun invoke() = repo.getMoviesFromFirestore() }