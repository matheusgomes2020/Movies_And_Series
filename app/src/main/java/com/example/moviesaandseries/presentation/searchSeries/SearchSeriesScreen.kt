package com.example.moviesaandseries.presentation.searchSeries

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.moviesaandseries.common.navigation.AppGraph
import com.example.moviesaandseries.presentation.general.AppBarWithBack
import com.example.moviesaandseries.presentation.general.CustomPadding
import com.example.moviesaandseries.presentation.general.DpDimensions
import com.example.moviesaandseries.presentation.general.SearchIcon
import com.example.moviesaandseries.presentation.general.SearchResultsShimmer
import com.example.moviesaandseries.presentation.general.TextSearchBar
import com.example.moviesaandseries.presentation.searchMovies.components.SearchMovieListItem
import com.example.moviesaandseries.presentation.searchSeries.components.SearchSeriesListItem
import com.example.moviesaandseries.ui.theme.DarkGrey11
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SearchSeriesScreen(
    query: String,
    navController: NavController,
    isSystemInDarkTheme: Boolean,
    searchViewModel: SearchSeriesViewModel = hiltViewModel()
) {
    val systemUiController = rememberSystemUiController()
    val useDarkIcons = !isSystemInDarkTheme

    SideEffect {
        systemUiController.setSystemBarsColor(
            color = if (useDarkIcons)
                Color.White else DarkGrey11,
            darkIcons = useDarkIcons
        )
    }

    val state = searchViewModel.state.value
    androidx.compose.material.Scaffold(
        topBar = {
            AppBarWithBack(
                title = "Pesquisar séries",
                backIcon = Icons.Default.ArrowBack,
                onBackClick = {
                    navController.popBackStack()
                })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(
                    color = if (useDarkIcons)
                        Color.White else DarkGrey11
                )
        ) {
            CustomPadding(
                verticalPadding = 0.dp,
                horizontalPadding = DpDimensions.Normal
            ) {
                var text by rememberSaveable { mutableStateOf(query) }
                var active by rememberSaveable { mutableStateOf(false) }
                SearchBar(
                    modifier = Modifier
                        .fillMaxWidth(),
                    // .height(60.dp)
                    //.padding(start = 20.dp, end = 20.dp, top = 10.dp),
                    query = text,
                    onQueryChange = {
                        text = it
                        searchViewModel.searchSeries(text)
                    },
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
                                    searchViewModel.searchSeries(text)
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
                Spacer(modifier = Modifier.height(DpDimensions.Normal))
                state.series?.let { series ->
                    LazyColumn(
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        items(state.series) { series ->
                            SearchSeriesListItem(
                                series = series,
                                onItemClick = {
                                    navController.navigate(AppGraph.series_details.DETAILS + "/${series.id}")
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
                            //contentPadding = PaddingValues(12.dp),
                        ) {
                            items(count = 20) { movies ->
                                SearchResultsShimmer(
                                    isLoading = true,
                                    contentAfterLoading = { /*TODO*/ })
                            }
                        }
                    }
                }
            }
        }
    }
}
