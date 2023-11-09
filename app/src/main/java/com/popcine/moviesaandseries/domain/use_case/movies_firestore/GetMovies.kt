package com.popcine.moviesaandseries.domain.use_case.movies_firestore

import com.popcine.moviesaandseries.domain.repository.MoviesFirebaseRepository

class GetMovies (
    private val repo: MoviesFirebaseRepository
) {
    operator fun invoke() = repo.getMoviesFromFirestore() }