package com.example.moviesaandseries.presentation.episode

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
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
import com.example.moviesaandseries.presentation.cast_grid.SharedCastGridViewModel
import com.example.moviesaandseries.presentation.general.AppBarWithBack
import com.example.moviesaandseries.presentation.general.CastCell
import com.example.moviesaandseries.presentation.general.CrewCell
import com.example.moviesaandseries.presentation.general.CustomPadding
import com.example.moviesaandseries.presentation.general.DpDimensions
import com.example.moviesaandseries.presentation.general.EpisodeDetailShimmer
import com.example.moviesaandseries.presentation.general.SubtitleHeader
import com.example.moviesaandseries.presentation.general.TextBiografia
import com.example.moviesaandseries.ui.theme.DarkGrey11
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun EpisodeScreen(
    navController: NavController,
    isSystemInDarkTheme: Boolean,
    viewModel: EpisodeViewModel = hiltViewModel(),
    sharedViewModel: SharedCastGridViewModel
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

    state.episode?.let { episode ->

    val nome = if (!episode.name.isNullOrEmpty()) episode.name else "sem nome"
    val overview = if (!episode.overview.isNullOrEmpty()) episode.overview else "sem overview"
    var director = ""
    if (!episode.crew.isNullOrEmpty()) {
        for (i in episode.crew) if ( i.job == "Director" ) director = i.name
    } else director = "Ninguém"

    Scaffold(
        topBar = {
            AppBarWithBack(title = nome,
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
                MainContent(overview)
            }
                if ( !episode.guest_stars.isNullOrEmpty() ) {
                    CastCell(navController = navController, cast = episode.guest_stars, text = "Elenco convidado", sharedViewModel = sharedViewModel)

                }
                if (!episode.crew.isNullOrEmpty() ) {
                    CrewCell( director, isDirector = true, episode.crew )
                }
                if (!episode.images.stills.isNullOrEmpty()) {
                    ImagesCell(episode.images.stills)
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
    CustomPadding(verticalPadding = 0.dp, horizontalPadding = DpDimensions.Normal) {
        SubtitleHeader(
            title = "Imagens",
            modifier = Modifier.fillMaxWidth(),
            isSystemInDarkTheme = true,
            isIconVisible = false,
            onClick = {
            }
        )
    }
    Column(modifier = Modifier
        .padding(10.dp)) {
        LazyRow(contentPadding = PaddingValues()) {
            items( images ) { image ->
                EpisodeImageListItem(profile = image)
            }
        }
    }
    Spacer(modifier = Modifier.height(DpDimensions.Small))
}

@Composable
fun MainContent(overview: String){
    Column(
    ) {
        if (overview != "sem overview") {
            TextBiografia(title = overview)
        }
    }
}
