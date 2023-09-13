package com.example.moviesaandseries.presentation.cast

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.moviesaandseries.R
import com.example.moviesaandseries.data.remote.dto.Genre
import com.example.moviesaandseries.presentation.movie_list.MovieListScreenCell
import com.example.moviesaandseries.presentation.movie_list.MovieListScreenCellWork
import com.example.moviesaandseries.presentation.movie_list.MovieListState
import com.example.moviesaandseries.presentation.season.SeasonListState
import com.example.moviesaandseries.presentation.series_list.SeriesListScreenCell
import com.example.moviesaandseries.presentation.series_list.SeriesListScreenCellPerson
import com.example.moviesaandseries.presentation.series_list.SeriesListState

@Composable
fun CastScreen(
    navController: NavController,
    viewModel: CastViewModel = hiltViewModel()
){
    val state = viewModel.state.value
    var stateSeries: SeriesCastListState
    var statemovies: MoviesCastListState
    Box(modifier = Modifier.fillMaxSize()) {
        state.person?.let { person ->
            stateSeries = SeriesCastListState(series = person.tv_credits!!.cast)
            statemovies = MoviesCastListState(movies = person.movie_credits!!.cast)
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.White),
                contentPadding = PaddingValues(start = 15.dp, end = 15.dp, top = 15.dp)
            ) {

                val name = if (!person.name.isNullOrEmpty()) person.name else "sem nome"
                val biografia = if (!person.biography.isNullOrEmpty()) person.biography else "sem biografia"
                val data = if (!person.birthday.isNullOrEmpty()) person.birthday else "sem data"
                val lugarNascimento = if (!person.place_of_birth.isNullOrEmpty()) person.place_of_birth else "Terra"

                item{
                    MainContent( name, biografia, data, lugarNascimento )
                    Spacer(modifier = Modifier.height( 15.dp ))
                    ImagesCell()
                    Spacer(modifier = Modifier.height( 15.dp ))
                    MoviesCell( navController, statemovies )
                    Spacer(modifier = Modifier.height( 15.dp ))
                    SeriesCell( navController, stateSeries )
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
                    .align(Alignment.Center)
            )
        }
        if(state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}

@Composable
fun MainContent(
    nome: String,
    biografia: String,
    data: String,
    lugarDeNascimento: String,
){
    Column {
        Text(
            text = nome,
            style = MaterialTheme.typography.headlineMedium,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 1
        )
        PersonIconsContent(data = data, localNascimento = lugarDeNascimento )
        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = biografia,
            style = MaterialTheme.typography.headlineMedium,
            lineHeight = 25.sp,
            fontSize = 16.sp
        )
    }
}

@Composable
fun ImagesCell(){
    Column {
        Text(
            text = "Imagens",
            style = MaterialTheme.typography.headlineMedium,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(15.dp))
        Text(text = "Imagens do ator")
    }
}

@Composable
fun SeriesCell(
    navController: NavController,
    state: SeriesCastListState
){
    Column {
        Text(
            text = "Séries",
            style = MaterialTheme.typography.headlineMedium,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(15.dp))
        SeriesListScreenCellPerson(navController  , state = state)
    }
}

@Composable
fun MoviesCell(
    navController: NavController,
    state: MoviesCastListState
){
    Column {
        Text(
            text = "Filmes",
            style = MaterialTheme.typography.headlineMedium,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(15.dp))
        MovieListScreenCellWork(navController = navController , state = state)
    }
}

@Composable
fun PersonIconsContent(data: String, localNascimento: String) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painterResource(R.drawable.ic_calendar),
                contentDescription = null,
                modifier = Modifier.requiredSize(25.dp)
            )
            Text(
                text = data,
                fontSize = 14.sp
            )
            Image(
                painterResource(R.drawable.ic_clock),
                contentDescription = null,
                modifier = Modifier.requiredSize(25.dp)
            )
            Text(
                text = localNascimento,
                fontSize = 14.sp
            )
        }
    }
}