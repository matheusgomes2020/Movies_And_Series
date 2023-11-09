package com.popcine.moviesaandseries.presentation.series_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.popcine.moviesaandseries.common.Resource
import com.popcine.moviesaandseries.domain.use_case.get_series.GetAiringTodayUseCase
import com.popcine.moviesaandseries.domain.use_case.get_series.GetOnAirSeriesUseCase
import com.popcine.moviesaandseries.domain.use_case.get_series.GetPopularSeriesUseCase
import com.popcine.moviesaandseries.domain.use_case.get_series.GetRatedSeriesUseCase
import com.popcine.moviesaandseries.domain.use_case.get_series.GetTrendingTodaySeriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SeriesListViewModel @Inject constructor(
    private val getTrendingTodaySeriesUseCase: GetTrendingTodaySeriesUseCase,
    private val getPopularSeriesUseCase: GetPopularSeriesUseCase,
    private val getOnAirSeriesUseCase: GetOnAirSeriesUseCase,
    private val getAiringTodayUseCase: GetAiringTodayUseCase,
    private val getRatedSeriesUseCase: GetRatedSeriesUseCase
): ViewModel() {
    private val _stateTrendingToday = mutableStateOf(SeriesListState())
    private val _statePopular = mutableStateOf(SeriesListState())
    private val _stateOnAir = mutableStateOf(SeriesListState())
    private val _stateAiringToday = mutableStateOf(SeriesListState())
    private val _stateRated = mutableStateOf(SeriesListState())
    val stateTrendingToday: State<SeriesListState> =_stateTrendingToday
    val statePopular: State<SeriesListState> =_statePopular
    val stateOnAir: State<SeriesListState> =_stateOnAir
    val stateAiryngToday: State<SeriesListState> = _stateAiringToday
    val stateRated: State<SeriesListState> =_stateRated

    init {

        getTrendingTodaySeries()
        getPopularSeries()
        getOnAirSeries()
        getAiringTodaySeries()
        getRatedSeries()
    }

    private fun getTrendingTodaySeries() {

        getTrendingTodaySeriesUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _stateTrendingToday.value = SeriesListState(series = result.data ?: emptyList() )
                }
                is Resource.Error -> {
                    _stateTrendingToday.value = SeriesListState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _stateTrendingToday.value = SeriesListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
    private fun getPopularSeries() {

        getPopularSeriesUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _statePopular.value = SeriesListState(series = result.data ?: emptyList() )
                }
                is Resource.Error -> {
                    _statePopular.value = SeriesListState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _statePopular.value = SeriesListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getOnAirSeries() {

        getOnAirSeriesUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _stateOnAir.value = SeriesListState(series = result.data ?: emptyList() )
                }
                is Resource.Error -> {
                    _stateOnAir.value = SeriesListState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _stateOnAir.value = SeriesListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getAiringTodaySeries() {

        getAiringTodayUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _stateAiringToday.value = SeriesListState(series = result.data ?: emptyList() )
                }
                is Resource.Error -> {
                    _stateAiringToday.value = SeriesListState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _stateAiringToday.value = SeriesListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }


    private fun getRatedSeries() {

        getRatedSeriesUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _stateRated.value = SeriesListState(series = result.data ?: emptyList() )
                }
                is Resource.Error -> {
                    _stateRated.value = SeriesListState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _stateRated.value = SeriesListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

}