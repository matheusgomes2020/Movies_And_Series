package com.popcine.moviesaandseries.presentation.searchSeries

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.popcine.moviesaandseries.common.Constants
import com.popcine.moviesaandseries.common.Resource
import com.popcine.moviesaandseries.domain.use_case.search_series.SearchSeriesUseCase
import com.popcine.moviesaandseries.presentation.series_list.SeriesListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SearchSeriesViewModel @Inject constructor(
    private val searchSeriesUseCase: SearchSeriesUseCase,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _state = mutableStateOf(SeriesListState())
    val state: State<SeriesListState> = _state
    init {
        savedStateHandle.get<String>(Constants.PARAM_SEARCH_SERIES)?.let { querySeries ->
            searchSeries( querySeries )
        }
   }



      fun searchSeries(query: String ) {
         Log.d("BATATAO", "VIEW MODEL searchSéries: $query")

        searchSeriesUseCase( query ).onEach { result ->
            Log.d("BATATAO", "VIEW MODEL result: $result")
            when (result) {
                is Resource.Success -> {
                    _state.value = SeriesListState(series = result.data ?: emptyList())
                    Log.d("BATATAO", "VIEW MODEL state value: ${state.value}")
                }

                is Resource.Error -> {
                    _state.value = SeriesListState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }

                is Resource.Loading -> {
                    _state.value = SeriesListState(isLoading = true)
                }
            }
        }.launchIn( viewModelScope )
    }

//    fun searchNew(query: String ) {
//        Log.d("BATATAO", "VIEW MODEL searchSéries: $query")
//
//        searchSeriesUseCase( query ).onEach { result ->
//            Log.d("BATATAO", "VIEW MODEL result: $result")
//            when (result) {
//                is Resource.Success -> {
//                    _stateNewSearch.value = SeriesListState(series = result.data ?: emptyList())
//                    Log.d("BATATAO", "VIEW MODEL state value: ${state.value}")
//                }
//
//                is Resource.Error -> {
//                    _stateNewSearch.value = SeriesListState(
//                        error = result.message ?: "An unexpected error occured"
//                    )
//                }
//
//                is Resource.Loading -> {
//                    _stateNewSearch.value = SeriesListState(isLoading = true)
//                }
//            }
//        }.launchIn( viewModelScope )
//    }
//
//    fun resetState() {
//        _state.update {
//            SeriesListState()
//        }
//    }
}

