package com.example.moviesaandseries.domain.use_case.movies_firestore

import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import com.example.moviesaandseries.domain.repository.MoviesFirebaseRepository

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