package com.example.moviesaandseries.presentation.cast

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesaandseries.common.Constants
import com.example.moviesaandseries.common.Resource
import com.example.moviesaandseries.domain.use_case.get_person.GetPersonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CastViewModel @Inject constructor(
    private val getPersonUseCase: GetPersonUseCase,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _state = mutableStateOf(CastDetailState())
    val state: State<CastDetailState> = _state

    init {
        savedStateHandle.get<String>(Constants.PARAM_PERSON_ID)?.let { castId ->
            getPerson( castId )
            Log.d("hdygfd", "$castId: ")
        }
    }

    private fun getPerson( castId: String ) {
        getPersonUseCase(castId).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = CastDetailState(person = result.data)
                }
                is Resource.Error -> {
                    _state.value = CastDetailState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _state.value = CastDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

}