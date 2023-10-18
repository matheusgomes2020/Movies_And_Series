package com.example.moviesaandseries.presentation.episode

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.moviesaandseries.R
import com.example.moviesaandseries.common.Constants
import com.example.moviesaandseries.data.remote.dto.Profile
import com.example.moviesaandseries.presentation.general.CastCell
import com.example.moviesaandseries.presentation.general.CrewCell
import com.example.moviesaandseries.presentation.general.EpisodeDetailShimmer
import com.example.moviesaandseries.presentation.general.TextBiografia
import com.example.moviesaandseries.presentation.general.TextSubTitulos
import com.example.moviesaandseries.presentation.general.TextTitulos
import com.example.moviesaandseries.ui.theme.BlueGrey11

@Composable
fun EpisodeScreen(
    navController: NavController,
    viewModel: EpisodeViewModel = hiltViewModel()
){

val state = viewModel.state.value
    Box(modifier = Modifier.fillMaxSize()) {
    Column(
        modifier = Modifier
           // .padding( 16.dp)
            .fillMaxWidth()
            .background(color = if (isSystemInDarkTheme())  BlueGrey11 else Color.White),
    ) {
        state.episode?.let { episode ->
            val nome = if (!episode.name.isNullOrEmpty()) episode.name else "sem nome"
            val overview = if (!episode.overview.isNullOrEmpty()) episode.overview else "sem overview"
            var director = ""
            if (!episode.crew.isNullOrEmpty()) {
                for (i in episode.crew) if ( i.job == "Director" ) director = i.name
            } else director = "Ninguém"
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                        .background(color = if (isSystemInDarkTheme())  BlueGrey11 else Color.White),
                contentPadding = PaddingValues(start = 15.dp, end = 15.dp, top = 15.dp)
            ) {
                item {
                    MainContent(nome, overview)
                    if ( !episode.guest_stars.isNullOrEmpty() ) {
                        Spacer(modifier = Modifier.height(16.dp))
                        CastCell(navController = navController, cast = episode.guest_stars, "Elenco convidado")
                    }
                    if (!episode.crew.isNullOrEmpty() ) {
                        Spacer(modifier = Modifier.height(16.dp))
                        CrewCell( director, episode.crew )
                    }
                    if (!episode.images.stills.isNullOrEmpty()) {
                        Spacer(modifier = Modifier.height(16.dp))
                        ImagesCell(episode.images.stills)
                    }
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
    }
        if(state.isLoading) {
            EpisodeDetailShimmer(isLoading = true, contentAfterLoading = { /*TODO*/ })
        }
    }
}

@Composable
fun EpisodeImageListItem(
    profile: Profile,
    //onItemClick: (Profile) -> Unit
) {
        Image(
            painter = rememberAsyncImagePainter(
                model = if (!profile.file_path.isNullOrEmpty()) Constants.BASE_IMAGE_URL + profile.file_path else R.drawable.logo
            ),
            contentScale = ContentScale.Crop,
            contentDescription = "episode image",
            modifier = Modifier
                .padding(end = 5.dp)
                .width(358.dp)
                .height(200.dp)
                .clip(shape = RoundedCornerShape(16.dp))
        )
}


@Composable
fun ImagesCell(
    images: List<Profile>
){
    TextSubTitulos(title = "Imagens")
    Column(modifier = Modifier
        .padding(10.dp)) {
        LazyRow(contentPadding = PaddingValues()) {
            items( images ) { image ->
                EpisodeImageListItem(profile = image)
            }
        }
    }
}

@Composable
fun MainContent(nome: String, overview: String){
    Column(
    ) {
        TextTitulos(title = nome)
        if (overview != "sem overview") {
            Spacer(modifier = Modifier.height(16.dp))
            TextBiografia(title = overview)
        }
    }
}
