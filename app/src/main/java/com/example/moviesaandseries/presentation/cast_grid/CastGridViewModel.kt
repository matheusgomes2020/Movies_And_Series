package com.example.moviesaandseries.presentation.cast_grid

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.moviesaandseries.common.Constants
import com.example.moviesaandseries.common.navigation.MoviesDetailsGraph.CAST
import com.example.moviesaandseries.data.remote.dto.movies.Cast
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CastGridViewModel  @Inject constructor(
    savedStateHandle: SavedStateHandle
) :ViewModel() {

    private val _state = mutableStateOf(CastGridState())
    val state: State<CastGridState> = _state

    init {
        getCast( savedStateHandle.get<List<Cast>>(CAST)!! )
    }

    private fun getCast( cast: List<Cast> ) {
        _state.value = CastGridState(cast = cast)
    }


}