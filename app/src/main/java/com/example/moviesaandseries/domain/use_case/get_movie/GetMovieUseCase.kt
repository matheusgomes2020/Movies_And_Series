package com.example.moviesaandseries.domain.use_case.get_movie

import com.example.moviesaandseries.common.Resource
import com.example.moviesaandseries.data.remote.dto.toMovieDetail
import com.example.moviesaandseries.domain.model.Movie
import com.example.moviesaandseries.domain.model.MovieDetail
import com.example.moviesaandseries.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetMovieUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    operator fun invoke(movieId: String ): Flow<Resource<MovieDetail>> = flow {

        try {
            emit(Resource.Loading<MovieDetail>())
            val movie = repository.getMovieInfo( movieId ).toMovieDetail()
            emit(Resource.Success<MovieDetail>(movie))
        } catch(e: HttpException) {
            emit(Resource.Error<MovieDetail>(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(Resource.Error<MovieDetail>("Couldn't reach server. Check your internet connection."))
        }
    }
}