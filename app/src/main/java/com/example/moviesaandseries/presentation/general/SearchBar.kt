package com.example.moviesaandseries.presentation.general

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.moviesaandseries.presentation.searchMovies.SearchMoviesViewModel
import com.example.moviesaandseries.presentation.searchSeries.SearchSeriesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SearchBar(
    query: String,
    searchViewModel: SearchSeriesViewModel = hiltViewModel()
    //onQueryChanged: () -> Unit
){


    var text by rememberSaveable { mutableStateOf(query) }
    var active by rememberSaveable { mutableStateOf(false) }

        SearchBar(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .padding(start = 20.dp, end = 20.dp, top = 10.dp),
            query = query,
            onQueryChange = { text = it
                           searchViewModel.searchSeries(text) } ,
            onSearch = { active = false },
            active = false,
            onActiveChange = { active = it },
            placeholder = {
                TextSearchBar(title = "Pesquisar series...")
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

}


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SearchBarMovies(
    query: String,
    tipo: String,
    searchViewModel: SearchMoviesViewModel = hiltViewModel()
    //onQueryChanged: () -> Unit
){


    var text by rememberSaveable { mutableStateOf(query) }
    var active by rememberSaveable { mutableStateOf(false) }

    SearchBar(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(start = 20.dp, end = 20.dp, top = 10.dp),
        query = query,
        onQueryChange = { text = it
            searchViewModel.searchMovies(text) } ,
        onSearch = { active = false },
        active = false,
        onActiveChange = { active = it },
        placeholder = {
            TextSearchBar(title = "Pesquisar filmes...")
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

}

@Composable
fun SearchIcon(){
    Icon(
        imageVector = Icons.Default.Search,
        contentDescription = "Search Icon",
        modifier = Modifier.size(20.dp),
        tint = Color.LightGray
    )
}

@Composable
fun TrailingIcon(text: String, onClick: () -> Unit ){
    var text = text
    Icon(
        modifier = Modifier
            .clickable {
                if (text.isNotEmpty()) text = ""
                onClick()
            }
            .size(20.dp),
        imageVector = Icons.Default.Close,
        contentDescription = "Close Icon",
        tint = Color.LightGray
    )
}