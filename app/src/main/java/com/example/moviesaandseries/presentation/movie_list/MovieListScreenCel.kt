package com.example.moviesaandseries.presentation.movie_list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.moviesaandseries.common.navigation.AppGraph
import com.example.moviesaandseries.presentation.cast.MoviesCastListState
import com.example.moviesaandseries.presentation.general.ShimmerListItem
import com.example.moviesaandseries.presentation.movie_list.components.MovieListItem
import com.example.moviesaandseries.presentation.movie_list.components.MovieListItemWork
import kotlinx.coroutines.delay

@Composable
fun MovieListScreenCell(
    navController: NavController,
    state: MovieListState,

){

//    var isLoading by remember {
//        mutableStateOf(state.isLoading)
//    }
//LaunchedEffect(key1 = true ) {
//    delay(1000)
//    isLoading = false
//}
//    LazyRow(
//        modifier = Modifier
//            .fillMaxSize()
//    ) {
//        items( state.movies ) { movie ->
//            ShimmerListItem(
//                isLoading =  isLoading,
//                contentAfterLoading = {
//                    MovieListItem(
//                    movie = movie,
//                    onItemClick = {
//                        navController.navigate(AppGraph.movies_details.DETAILS + "/${movie.id}")
//                    }
//                )
//                },
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(16.dp)
//            )
//        }
//    }

    Box(
        //  modifier = Modifier.fillMaxSize()
    ) {

        LazyRow {
            items(state.movies) { movie ->
                MovieListItem(
                    movie = movie,
                    onItemClick = {
                        navController.navigate(AppGraph.movies_details.DETAILS + "/${movie.id}")
                    }
                )
            }
        }
        if ( state.error.isNotBlank() ) {
            Text(
                text = state.error,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }
        if(state.isLoading) {
            LazyRow {
                items(20) {
                   ShimmerListItem(isLoading = true,
                       contentAfterLoading = { /*TODO*/ })
                }
            }
        }
    }
}

@Composable
fun MovieListScreenCellWork(
    navController: NavController,
    state: MoviesCastListState
){
    Box(
        //  modifier = Modifier.fillMaxSize()
    ) {

        LazyRow(
            //modifier = Modifier.fillMaxSize()
        ) {
            items(state.movies) { movie ->
                MovieListItemWork(
                    movie = movie,
                    onItemClick = {
                        try {
                            navController.navigate(AppGraph.movies_details.DETAILS + "/${movie.id}")
                        }catch (e: Exception){
                            e.printStackTrace()
                        }
                        // navController.navigate(DetailsScreen.MoviesDetails.route + "/${movie.id}")

                        //navController.navigate(Graph.DETAILS)
                    }
                )
            }
        }
        if ( state.error.isNotBlank() ) {
            Text(
                text = state.error,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }
        if(state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}