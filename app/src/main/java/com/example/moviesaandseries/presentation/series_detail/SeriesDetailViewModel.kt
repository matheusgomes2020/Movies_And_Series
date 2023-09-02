package com.example.moviesaandseries.presentation.series_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesaandseries.common.Constants
import com.example.moviesaandseries.common.Resource
import com.example.moviesaandseries.domain.use_case.get_serie.GetSerieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SeriesDetailViewModel @Inject constructor(
    private val getSeriesUseCase: GetSerieUseCase,
    savedStateHandle: SavedStateHandle
): ViewModel(){

    private val _state = mutableStateOf(SeriesDetailState())
    val state: State<SeriesDetailState> = _state

    init {
        savedStateHandle.get<String>(Constants.PARAM_SERIES_ID)?.let { seriesId ->
            getSeries( seriesId )
        }
    }

    private fun getSeries(seriesId: String ) {
        getSeriesUseCase( seriesId ).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = SeriesDetailState(series = result.data)
                }
                is Resource.Error -> {
                    _state.value = SeriesDetailState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _state.value = SeriesDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}