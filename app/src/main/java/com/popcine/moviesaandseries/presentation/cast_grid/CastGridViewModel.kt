package com.popcine.moviesaandseries.presentation.cast_grid

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.popcine.moviesaandseries.data.remote.dto.movies.Cast

class CastGridViewModel: ViewModel() {

    var cast by mutableStateOf<List<Cast>?>(null)
        private set

    fun getCast(newCast: List<Cast>) {
        cast = newCast
    }

}