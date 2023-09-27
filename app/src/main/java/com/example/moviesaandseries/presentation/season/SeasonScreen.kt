package com.example.moviesaandseries.presentation.season

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.moviesaandseries.R
import com.example.moviesaandseries.common.Constants
import com.example.moviesaandseries.common.navigation.AppGraph
import com.example.moviesaandseries.data.remote.dto.episode.EpisodeDto

@Composable
 fun SeasonDetailScreen(
    seriesId: String,
    navController: NavController,
    viewModel: SeasonDetailViewModel = hiltViewModel()

) {
    val state = viewModel.state.value

    Column {
        state.season?.let { season ->
            val overview = if (!season.overview.isNullOrEmpty()) season.overview else "sm overview"
            val posterPath = if (!season.poster_path.isNullOrEmpty()) season.poster_path else "sem poster"

            MainContent(numeroTemporada = season.season_number, posterPath = posterPath , overview = overview )
            EpisodeCell( navController, seriesId = seriesId, episodes = season.episodes)

        }
        if ( state.error.isNotBlank() ) {
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
        if(state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(CenterHorizontally))
        }
    }
}





@Composable
fun MainContent(numeroTemporada: Int, posterPath: String, overview: String){
    Column(
        modifier = Modifier.padding(horizontal = 15.dp)
    ) {
        Text(
            text = "$numeroTemporada - Temporada",
            modifier = Modifier.padding(vertical = 10.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp)

        Image(
            painter = rememberAsyncImagePainter(
                model = if (!posterPath.equals("sem poster")) Constants.BASE_IMAGE_URL + posterPath else R.drawable.flash
            ),
            contentDescription = null,
            modifier = Modifier
                .height(300.dp)
                .fillMaxWidth(),
            contentScale = ContentScale.Crop
        )
        Text(
            text = overview,
            modifier = Modifier.padding(vertical = 10.dp)
        )
    }
}
@Composable
fun EpisodeCell(
    navController: NavController,
    seriesId: String,
    episodes: List<EpisodeDto>){
    Column(
        modifier = Modifier.padding(horizontal = 15.dp)
    ) {
        Text(text = "Episódios",
            modifier = Modifier.padding(
                top = 5.dp
            ),
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp)
        LazyColumn(contentPadding = PaddingValues(10.dp)){
            items(episodes) { episode->
                EpisodeListItem(
                    episode = episode,
                    onItemClick = {
                        navController.navigate(AppGraph.episode_details.DETAILS + "/${seriesId}/${episode.season_number}/${episode.episode_number}")
                        Log.d("BATATA", "/${seriesId}/${episode.season_number}/${episode.episode_number}")
                    }
                )
            }
        }
    }
}

@Composable
fun EpisodeListItem(
    episode: EpisodeDto,
    onItemClick: (EpisodeDto) -> Unit
){
    Row(
        modifier = Modifier
            .padding(
                vertical = 5.dp
            )
            .clickable { onItemClick(episode) }
    ) {
        Image(painter = rememberAsyncImagePainter(
            model = Constants.BASE_IMAGE_URL + episode.still_path),
            contentDescription = null,
            modifier = Modifier
                .requiredSize(110.dp)
                .clip(shape = RoundedCornerShape(15.dp)),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier.padding(
                vertical = 10.dp,
                horizontal = 5.dp
            )
        ) {
            Text(text = "${episode.episode_number} - ${episode.name}",
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp)
            Text(text = episode.overview,
                modifier = Modifier.padding(
                    top = 7.dp
                ),
                fontSize = 14.sp,
                maxLines = 3)
        }
    }
}

@Preview
@Composable
fun SeasonScreen(){
    Column {
        mainContent()
        EpisodesCell()
    }
}

@Preview
@Composable
private fun mainContent() {
    Column(
        modifier = Modifier.padding(horizontal = 15.dp)
    ) {
        Text(
            text = "2 - Temporada",
            modifier = Modifier.padding(vertical = 10.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp)

        Image(
            painterResource(R.drawable.flash),
            contentDescription = null,
            modifier = Modifier
                .height(300.dp)
                .fillMaxWidth(),
            contentScale = ContentScale.Crop
        )
        Text(
            text = "Agente Phil Coulson da S.H.I.E.L.D. (Superintendência Humana de Intervenção, Espionagem, Logística e Dissuasão) reúne uma equipe de agentes para investigar o novo, o estranho e o desconhecido em todo o mundo, protegendo o comum do extraordinário.",
            modifier = Modifier.padding(vertical = 10.dp)
        )
    }
}

@Preview
@Composable
fun EpisodesCell(){
    Column(
        modifier = Modifier.padding(horizontal = 15.dp)
    ) {
        Text(text = "Episódios",
            modifier = Modifier.padding(
                top = 5.dp
            ),
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp)
        LazyColumn(contentPadding = PaddingValues(10.dp)){
            items(count = 15) { index->
                EpisodesListItem()
            }
        }
    }
}

@Preview
@Composable
fun EpisodesListItem(){
    Row(
        modifier = Modifier.padding(
            vertical = 5.dp
        )
    ) {
        Image(
            painterResource(R.drawable.flash),
            contentDescription = null,
            modifier = Modifier
                .requiredSize(110.dp)
                .clip(shape = RoundedCornerShape(15.dp)),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier.padding(
                vertical = 10.dp,
                horizontal = 5.dp
            )
        ) {
            Text(text = "1 - O Novo Acordo",
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp)
            Text(text = "Coulson e os Agentes da S.H.I.E.L.D. voltam no tempo e ficam presos na Nova Iorque de 1931. Com a nova Zephyr prestes a saltar no tempo a qualquer momento, a equipe precisa correr para descobrir exatamente o que aconteceu.",
                modifier = Modifier.padding(
                    top = 7.dp
                ),
                fontSize = 14.sp,
                maxLines = 3)
        }
    }
}
