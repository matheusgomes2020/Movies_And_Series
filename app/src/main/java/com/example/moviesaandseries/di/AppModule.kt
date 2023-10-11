package com.example.moviesaandseries.di

import com.example.moviesaandseries.common.Constants
import com.example.moviesaandseries.common.Constants.MOVIES
import com.example.moviesaandseries.data.remote.CastApi
import com.example.moviesaandseries.data.remote.MovieApi
import com.example.moviesaandseries.data.remote.SeriesApi
import com.example.moviesaandseries.data.repository.MovieRepositoryImpl
import com.example.moviesaandseries.data.repository.MoviesFirebaseRepositoryImpl
import com.example.moviesaandseries.data.repository.PersonRepositoryImpl
import com.example.moviesaandseries.data.repository.SeriesRepositoryImpl
import com.example.moviesaandseries.domain.repository.MovieRepository
import com.example.moviesaandseries.domain.repository.MoviesFirebaseRepository
import com.example.moviesaandseries.domain.repository.PersonRepository
import com.example.moviesaandseries.domain.repository.SeriesRepository
import com.example.moviesaandseries.domain.use_case.movies_firestore.AddMovie
import com.example.moviesaandseries.domain.use_case.movies_firestore.DeleteMovie
import com.example.moviesaandseries.domain.use_case.movies_firestore.GetMovies
import com.example.moviesaandseries.domain.use_case.movies_firestore.UseCases
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn( SingletonComponent::class )
object AppModule {

    @Singleton
    @Provides
    fun providesMoviesApi() : MovieApi {

        return Retrofit.Builder()
            .baseUrl( Constants.BASE_URL )
            .addConverterFactory( GsonConverterFactory.create() )
            .build()
            .create( MovieApi::class.java )

    }

    @Provides
    @Singleton
    fun provideMovieRepository(api: MovieApi): MovieRepository {
        return MovieRepositoryImpl(api)
    }

    @Singleton
    @Provides
    fun providesSeriesApi() : SeriesApi {

        return Retrofit.Builder()
            .baseUrl( Constants.BASE_URL )
            .addConverterFactory( GsonConverterFactory.create() )
            .build()
            .create( SeriesApi::class.java )

    }

    @Provides
    @Singleton
    fun provideSeriesRepository(api: SeriesApi): SeriesRepository {
        return SeriesRepositoryImpl(api)
    }

    @Singleton
    @Provides
    fun providesCastApi() : CastApi {

        return Retrofit.Builder()
            .baseUrl( Constants.BASE_URL )
            .addConverterFactory( GsonConverterFactory.create() )
            .build()
            .create( CastApi::class.java )

    }

    @Provides
    @Singleton
    fun providePersonRepository(api: CastApi): PersonRepository {
        return PersonRepositoryImpl(api)
    }

    @Provides
    fun provideMoviesRef() = Firebase.firestore.collection(MOVIES)

    @Provides
    fun provideBooksRepository(
        moviesRef: CollectionReference
    ): MoviesFirebaseRepository = MoviesFirebaseRepositoryImpl(moviesRef)

    @Provides
    fun provideUseCases(
        repo: MoviesFirebaseRepository
    ) = UseCases(
        getMovies = GetMovies(repo),
        addMovie = AddMovie(repo),
        deleteMovie = DeleteMovie(repo)
    )


}