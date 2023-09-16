package com.example.moviesaandseries.presentation.movie_list

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.moviesaandseries.common.navigation.AppGraph
import com.example.moviesaandseries.presentation.search.SearchViewModel


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MovieListScreen(
    navController: NavController,
    viewModel: MovieListViewModel = hiltViewModel(),
    searchViewModel: SearchViewModel = hiltViewModel()
){

    val state = viewModel.statePopular.value
    val state2 = viewModel.stateUpcoming.value
    val state3 = viewModel.stateNowPlaying.value
    val state4 = viewModel.stateRated.value

    var queryMovie by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }
    var items = remember {
        mutableStateListOf(
            ""
        )
    }

    Box(modifier = Modifier.fillMaxSize()) {
        state.movies?.let { movies ->
            Scaffold(

            ) {
                Column {
                    SearchBar(
                        modifier = Modifier.fillMaxWidth(),
                        query = queryMovie,
                        onQueryChange = {
                            queryMovie = it
                        },
                        onSearch = {
                            //items.add(text)
//                            searchViewModel.searchMovies(text).let {
//                                Log.d("BATATAO", "HomeScreen: ${searchViewModel.state.value.movies}")
//                            }
                            //try {
                                navController.navigate( AppGraph.search_movies.DETAILS + "/${queryMovie}" )
                            //}catch (e: Exception) {
                               // e.printStackTrace()
                           // }
                            active = false
                        },
                        active = active,
                        onActiveChange = {
                            active = it
                        },
                        placeholder = {
                            androidx.compose.material.Text(text = "Pesquisar...")
                        },
                        leadingIcon = {
                            Icon(imageVector = Icons.Default.Search, contentDescription = "Search Icon" )
                        },
                        trailingIcon = {
                            if ( active ) {
                                Icon(
                                    modifier = Modifier.clickable {
                                        if ( queryMovie.isNotEmpty() ) {
                                            queryMovie = ""
                                        } else {
                                            active = false
                                        }
                                    },
                                    imageVector = Icons.Default.Close,
                                    contentDescription = "Close Icon" )
                            }
                        },
                    ) {

                        items.forEach {
                            Row( modifier = Modifier.padding( all = 14.dp ) ) {
                                Icon(
                                    modifier = Modifier.padding( end = 10.dp ),
                                    imageVector = Icons.Default.List,
                                    contentDescription = "History Icon"
                                )
                                androidx.compose.material.Text( text = it )
                            }
                        }

                    }
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(start = 25.dp, end = 25.dp)
                ) {
                    item {
                        Column {
                            Text(text = "Filmes em alta",
                                modifier = Modifier
                                    .padding(top = 15.dp, bottom = 15.dp)
                                    .clickable {
                                        //navController.navigate(Screen.SeriesListScreen.route)
                                    },
                                fontSize = 18.sp,
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.Bold
                            )
                            //Spacer(modifier = Modifier.height(10.dp))
                            MovieListScreenCell(navController , state = state)
                            Text(text = "Filmes em cartaz",
                                modifier = Modifier
                                    .padding(top = 15.dp, bottom = 15.dp),
                                fontSize = 18.sp,
                                style = MaterialTheme.typography.labelLarge,
                                fontWeight = FontWeight.Bold
                            )
                            //Spacer(modifier = Modifier.height(10.dp))
                            MovieListScreenCell(navController = navController , state = state3)
                            Text(text = "Filmes em lançamentos",
                                modifier = Modifier
                                    .padding(top = 15.dp, bottom = 15.dp),
                                fontSize = 18.sp,
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.Bold
                            )
                            //Spacer(modifier = Modifier.height(10.dp))
                            MovieListScreenCell(navController = navController , state = state2)
                            Text(text = "Filmes melhores avaliados",
                                modifier = Modifier
                                    .padding(top = 15.dp, bottom = 15.dp),
                                fontSize = 18.sp,
                                style = MaterialTheme.typography.displayMedium,
                                fontWeight = FontWeight.Bold
                            )
                            MovieListScreenCell(navController = navController , state = state4)
                            Spacer(modifier = Modifier.height(55.dp))
                            }
                        }
                    }
                }
            }
        }
    }
}


