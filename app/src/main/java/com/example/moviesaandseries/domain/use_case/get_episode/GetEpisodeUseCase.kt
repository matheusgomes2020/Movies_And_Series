package com.example.moviesaandseries.domain.use_case.get_episode

import android.util.Log
import com.example.moviesaandseries.common.Resource
import com.example.moviesaandseries.data.remote.dto.episode.toEpisodeDetail
import com.example.moviesaandseries.domain.model.EpisodeDetail
import com.example.moviesaandseries.domain.repository.SeriesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetEpisodeUseCase @Inject constructor(
    private val repository: SeriesRepository
) {
    operator fun invoke(seriesId: String, seasonNumber: String, episodeNumber:String ): Flow<Resource<EpisodeDetail>> = flow {

        try {
            emit(Resource.Loading<EpisodeDetail>())
            val episode = repository.getEpisodeInfo( seriesId, seasonNumber, episodeNumber ).toEpisodeDetail()
            emit(Resource.Success<EpisodeDetail>(episode))
        } catch(e: HttpException) {
            emit(Resource.Error<EpisodeDetail>(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(Resource.Error<EpisodeDetail>("Couldn't reach server. Check your internet connection."))
        }
    }
}