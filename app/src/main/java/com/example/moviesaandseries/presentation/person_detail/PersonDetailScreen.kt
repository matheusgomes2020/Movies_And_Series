package com.example.moviesaandseries.presentation.person_detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.moviesaandseries.R
import com.example.moviesaandseries.common.Constants
import com.example.moviesaandseries.common.navigation.AppGraph
import com.example.moviesaandseries.data.remote.dto.Profile
import com.example.moviesaandseries.presentation.general.ActorDetailShimmer
import com.example.moviesaandseries.presentation.general.RowIcons
import com.example.moviesaandseries.presentation.general.TextBiografia
import com.example.moviesaandseries.presentation.general.TextSubTitulos
import com.example.moviesaandseries.presentation.general.TextTitulos
import com.example.moviesaandseries.presentation.movie_list.MovieListScreenCellWork
import com.example.moviesaandseries.presentation.person_list.MoviesCastListState
import com.example.moviesaandseries.presentation.person_list.SeriesCastListState
import com.example.moviesaandseries.presentation.series_list.SeriesListScreenCellPerson

@Composable
fun CastScreen(
    navController: NavController,
    viewModel: PersonViewModel = hiltViewModel()
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
                    if ( !person.images?.profiles.isNullOrEmpty() ) {
                        Spacer(modifier = Modifier.height( 16.dp ))
                        ImagesActorCell( navController,images = person.images!!.profiles)
                    }
                    if (!person.movie_credits.cast.isNullOrEmpty()) {
                        Spacer(modifier = Modifier.height( 16.dp ))
                        MoviesCell( navController, statemovies )
                    }
                    if (!person.tv_credits.cast.isNullOrEmpty()) {
                        Spacer(modifier = Modifier.height( 16.dp ))
                        SeriesCell( navController, stateSeries )
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
                    .align(Alignment.Center)
            )
        }
        if(state.isLoading) {
            ActorDetailShimmer(isLoading = true, contentAfterLoading = { /*TODO*/ })
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
        TextTitulos(title = nome )
        PersonIconsContent(data = data, localNascimento = lugarDeNascimento )
        if (biografia != "sem biografia") {
            Spacer(modifier = Modifier.height(15.dp))
            TextBiografia(title = biografia)
        }
    }
}

@Composable
fun ImageListItem(
    profile: Profile,
    modifier: Modifier = Modifier,
    onItemClick: (Profile) -> Unit
) {
    Card(
        modifier = modifier
            .clickable { onItemClick(profile) }
    ) {
        Image(
            painter = rememberAsyncImagePainter(
                model = if (!profile.file_path.isNullOrEmpty()) Constants.BASE_IMAGE_URL + profile.file_path else R.drawable.flash
            ),
            contentScale = ContentScale.Crop,
            contentDescription = "profile image",
            modifier = Modifier
                .width(165.dp)
                .height(200.dp)
        )
    }
}

@Composable
fun ImagesCell(){
    Column {
        TextSubTitulos(title = "Imagens")
        Spacer(modifier = Modifier.height(16.dp))
        Text( text = "Imagens")
    }
}

@Composable
fun ImagesActorCell(
    navController: NavController,
    images: List<Profile>){
    TextSubTitulos(title = "Imagens")
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(15.dp))
        LazyRow(contentPadding = PaddingValues()
        ){
            items(images) { image ->
                ImageListItem(
                    profile = image,
                    modifier = Modifier
                        .fillMaxWidth(),
                    onItemClick = {
                        try {
                            val image_path = image.file_path
                            val stringBuilder = StringBuilder(image_path)
                            stringBuilder.deleteCharAt(0)
                            navController.navigate(AppGraph.image_cast_details.DETAILS + "/${stringBuilder}")
                        }catch (e: Exception) {
                            e.printStackTrace()
                            val image_path = image.file_path
                            val stringBuilder = StringBuilder(image_path)
                            stringBuilder.deleteCharAt(0)
                            navController.navigate(AppGraph.image_cast_details.DETAILS + "/${"3dVrtUzLYNszM4QecBhMypUPdU4.jpg"}")

                        }

                    } )
                Spacer(modifier = Modifier.width(10.dp))
            }
        }
    }
}

@Composable
fun SeriesCell(
    navController: NavController,
    state: SeriesCastListState
){
    TextSubTitulos(title = "Séries")
    Column(
        modifier = Modifier.padding(
            horizontal = 10.dp
        )
    ) {
        Spacer(modifier = Modifier.height(12.dp))
        SeriesListScreenCellPerson(navController  , state = state)
    }
}

@Composable
fun MoviesCell(
    navController: NavController,
    state: MoviesCastListState
){
    TextSubTitulos(title = "Filmes")
    Column(
        modifier = Modifier.padding(
            horizontal = 10.dp
        )
    ) {
        Spacer(modifier = Modifier.height(12.dp))
        MovieListScreenCellWork(navController = navController , state = state)
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