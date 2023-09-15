package com.example.moviesaandseries.presentation.cast.castImage

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.unit.Constraints
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.moviesaandseries.common.Constants
import dagger.hilt.android.lifecycle.HiltViewModel


class ImagePersonViewModel(
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _state = mutableStateOf(PersonImageState())
    val state: MutableState<PersonImageState> = _state

    init {
        savedStateHandle.get<String>(Constants.IMAGE_FILE_PATH)?.let { imagePath ->
            Log.d("NEYMAR", "INIT: ${imagePath}")
            getImage( imagePath )
        }
    }

    private fun getImage(image: String){
        Log.d("NEYMAR", "getImage: ${image}")
        _state.value = PersonImageState(image = image)
    }

}