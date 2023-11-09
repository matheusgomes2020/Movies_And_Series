package com.popcine.moviesaandseries.di

import com.google.firebase.auth.FirebaseAuth
import com.popcine.moviesaandseries.common.Constants
import com.popcine.moviesaandseries.common.Constants.MOVIES
import com.popcine.moviesaandseries.data.remote.CastApi
import com.popcine.moviesaandseries.data.remote.MovieApi
import com.popcine.moviesaandseries.data.remote.SeriesApi
import com.popcine.moviesaandseries.data.repository.MovieRepositoryImpl
import com.popcine.moviesaandseries.data.repository.MoviesFirebaseRepositoryImpl
import com.popcine.moviesaandseries.data.repository.PersonRepositoryImpl
import com.popcine.moviesaandseries.data.repository.SeriesRepositoryImpl
import com.popcine.moviesaandseries.domain.repository.MovieRepository
import com.popcine.moviesaandseries.domain.repository.MoviesFirebaseRepository
import com.popcine.moviesaandseries.domain.repository.PersonRepository
import com.popcine.moviesaandseries.domain.repository.SeriesRepository
import com.popcine.moviesaandseries.domain.use_case.movies_firestore.AddMovie
import com.popcine.moviesaandseries.domain.use_case.movies_firestore.DeleteMovie
import com.popcine.moviesaandseries.domain.use_case.movies_firestore.GetMovies
import com.popcine.moviesaandseries.domain.use_case.movies_firestore.UseCases
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.popcine.moviesaandseries.domain.repository.AuthRepository
import com.popcine.moviesaandseries.data.repository.AuthRepositoryImpl
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
    fun provideFirebaseRepository(
        moviesRef: CollectionReference
    ): MoviesFirebaseRepository = MoviesFirebaseRepositoryImpl(moviesRef)

    @Provides
    fun provideUseCases(
        repo: MoviesFirebaseRepository,
    ) = UseCases(
        getMovies = GetMovies(repo),
        addMovie = AddMovie(repo),
        deleteMovie = DeleteMovie(repo)
    )

    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    fun providesAuthRepository(impl: AuthRepositoryImpl): AuthRepository = impl


}