package com.popcine.moviesaandseries.domain.use_case.get_series

import com.popcine.moviesaandseries.common.Resource
import com.popcine.moviesaandseries.data.remote.dto.series.toSeries
import com.popcine.moviesaandseries.domain.model.Series
import com.popcine.moviesaandseries.domain.repository.SeriesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetSeriesGenreUseCase @Inject constructor(
    private val repository: SeriesRepository
) {

    operator fun invoke( pageNumber: String, genreId: String ): Flow<Resource<List<Series>>> = flow {

        try {
            emit(Resource.Loading<List<Series>>())
            val series = repository.getSeriesGenre( pageNumber, genreId ).map { it.toSeries() }
            emit(Resource.Success<List<Series>>( series ))
        } catch(e: HttpException) {
            emit(Resource.Error<List<Series>>(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(Resource.Error<List<Series>>("Couldn't reach server. Check your internet connection."))
        }
    }
}