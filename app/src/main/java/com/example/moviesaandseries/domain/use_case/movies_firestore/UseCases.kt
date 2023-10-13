package com.example.moviesaandseries.domain.use_case.movies_firestore

data class UseCases (
    val getMovies: GetMovies,
    val getMovies2: GetMovies2,
    val addMovie: AddMovie,
    val deleteMovie: DeleteMovie
)