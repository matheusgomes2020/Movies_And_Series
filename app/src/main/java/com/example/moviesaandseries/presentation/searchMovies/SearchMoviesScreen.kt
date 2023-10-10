package com.example.moviesaandseries.presentation.searchMovies

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.moviesaandseries.common.navigation.AppGraph
import com.example.moviesaandseries.presentation.general.SearchIcon
import com.example.moviesaandseries.presentation.general.SearchResultsShimmer
import com.example.moviesaandseries.presentation.general.TextSearchBar
import com.example.moviesaandseries.presentation.searchMovies.components.SearchMovieListItem

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchMoviesScreen(
    query: String,
    navController: NavController,
    searchViewModel: SearchMoviesViewModel = hiltViewModel()
) {
    val state = searchViewModel.state.value
    Scaffold {
        Column {
            var text by rememberSaveable { mutableStateOf(query) }
            var active by rememberSaveable { mutableStateOf(false) }
            SearchBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .padding(start = 20.dp, end = 20.dp, top = 10.dp),
                query = text,
                onQueryChange = {
                    text = it
                    searchViewModel.searchMovies(text) },
                onSearch = { active = false },
                active = false,
                onActiveChange = { active = it },
                placeholder = { TextSearchBar(title = "Pesquisar filmes...") },
                leadingIcon = { SearchIcon() },
                trailingIcon = {
                    Icon(
                        modifier = Modifier
                            .clickable {
                                if (text.isNotEmpty()) text = ""
                                searchViewModel.searchMovies(text)
                            }
                            .size(20.dp),
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close Icon",
                        tint = Color.LightGray
                    )
                },
                shape = RoundedCornerShape(15.dp)
            ) {
            }
            Spacer(modifier = Modifier.height(8.dp))
            state.movies?.let { movies ->
                LazyColumn(
                    contentPadding = PaddingValues(12.dp),
                ) {
                    items(state.movies) { movie ->
                        SearchMovieListItem(
                            movie = movie,
                            onItemClick = {
                                navController.navigate(AppGraph.movies_details.DETAILS + "/${movie.id}")
                            }
                        )
                    }
                }
            }
            if (state.error.isNotBlank()) {
                Text(
                    text = state.error,
                    color = MaterialTheme.colorScheme.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                )
            }
            if (state.isLoading) {
                Column {
                    LazyColumn(
                        contentPadding = PaddingValues(12.dp),
                    ) {
                        items(count = 20) { movies ->
                            SearchResultsShimmer(isLoading = true, contentAfterLoading = { /*TODO*/ })
                        }
                    }
                }
            }
        }
    }
}
