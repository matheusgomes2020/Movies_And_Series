package com.example.moviesaandseries.presentation.season

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesaandseries.common.Constants
import com.example.moviesaandseries.common.Resource
import com.example.moviesaandseries.domain.use_case.get_season.GetSeasonUseCase
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

        var a = ""
        var b = 0

        savedStateHandle.get<String>(Constants.PARAM_SERIES_ID)?.let { seriesId ->
            a = seriesId
        }
        savedStateHandle.get<Int>(Constants.PARAM_SEASON_NUMBER)?.let { seriesId ->
            b = seriesId
        }

        getSeason(a,b)
    }

    private fun getSeason(seriesId: String, seasonNumber: Int) {
        getSeasonUseCase(seriesId, seasonNumber).onEach { result ->

            when (result) {
                is Resource.Success -> {
                    _state.value = SeasonDetailState(season = result.data)
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
