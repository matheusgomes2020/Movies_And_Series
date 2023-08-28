package com.example.moviesaandseries.domain.use_case.get_movies

import com.example.moviesaandseries.common.Resource
import com.example.moviesaandseries.data.remote.dto.toMovie
import com.example.moviesaandseries.domain.model.Movie
import com.example.moviesaandseries.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetRatedMoviesUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    operator fun invoke(): Flow<Resource<List<Movie>>> = flow {

        try {
            emit(Resource.Loading<List<Movie>>())
            val movies = repository.getRatedMovies().map { it.toMovie() }
            emit(Resource.Success<List<Movie>>(movies))
        } catch(e: HttpException) {
            emit(Resource.Error<List<Movie>>(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(Resource.Error<List<Movie>>("Couldn't reach server. Check your internet connection."))
        }
    }
}