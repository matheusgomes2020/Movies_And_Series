package com.popcine.moviesaandseries.presentation.person_Image

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.popcine.moviesaandseries.common.Constants


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