package com.example.moviesaandseries.presentation.search

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesaandseries.common.Constants
import com.example.moviesaandseries.common.Resource
import com.example.moviesaandseries.domain.use_case.search_movies.SearchMoviesUseCase
import com.example.moviesaandseries.presentation.movie_list.MovieListState
import com.example.moviesaandseries.presentation.season.SeasonDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchMoviesUseCase: SearchMoviesUseCase,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _state = mutableStateOf(MovieListState())
    val state: State<MovieListState> = _state
    init {
        savedStateHandle.get<String>(Constants.PARAM_SEARCH_MOVIE)?.let { queryMovie ->
            searchMovies( queryMovie )
        }
   }



     private fun searchMovies(query: String ) {
         Log.d("BATATAO", "VIEW MODEL searchMovies: $query")

        searchMoviesUseCase( query ).onEach { result ->
            Log.d("BATATAO", "VIEW MODEL result: $result")
            when (result) {
                is Resource.Success -> {
                    _state.value = MovieListState(movies = result.data ?: emptyList())
                    Log.d("BATATAO", "VIEW MODEL state value: ${state.value}")
                }

                is Resource.Error -> {
                    _state.value = MovieListState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }

                is Resource.Loading -> {
                    _state.value = MovieListState(isLoading = true)
                }
            }
        }.launchIn( viewModelScope )
    }
}