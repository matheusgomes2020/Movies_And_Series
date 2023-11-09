package com.popcine.moviesaandseries.presentation.season

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.popcine.moviesaandseries.common.Constants
import com.popcine.moviesaandseries.common.Resource
import com.popcine.moviesaandseries.domain.use_case.get_season.GetSeasonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SeasonDetailViewModel @Inject constructor(
    private val getSeasonUseCase: GetSeasonUseCase,
    savedStateHandle: SavedStateHandle

): ViewModel() {

    private val _state = mutableStateOf(SeasonDetailState())
    val state: State<SeasonDetailState> = _state

    init {

        var seriesIdHandle = ""
        var seasonNumberHandle = ""

        savedStateHandle.get<String>(Constants.PARAM_SERIES_ID)?.let { seriesId ->
            seriesIdHandle = seriesId
        }
        savedStateHandle.get<String>(Constants.PARAM_SEASON_NUMBER)?.let { seasonNumber ->
            seasonNumberHandle = seasonNumber
        }

        getSeason(seriesIdHandle,seasonNumberHandle)
        Log.d("BATATA", "INIT ViewModel: id: $seriesIdHandle | season number: $seasonNumberHandle")
    }

    private fun getSeason(seriesId: String, seasonNumber: String) {
        Log.d("BATATA", "CHAMA FUNÇÃO getSeason: id: $seriesId | season number: $seasonNumber")
        getSeasonUseCase(seriesId, seasonNumber).onEach { result ->

            when (result) {
                is Resource.Success -> {
                    _state.value = SeasonDetailState(season = result.data)
                    Log.d("BATATA", "SEASON | getEpisode: ${result.data}" )
                }

                is Resource.Error -> {
                    _state.value = SeasonDetailState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }

                is Resource.Loading -> {
                    _state.value = SeasonDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}
