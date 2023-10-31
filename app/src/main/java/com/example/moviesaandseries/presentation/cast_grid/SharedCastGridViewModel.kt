package com.example.moviesaandseries.presentation.cast_grid

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.moviesaandseries.data.remote.dto.movies.Cast

class SharedCastGridViewModel: ViewModel() {

    var cast by mutableStateOf<List<Cast>?>(null)
        private set

    fun getCast(newCast: List<Cast>) {
        Log.d("Sera?", "addCast:$newCast ")
        cast = newCast
    }

}