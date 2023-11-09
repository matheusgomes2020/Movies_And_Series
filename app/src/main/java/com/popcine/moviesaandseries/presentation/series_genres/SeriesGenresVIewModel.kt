package com.popcine.moviesaandseries.presentation.series_genres

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.popcine.moviesaandseries.common.Constants.GENRE_ID
import com.popcine.moviesaandseries.common.Constants.GENRE_TITLE
import com.popcine.moviesaandseries.common.Constants.PAGE_NUMBER
import com.popcine.moviesaandseries.common.Resource
import com.popcine.moviesaandseries.domain.use_case.get_series.GetSeriesGenreUseCase
import com.popcine.moviesaandseries.presentation.series_list.SeriesListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SeriesGenresVIewModel @Inject constructor(
    private val getSeriesGenreUseCase: GetSeriesGenreUseCase,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _state = mutableStateOf(SeriesListState())

    val state: State<SeriesListState> = _state

    init {

        var pageNumberHandle = ""
        var genreIDHandle = ""
        var genreNameHandle = ""
        savedStateHandle.get<String>(PAGE_NUMBER)?.let { pageNumber ->
            pageNumberHandle = pageNumber
        }
        savedStateHandle.get<String>(GENRE_ID)?.let { genreID ->
            genreIDHandle = genreID
        }
        savedStateHandle.get<String>(GENRE_TITLE)?.let { genreTitle ->
            genreNameHandle = genreTitle
        }
        getMoviesGenre( pageNumberHandle, genreIDHandle )
    }


    private fun getMoviesGenre( pageNumber: String, genreId: String ) {
        getSeriesGenreUseCase( pageNumber, genreId ).onEach { result ->
            when (result) {
                is  Resource.Success -> {
                    _state.value = SeriesListState(series = result.data ?: emptyList() )
                }
                is Resource.Error -> {
                    _state.value = SeriesListState(
                        error =  result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _state.value = SeriesListState(
                        isLoading = true
                    )
                }
            }
        }.launchIn( viewModelScope )
    }

}
