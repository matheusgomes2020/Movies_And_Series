package com.example.moviesaandseries.presentation.episode

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.moviesaandseries.presentation.cast.ImagesCell
import com.example.moviesaandseries.presentation.series_detail.CastCell
import com.example.moviesaandseries.presentation.series_detail.CrewCell

@Composable
fun EpisodeScreen(
    navController: NavController,
    viewModel: EpisodeViewModel = hiltViewModel()
){

val state = viewModel.state.value
    Box(modifier = Modifier.fillMaxSize()) {
    Column {
        state.episode?.let { episode ->
            Log.d("BATATA", "EpisodeScreen: $episode")
            val nome = if (!episode.name.isNullOrEmpty()) episode.name else "sem nome"
            val overview = if (!episode.overview.isNullOrEmpty()) episode.overview else "sem overview"
            var director = ""
            if (!episode.crew.isNullOrEmpty()) {
                for (i in episode.crew) if ( i.job == "Director" ) director = i.name
            } else director = "Ninguém"

            MainContent(nome, overview)
            Spacer(modifier = Modifier.height(15.dp))
            CastCell(navController = navController, cast = episode.guest_stars)
            Spacer(modifier = Modifier.height(15.dp))
            CrewCell( director, episode.crew )
            ImagesCell()
        }
        if (state.error.isNotBlank()) {
            androidx.compose.material3.Text(
                text = state.error,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                //.align(Alignment.Center)
            )
        }
    }
        if(state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}

@Composable
fun MainContent(nome: String, overview: String){
    Column(
        modifier = Modifier.padding(horizontal = 15.dp)
    ) {
        Text(
            text = nome,
            modifier = Modifier.padding(vertical = 10.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp)

        Text(
            text = overview,
            modifier = Modifier.padding(vertical = 10.dp)
        )
    }
}

//Elenco
//direcao
//roteiro
// imagens