package com.example.moviesaandseries.di

import com.example.moviesaandseries.common.Constants
import com.example.moviesaandseries.data.remote.MovieApi
import com.example.moviesaandseries.data.repository.MovieRepositoryImpl
import com.example.moviesaandseries.domain.repository.MovieRepository
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

}