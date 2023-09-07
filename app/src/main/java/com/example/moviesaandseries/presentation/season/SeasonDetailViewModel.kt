package com.example.moviesaandseries.presentation.season

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesaandseries.common.Resource
import com.example.moviesaandseries.domain.use_case.get_season.GetSeasonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SeasonDetailViewModel @Inject constructor(
    private val getSeasonUseCase: GetSeasonUseCase,

): ViewModel() {

    private val _state = mutableStateOf(SeasonDetailState())
    val state: State<SeasonDetailState> = _state


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
