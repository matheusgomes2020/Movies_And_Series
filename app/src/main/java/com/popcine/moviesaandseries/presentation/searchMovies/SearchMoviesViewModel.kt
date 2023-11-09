package com.popcine.moviesaandseries.presentation.searchMovies

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.popcine.moviesaandseries.common.Constants
import com.popcine.moviesaandseries.common.Resource
import com.popcine.moviesaandseries.domain.use_case.search_movies.SearchMoviesUseCase
import com.popcine.moviesaandseries.presentation.movie_list.MovieListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SearchMoviesViewModel @Inject constructor(
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



      fun searchMovies(query: String ) {

        searchMoviesUseCase( query ).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = MovieListState(movies = result.data ?: emptyList())
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