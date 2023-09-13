package com.example.moviesaandseries.presentation.episode

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.moviesaandseries.common.Constants
import com.example.moviesaandseries.common.Resource

import com.example.moviesaandseries.presentation.cast.CastDetailState
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class EpisodeViewModel(
    savedStateHandle: SavedStateHandle

): ViewModel() {
    private val _state = mutableStateOf(EpisodeDetailState())
    val state: State<EpisodeDetailState> = _state


/*
    fun getEpisode(episode: Resource<Episode>) {
        episode.let { ep ->
            when (ep) {
                is Resource.Success -> {
                    _state.value = EpisodeDetailState(episode = ep.data)
                }
                is Resource.Error -> {
                    _state.value = EpisodeDetailState(
                        error = ep.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _state.value = EpisodeDetailState(isLoading = true)
                }

                else -> {}
            }
        }
    }


 */
}