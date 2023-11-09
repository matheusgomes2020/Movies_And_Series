package com.popcine.moviesaandseries.domain.use_case.get_serie

import com.popcine.moviesaandseries.common.Resource
import com.popcine.moviesaandseries.data.remote.dto.series.toSeriesDetail
import com.popcine.moviesaandseries.domain.model.SeriesDetail
import com.popcine.moviesaandseries.domain.repository.SeriesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


class GetSerieUseCase @Inject constructor(
    private val repository: SeriesRepository
) {
    operator fun invoke(seriesID: String ): Flow<Resource<SeriesDetail>> = flow {

        try {
            emit(Resource.Loading<SeriesDetail>())
            val series = repository.getSeriesInfo( seriesID ).toSeriesDetail()
            emit(Resource.Success<SeriesDetail>(series))
        } catch(e: HttpException) {
            emit(Resource.Error<SeriesDetail>(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(Resource.Error<SeriesDetail>("Couldn't reach server. Check your internet connection."))
        }
    }
}