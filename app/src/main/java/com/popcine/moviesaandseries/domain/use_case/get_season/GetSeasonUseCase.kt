package com.popcine.moviesaandseries.domain.use_case.get_season

import android.util.Log
import com.popcine.moviesaandseries.common.Resource
import com.popcine.moviesaandseries.data.remote.dto.season.toSeasonDetail
import com.popcine.moviesaandseries.domain.model.SeasonDetail
import com.popcine.moviesaandseries.domain.repository.SeriesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetSeasonUseCase @Inject constructor(
    private val repository: SeriesRepository
) {
    operator fun invoke(seriesId: String, seasonNumber: String): Flow<Resource<SeasonDetail>> = flow {

        try {
            emit(Resource.Loading<SeasonDetail>())
            val season = repository.getSeasonInfo( seriesId, seasonNumber ).toSeasonDetail()
            Log.d("BATATA", "SEASON GetEpisodeUseCase | getEpisode: $seriesId | $seasonNumber\n$season" )
            emit(Resource.Success<SeasonDetail>(season))
        } catch(e: HttpException) {
            emit(Resource.Error<SeasonDetail>(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(Resource.Error<SeasonDetail>("Couldn't reach server. Check your internet connection."))
        }
    }
}