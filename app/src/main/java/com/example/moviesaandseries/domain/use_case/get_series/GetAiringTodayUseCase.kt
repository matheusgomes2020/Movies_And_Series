package com.example.moviesaandseries.domain.use_case.get_series

import com.example.moviesaandseries.common.Resource
import com.example.moviesaandseries.data.remote.dto.series.toSeries
import com.example.moviesaandseries.domain.model.Series
import com.example.moviesaandseries.domain.repository.SeriesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetAiringTodayUseCase @Inject constructor(
    private val repository: SeriesRepository
) {
    operator fun invoke(): Flow<Resource<List<Series>>> = flow {

        try {
            emit(Resource.Loading<List<Series>>())
            val series = repository.getAiryingtodaySeries().map { it.toSeries() }
            emit(Resource.Success<List<Series>>(series))
        } catch(e: HttpException) {
            emit(Resource.Error<List<Series>>(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(Resource.Error<List<Series>>("Couldn't reach server. Check your internet connection."))
        }
    }
}