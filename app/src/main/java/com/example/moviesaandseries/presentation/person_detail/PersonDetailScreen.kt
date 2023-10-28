package com.example.moviesaandseries.presentation.person_detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.moviesaandseries.R
import com.example.moviesaandseries.presentation.general.ActorDetailShimmer
import com.example.moviesaandseries.presentation.general.AppBarWithBack
import com.example.moviesaandseries.presentation.general.CustomPadding
import com.example.moviesaandseries.presentation.general.DpDimensions
import com.example.moviesaandseries.presentation.general.RowIcons
import com.example.moviesaandseries.presentation.general.TextBiografia
import com.example.moviesaandseries.presentation.movie_list.MovieListState
import com.example.moviesaandseries.presentation.movie_list.components.MovieListCell
import com.example.moviesaandseries.presentation.person_detail.components.PersonImagesCell
import com.example.moviesaandseries.presentation.series_list.SeriesListState
import com.example.moviesaandseries.presentation.series_list.components.SeriesListCell
import com.example.moviesaandseries.ui.theme.DarkGrey11
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun CastScreen(
    navController: NavController,
    isSystemInDarkTheme: Boolean,
    viewModel: PersonViewModel = hiltViewModel()
){

    val systemUiController = rememberSystemUiController()
    val useDarkIcons = !isSystemInDarkTheme

    SideEffect {
        systemUiController.setSystemBarsColor(
            color = if (useDarkIcons)
                Color.White else DarkGrey11,
            darkIcons = useDarkIcons
        )
    }

    val state = viewModel.state.value
    var stateSeries: SeriesListState
    var statemovies: MovieListState

        state.person?.let { person ->

            stateSeries = SeriesListState(series = person.tv_credits!!.cast)
            statemovies = MovieListState(movies = person.movie_credits!!.cast)

            val name = if (!person.name.isNullOrEmpty()) person.name else "sem nome"
            val biography = if (!person.biography.isNullOrEmpty()) person.biography else "sem biografia"
            val data = if (!person.birthday.isNullOrEmpty()) person.birthday else "sem data"
            val lugarNascimento = if (!person.place_of_birth.isNullOrEmpty()) person.place_of_birth else "Terra"

            Scaffold(
                topBar = {
                    AppBarWithBack(title = name,
                        backIcon = Icons.Default.ArrowBack,
                        onBackClick = {
                            navController.popBackStack()
                        }
                    )
                }
            ) { paddingValues ->
                Column(
                    modifier = Modifier
                        .padding(paddingValues)
                        .verticalScroll(rememberScrollState())
                        //.horizontalScroll(rememberScrollState())
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
                        MainContent( name, biography, data, lugarNascimento )
                    }
                        if ( !person.images?.profiles.isNullOrEmpty() ) {
                            PersonImagesCell( navController,images = person.images!!.profiles)
                        }
                        if (!person.movie_credits.cast.isNullOrEmpty()) {
                            MovieListCell(navController, statemovies, "Filmes", {} )
                        }
                        if (!person.tv_credits.cast.isNullOrEmpty()) {
                            SeriesListCell(navController, stateSeries, "Séries", {}  )
                        }
                }
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
        )
    }
    if(state.isLoading) {
        ActorDetailShimmer()
    }
}

@Composable
fun MainContent(
    nome: String,
    biography: String,
    data: String,
    lugarDeNascimento: String,
){
    Column {
        PersonIconsContent(data = data, localNascimento = lugarDeNascimento )
        if (biography != "sem biografia") {
            Spacer(modifier = Modifier.height(15.dp))
            TextBiografia(title = biography)
        }
    }
}


@Composable
fun PersonIconsContent(data: String, localNascimento: String) {
    Column {
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (data != "sem data") {
                item {
                        RowIcons(text = data, painterResource = R.drawable.ic_calendar)
                        Spacer(modifier = Modifier.width(8.dp))
                    }
            }
            if (localNascimento != "Terra") {
                item {
                    RowIcons(text = localNascimento, painterResource = R.drawable.ic_earth)
                }
            }
        }

    }
}