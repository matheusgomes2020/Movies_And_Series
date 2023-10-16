package com.example.moviesaandseries.data.repository

import android.content.Context
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import com.example.moviesaandseries.common.Constants.MOVIES
import com.example.moviesaandseries.domain.model.MovieFirebase
import com.example.moviesaandseries.domain.model.Response
import com.example.moviesaandseries.domain.repository.MoviesFirebaseRepository
import com.google.firebase.firestore.CollectionReference
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class MoviesFirebaseRepositoryImpl @Inject constructor(
    private val moviesRef: CollectionReference
) : MoviesFirebaseRepository {
    override fun getMoviesFromFirestore() = callbackFlow {
        val snapshotListener = moviesRef.addSnapshotListener { snapshot, e ->
            val moviesResponse = if (snapshot != null) {
                val movies = snapshot.toObjects(MovieFirebase::class.java)
                Response.Success( movies )
            } else {
                Response.Failure(e)
            }
            trySend( moviesResponse )
        }
        awaitClose {
            snapshotListener.remove()
        }
    }

    override suspend fun addMovieToFirestore(
        id: Int,
        title: String,
        posterPath: String,
        tipo: String,
        userId: String
    ) = try {
        val idFirebase = moviesRef.document().id
        val movie = MovieFirebase(
            id = id.toString(),
            title = title,
            posterPath = posterPath,
            tipo = tipo,
            idFirebase = idFirebase,
            userId = userId
        )
        moviesRef.document(idFirebase).set(movie).await()
        Response.Success(true)
    } catch (e: Exception) {
        Response.Failure(e)
    }


    override suspend fun deleteMovieToFirestore(idFirebase: String) = try {
        moviesRef.document(idFirebase).delete().await()
        Response.Success(true)
    } catch (e: Exception) {
        Response.Failure(e)
    }



}
