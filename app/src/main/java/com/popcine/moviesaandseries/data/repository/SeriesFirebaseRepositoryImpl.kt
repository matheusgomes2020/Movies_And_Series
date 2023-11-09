package com.popcine.moviesaandseries.data.repository

//import com.example.moviesaandseries.common.Constants.SERIES
//import com.example.moviesaandseries.domain.model.Response
//import com.example.moviesaandseries.domain.model.SeriesFirebase
//import com.example.moviesaandseries.domain.repository.AddSeriesResponse
//import com.example.moviesaandseries.domain.repository.DeleteSeriesResponse
//import com.example.moviesaandseries.domain.repository.MoviesFirebaseRepository
//import com.example.moviesaandseries.domain.repository.SeriesFirebaseRepository
//import com.google.firebase.firestore.CollectionReference
//import kotlinx.coroutines.channels.awaitClose
//import kotlinx.coroutines.flow.callbackFlow
//import kotlinx.coroutines.tasks.await
//import javax.inject.Inject
//import javax.inject.Named
//import javax.inject.Singleton

//@Singleton
//class SeriesFirebaseRepositoryImpl @Inject constructor(
//    @Named(SERIES)
//    private val seriesRef: CollectionReference
//) : SeriesFirebaseRepository {
//    override fun getSeriesFromFirestore() = callbackFlow {
//        val snapshotListener = seriesRef.addSnapshotListener { snapshot, e ->
//            val seriesResponse = if (snapshot != null) {
//                val series = snapshot.toObjects(SeriesFirebase::class.java)
//                Response.Success( series )
//            } else {
//                Response.Failure(e)
//            }
//            trySend( seriesResponse )
//        }
//        awaitClose {
//            snapshotListener.remove()
//        }
//    }
//
//    override suspend fun addSeriesToFirestore(
//        id: Int,
//        name: String,
//        posterPath: String,
//        userId: String
//    )= try {
//        val idFirebase = seriesRef.document().id
//        val series = SeriesFirebase(
//            id = id.toString(),
//            name = name,
//            posterPath = posterPath,
//            idFirebase = idFirebase,
//            userId = userId
//        )
//        seriesRef.document(idFirebase).set( series ).await()
//        Response.Success(true)
//    } catch (e: Exception) {
//        Response.Failure(e)
//    }
//
//    override suspend fun deleteSeriesToFirestore(idFirebase: String)= try {
//        seriesRef.document(idFirebase).delete().await()
//        Response.Success(true)
//    } catch (e: Exception) {
//        Response.Failure(e)
//    }
//
//}
