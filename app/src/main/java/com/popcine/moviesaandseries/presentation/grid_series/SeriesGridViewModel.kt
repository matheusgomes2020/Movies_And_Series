package com.popcine.moviesaandseries.presentation.grid_series

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.popcine.moviesaandseries.domain.model.Series

class SeriesGridViewModel: ViewModel() {

    var series by mutableStateOf<List<Series>?>(null)
        private set

    fun getSeries(newSeries: List<Series>) {
        series = newSeries
    }
}