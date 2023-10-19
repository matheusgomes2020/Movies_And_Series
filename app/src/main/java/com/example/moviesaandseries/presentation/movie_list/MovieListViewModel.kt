package com.example.moviesaandseries.presentation.movie_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesaandseries.common.Constants
import com.example.moviesaandseries.common.Constants.TYPE
import com.example.moviesaandseries.common.Resource
import com.example.moviesaandseries.domain.use_case.get_movies.GetNowPlayingMoviesUseCase
import com.example.moviesaandseries.domain.use_case.get_movies.GetPopularMoviesUseCase
import com.example.moviesaandseries.domain.use_case.get_movies.GetRatedMoviesUseCase
import com.example.moviesaandseries.domain.use_case.get_movies.GetTrendingTodayMoviesUseCase
import com.example.moviesaandseries.domain.use_case.get_movies.GetUpcomingMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val getTrendingTodayMoviesUseCase: GetTrendingTodayMoviesUseCase,
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val getUpcomingMoviesUseCase: GetUpcomingMoviesUseCase,
    private val getNowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase,
    private val getRatedMoviesUseCase: GetRatedMoviesUseCase,
    savedStateHandle: SavedStateHandle

): ViewModel(){

    private val _stateTrendingToday = mutableStateOf(MovieListState())
    private val _statePopular = mutableStateOf(MovieListState())
    private val _stateUpcoming = mutableStateOf(MovieListState())
    private val _stateNowPlaying = mutableStateOf(MovieListState())
    private val _stateRated = mutableStateOf(MovieListState())

    val stateTrendingToday: State<MovieListState> = _stateTrendingToday
    val statePopular: State<MovieListState> = _statePopular
    val stateUpcoming: State<MovieListState> = _stateUpcoming
    val stateNowPlaying: State<MovieListState> = _stateNowPlaying
    val stateRated: State<MovieListState> = _stateRated

    init {
        savedStateHandle.get<String>(TYPE)?.let { type ->

        }
        getTrendingTodayMovies()
        getPopularMovies()
        getUpcomingMovies()
        getNowPlayingMovies()
        getRatedMovies()
    }

    private fun getTrendingTodayMovies() {
        getTrendingTodayMoviesUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _stateTrendingToday.value = MovieListState(movies = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _stateTrendingToday.value = MovieListState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _stateTrendingToday.value = MovieListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getPopularMovies() {
        getPopularMoviesUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _statePopular.value = MovieListState(movies = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _statePopular.value = MovieListState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _statePopular.value = MovieListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getUpcomingMovies() {
        getUpcomingMoviesUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _stateUpcoming.value = MovieListState(movies = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _stateUpcoming.value = MovieListState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _stateUpcoming.value = MovieListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getNowPlayingMovies() {
        getNowPlayingMoviesUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _stateNowPlaying.value = MovieListState(movies = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _stateNowPlaying.value = MovieListState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _stateNowPlaying.value = MovieListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getRatedMovies() {
        getRatedMoviesUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _stateRated.value = MovieListState(movies = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _stateRated.value = MovieListState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _stateRated.value = MovieListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }


}