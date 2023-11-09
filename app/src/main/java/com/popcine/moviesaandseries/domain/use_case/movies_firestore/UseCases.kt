package com.popcine.moviesaandseries.domain.use_case.movies_firestore

data class UseCases (
    val getMovies: GetMovies,
    val addMovie: AddMovie,
    val deleteMovie: DeleteMovie
)