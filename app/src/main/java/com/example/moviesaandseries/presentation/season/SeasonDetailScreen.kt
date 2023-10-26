package com.example.moviesaandseries.presentation.season

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.isSystemInDarkTheme
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
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
import com.example.moviesaandseries.presentation.episode.MainContent
import com.example.moviesaandseries.presentation.episode.components.EpisodeCell
import com.example.moviesaandseries.presentation.episode_list.EpisodeListItem
import com.example.moviesaandseries.presentation.general.AppBarWithBack
import com.example.moviesaandseries.presentation.general.AppBarWithBackAndIcon
import com.example.moviesaandseries.presentation.general.CustomPadding
import com.example.moviesaandseries.presentation.general.DpDimensions
import com.example.moviesaandseries.presentation.general.SeasonDetailShimmer
import com.example.moviesaandseries.presentation.general.SubtitleHeader
import com.example.moviesaandseries.presentation.general.TextBiografia
import com.example.moviesaandseries.presentation.general.TextSubTitulos
import com.example.moviesaandseries.presentation.general.TextTitulos
import com.example.moviesaandseries.ui.theme.BlueGrey11
import com.example.moviesaandseries.ui.theme.DarkGrey11
import com.example.moviesaandseries.ui.theme.MoviesAandSeriesTheme
import com.example.moviesaandseries.ui.theme.fontFamily3
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
 fun SeasonDetailScreen(
    seriesId: String,
    navController: NavController,
    isSystemInDarkTheme: Boolean,
    viewModel: SeasonDetailViewModel = hiltViewModel()

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

    val state = viewModel.state.value

        state.season?.let { season ->

            val overview = if (!season.overview.isNullOrEmpty()) season.overview else "sem overview"
            val posterPath = if (!season.poster_path.isNullOrEmpty()) season.poster_path else "sem poster"

            Scaffold(
                topBar = {
                    AppBarWithBack(title = "${season.season_number} - Temporada",
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
                        MainContentSeason(posterPath = posterPath , overview = overview )
                    }
                   // EpisodeCellNewUI(navController, seriesId = seriesId, episodes = season.episodes)
                    EpisodeCell( navController, seriesId = seriesId, episodes = season.episodes)
                }
            }
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
            SeasonDetailShimmer(isLoading = true, contentAfterLoading = { /*TODO*/ })
        }

}

@Preview()
@Composable
fun aa(){
    MoviesAandSeriesTheme {
        E(episode = EpisodeDto(
            name = "Estou aqui",
            id = 12345,
            overview = "combined the power of the Following feed with the For you feed so there’s one place to discover content on GitHub. There’s improved filtering so you can customize your feed exactly how you like it, and a shiny new visual design. ",
            still_path = "/qT1JPO6IltC2B39QAriAg7SelMx.jpg",
            season_number = 2,
            episode_number = 1
        ), onItemClick = {} )
    }
}
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun E(episode: EpisodeDto,
      onItemClick: (EpisodeDto) -> Unit) {

Surface(
    onClick = { onItemClick(episode) },
    //border = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.outline),
    shape = RoundedCornerShape(DpDimensions.Small),
) {

        Row() {
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
                    horizontal = 6.dp
                )
            ) {
                Text(text = "${episode.episode_number} - ${episode.name}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    fontFamily = fontFamily3,
                    maxLines = 1,
                    // color =  if (isSystemInDarkTheme()) Color.White  else BlueGrey11
                )
                Text(text = episode.overview,
                    modifier = Modifier.padding(
                        top = 7.dp
                    ),
                    fontSize = 14.sp,
                    maxLines = 3
                    , fontFamily = fontFamily3,
                    //color =  if (isSystemInDarkTheme()) Color.White  else BlueGrey11
                )
            }

    }
}




}

@Composable
fun EpisodeCellNewUI(navController: NavController, seriesId: String, episodes: List<EpisodeDto>){
    CustomPadding(verticalPadding = 0.dp, horizontalPadding = DpDimensions.Normal) {
        SubtitleHeader(
            title = "Episódios",
            modifier = Modifier.fillMaxWidth(),
            isSystemInDarkTheme = true,
            onClick = {}
        )
    }
    EpisodeListCell(navController = navController, seriesId, episodes)
}

@Composable
fun EpisodeListCell(navController: NavController, seriesId: String, episodes: List<EpisodeDto>) {
    Box(){
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
        ){
            items(episodes) { episode->
                E(
                    episode = episode,
                    onItemClick = {
                        navController.navigate(AppGraph.episode_details.DETAILS + "/${seriesId}/${episode.season_number}/${episode.episode_number}")
                    }
                )
            }
        }
    }
}


@Composable
fun MainContentSeason( posterPath: String, overview: String ){
    Column(modifier = Modifier
        .fillMaxWidth())
    {
        Image(
            painter = rememberAsyncImagePainter(
                model = if (!posterPath.equals("sem poster")) Constants.BASE_IMAGE_URL + posterPath else R.drawable.logo
            ),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp)
                .clip(shape = RoundedCornerShape(16.dp)),
            contentScale = ContentScale.Crop
        )
        if (overview != "sem overview") {
            Spacer(modifier = Modifier.height( 16.dp ))
            //Text(text = overview, maxLines = 3)
            TextBiografia(title = overview)
        }
    }
}



