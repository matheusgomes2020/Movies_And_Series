package com.example.moviesaandseries.presentation.episode

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesaandseries.common.Constants
import com.example.moviesaandseries.common.Resource
import com.example.moviesaandseries.domain.use_case.get_episode.GetEpisodeUseCase

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class EpisodeViewModel @Inject constructor(
    private val getEpisodeUseCase: GetEpisodeUseCase,
    savedStateHandle: SavedStateHandle

): ViewModel() {
    private val _state = mutableStateOf(EpisodeDetailState())
    val state: State<EpisodeDetailState> = _state

    init {

        var a = ""
        var b = ""
        var c = ""

        savedStateHandle.get<String>(Constants.PARAM_SERIES_ID)?.let { seriesId ->
            a = seriesId
        }
        savedStateHandle.get<String>(Constants.PARAM_SEASON_NUMBER)?.let { seasonNumber ->
            b = seasonNumber
        }

        savedStateHandle.get<String>(Constants.PARAM_EPISODE_NUMBER)?.let { episodeNumber ->
            c = episodeNumber
        }

        getEpisode( a, b, c )
        Log.d("BATATA", "INIT ViewModel: id: $a | season number: $b | episode number: $c")
    }

    private fun getEpisode(seriesId: String, seasonNumber: String, episodeNumber: String) {
        Log.d("BATATA", "CHAMA FUNÇÃO getEpisode: id: $seriesId | season number: $seasonNumber | episode number: $episodeNumber")
       getEpisodeUseCase( seriesId, seasonNumber, episodeNumber ).onEach { result ->
           Log.d("BATATA", "ViewMoDel use case | getEpisode: ${result.data}" )
           when (result) {
               is Resource.Success -> {
                   _state.value = EpisodeDetailState(episode = result.data)
                   Log.d("BATATA", "ViewMoel | getEpisode: $result" )
               }
               is Resource.Error -> {
                   _state.value = EpisodeDetailState(

                       error = result.message ?: "An unexpected error occured"


                   )
               }
               is Resource.Loading -> {
                   _state.value = EpisodeDetailState(isLoading = true)
                   Log.d("BATATA", "ViewMoel CARR | getEpisode: $result" )
               }

           }
       }.launchIn(viewModelScope)
    }
}