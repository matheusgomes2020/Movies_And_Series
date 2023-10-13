package com.example.moviesaandseries.presentation.favorites

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesaandseries.common.Resource
import com.example.moviesaandseries.domain.model.Movie
import com.example.moviesaandseries.domain.model.Response
import com.example.moviesaandseries.domain.repository.AddMovieResponse
import com.example.moviesaandseries.domain.repository.DeleteMovieResponse
import com.example.moviesaandseries.domain.repository.MoviesFirebaseRepository
import com.example.moviesaandseries.domain.repository.MoviesResponse
import com.example.moviesaandseries.domain.use_case.movies_firestore.GetMovies
import com.example.moviesaandseries.domain.use_case.movies_firestore.UseCases
import com.example.moviesaandseries.presentation.episode.EpisodeDetailState
import com.example.moviesaandseries.presentation.movie_list.MovieListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val useCases: UseCases
): ViewModel() {
    private val _state = mutableStateOf(MovieFirebaseState())
    val state: State<MovieFirebaseState> = _state
    var moviesResponse by mutableStateOf<MoviesResponse>(Response.Loading)
        private set
    var addMovieResponse by mutableStateOf<AddMovieResponse>(Response.Success(false))
        private set
    var deleteMovieResponse by mutableStateOf<DeleteMovieResponse>(Response.Success(false))
        private set

    init {
        getmovies2()
        getmovies()
    }

    private fun getmovies() = viewModelScope.launch {
        useCases.getMovies().collect { response ->
            moviesResponse = response
            Log.d("FFFFIRRE", "getmovies: " + response)
        }
    }

    private fun getmovies2() {
        Log.d("FFFFIRRE", "getmovies2: chama?????")
        useCases.getMovies().onEach { response ->
            Log.d("FFFFIRRE", "RRRRRRRRRRRRRRRRRR:  " + response)
            when (response) {
                is Response.Success -> {
                    _state.value = MovieFirebaseState(movies = response.data ?: emptyList())
                }
                is Response.Failure -> {
                    _state.value = MovieFirebaseState(
                        error = response.toString()
                    )
                }
                is Response.Loading -> {
                    _state.value = MovieFirebaseState(isLoading = true)
                }
            }
            //moviesResponse = response
            Log.d("FFFFIRRE", "getmovies22222222: " + response)
        }.launchIn(viewModelScope)
    }

    fun addMovie(id: Int, title: String,
                posterPath: String,
                userId: String) = viewModelScope.launch {
        Log.d("FFFFIRRE", "Add:  $id, $title, $posterPath, $userId")
        addMovieResponse = Response.Loading
        addMovieResponse = useCases.addMovie(id, title, posterPath, userId)
        Log.d("FFFFIRRE", "Add: " + addMovieResponse.toString())

    }

    fun deleteMovie(idFirebase: String) = viewModelScope.launch {
        deleteMovieResponse = Response.Loading
        deleteMovieResponse = useCases.deleteMovie( idFirebase )
    }
}



