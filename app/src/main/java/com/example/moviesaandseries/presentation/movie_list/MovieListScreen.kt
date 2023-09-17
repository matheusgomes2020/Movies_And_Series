package com.example.moviesaandseries.presentation.movie_list

import android.annotation.SuppressLint
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
                        modifier = Modifier.fillMaxWidth()
                            .height(60.dp)
                            .padding(start = 20.dp, end = 20.dp, top = 10.dp),
                        query = queryMovie,
                        onQueryChange = {
                            queryMovie = it
                        },
                        onSearch = {
                                navController.navigate( AppGraph.search_movies.DETAILS + "/${queryMovie}" )
                            active = false
                        },
                        active = false,
                        onActiveChange = {
                            active = it
                        },
                        placeholder = {
                            Text(text = "Pesquisar...",
                                modifier = Modifier,
                                color = Color.LightGray,
                                fontSize = 13.sp,
                                textAlign = TextAlign.Center
                            )
                        },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "Search Icon",
                                modifier = Modifier.size(20.dp),
                                tint = Color.LightGray
                                    )
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
                                    }
                                        .size(20.dp),
                                    imageVector = Icons.Default.Close,
                                    contentDescription = "Close Icon",
                                    tint = Color.LightGray
                                    )
                            }
                        },
                        shape = RoundedCornerShape(15.dp),
                    ) {

                        Text(text = "Olá")

//                        items.forEach {
//                            Row( modifier = Modifier.padding( all = 14.dp ) ) {
//                                Icon(
//                                    modifier = Modifier.padding( end = 10.dp ),
//                                    imageVector = Icons.Default.List,
//                                    contentDescription = "History Icon"
//                                )
//                                androidx.compose.material.Text( text = it )
//                            }
//                        }

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


