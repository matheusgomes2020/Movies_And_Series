package com.popcine.moviesaandseries.domain.use_case.get_movies

import com.popcine.moviesaandseries.common.Resource
import com.popcine.moviesaandseries.data.remote.dto.movies.toMovie
import com.popcine.moviesaandseries.domain.model.Movie
import com.popcine.moviesaandseries.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetUpcomingMoviesUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    operator fun invoke(): Flow<Resource<List<Movie>>> = flow {

        try {
            emit(Resource.Loading<List<Movie>>())
            val movies = repository.getUpcomingMovies().map { it.toMovie() }
            emit(Resource.Success<List<Movie>>(movies))
        } catch(e: HttpException) {
            emit(Resource.Error<List<Movie>>(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(Resource.Error<List<Movie>>("Couldn't reach server. Check your internet connection."))
        }
    }
}