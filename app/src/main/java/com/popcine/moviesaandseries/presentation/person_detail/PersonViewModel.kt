package com.popcine.moviesaandseries.presentation.person_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.popcine.moviesaandseries.common.Constants
import com.popcine.moviesaandseries.common.Resource
import com.popcine.moviesaandseries.domain.use_case.get_person.GetPersonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PersonViewModel @Inject constructor(
    private val getPersonUseCase: GetPersonUseCase,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _state = mutableStateOf(PersonDetailState())
    val state: State<PersonDetailState> = _state

    init {
        savedStateHandle.get<String>(Constants.PARAM_PERSON_ID)?.let { castId ->
            getPerson( castId )
        }
    }

    private fun getPerson( castId: String ) {
        getPersonUseCase(castId).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = PersonDetailState(person = result.data)
                }
                is Resource.Error -> {
                    _state.value = PersonDetailState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _state.value = PersonDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

}