package com.example.moviesaandseries.presentation.general

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.moviesaandseries.R
import com.example.moviesaandseries.common.Constants
import com.example.moviesaandseries.common.navigation.AppGraph
import com.example.moviesaandseries.data.remote.dto.movies.Cast
import com.example.moviesaandseries.data.remote.dto.movies.Crew
import com.example.moviesaandseries.data.remote.dto.Genre
import com.example.moviesaandseries.data.remote.dto.Review
import com.example.moviesaandseries.data.remote.dto.season.SeasonDto
import com.example.moviesaandseries.presentation.cast.components.CastListItem
import com.example.moviesaandseries.presentation.movie_list.MovieListScreenCell
import com.example.moviesaandseries.presentation.movie_list.MovieListState
import com.example.moviesaandseries.presentation.review.ReviewListItem
import com.example.moviesaandseries.presentation.season.SeasonListScreenCell
import com.example.moviesaandseries.presentation.season.SeasonListState
import com.example.moviesaandseries.presentation.series_list.SeriesListScreenCell
import com.example.moviesaandseries.presentation.series_list.SeriesListState
import com.example.moviesaandseries.presentation.series_list.TextT
import com.example.moviesaandseries.ui.theme.fontFamily
import com.example.moviesaandseries.ui.theme.fontFamilyLato


@Composable
 fun MainContent(
    isVideo: Boolean,
    nomeOrTitle: String,
    overview: String,
    posterPath: String,
    data: String,
    runtime: String,
    star: Double,
    genres: List<Genre>,
) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = nomeOrTitle,
                style = MaterialTheme.typography.headlineMedium,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = fontFamily,
                maxLines = 1
            )
            Image(
                painterResource(R.drawable.ic_action_name),
                contentDescription = null,
                modifier = Modifier.requiredSize(40.dp)
            )
        }
        //data, time and star
        IconsContent( data, runtime, star, genres )
        Spacer(modifier = Modifier.height(15.dp))
        //image or trailer
        if ( isVideo ) {
            Player( posterPath )
        } else {
            Image(
                painter = rememberAsyncImagePainter(
                    model = if (!posterPath.equals("sem poster")) Constants.BASE_IMAGE_URL + posterPath else R.drawable.logo
                ),
                contentScale = ContentScale.Crop,
                contentDescription = "poster image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
        }
        Spacer(modifier = Modifier.height(15.dp))
        //overview
        if (overview != "sem overview") {
            Text(
                text = overview,
                style = MaterialTheme.typography.headlineMedium,
                lineHeight = 25.sp,
                fontFamily = fontFamilyLato,
                fontSize = 16.sp,
                modifier = Modifier.padding(5.dp)
            )
        }
    }
}

@Composable
 fun CastCell(
    navController: NavController,
    cast: List<Cast>) {
    TextT(title = "Elenco")
    Column(
        modifier = Modifier.padding(
            10.dp
        )
    ) {
        //Spacer(modifier = Modifier.height(10.dp))
        LazyRow(
            contentPadding = PaddingValues()
        ) {
            items(cast) { cast ->
                CastListItem(
                    cast = cast,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                    onItemClick = {
                        try {
                            navController.navigate(AppGraph.cast_details.DETAILS + "/${cast.id}")
                        }catch (e: Exception){
                            Log.d("QQQ", "CastCell: ${e.printStackTrace()}")
                        }
                    }
                )
            }
        }
    }
}

@Composable
 fun CrewCell(
    director: String,
    crew: List<Crew>) {
    Column {
        var roteiro = ""
        for (i in crew) if ( i.department == "Writing" ) {
            roteiro += i.name + "\n"
        }
        if (director != "Ninguém") {
            TextT(title = "Direção")
            Text(text = director,
                fontSize = 17.sp,
                fontFamily = fontFamilyLato,
                modifier = Modifier.padding(top = 5.dp, start = 7.dp))
            Spacer(modifier = Modifier.height( 10.dp ))
        }
        if (!roteiro.isNullOrEmpty()) {
            TextT(title = "Roteiro")
            Text(text = roteiro.trim(),
                fontSize = 17.sp,
                fontFamily = fontFamilyLato,
                modifier = Modifier.padding(top = 5.dp, start = 7.dp),
                maxLines = 5)
        }

    }
}

@Composable
fun SeasonsCell(
    navController: NavController,
    seriesId: String, numeroTemporadas: Int, seasons: List<SeasonDto>, state: SeasonListState) {
    TextT(title =  "$numeroTemporadas - temporadas")
    Column(
        modifier = Modifier.padding(
            horizontal = 10.dp
        )
    ) {
        Spacer(modifier = Modifier.height( 15.dp ))
        SeasonListScreenCell(navController = navController, seriesId = seriesId , state = state )
    }
}

@Composable
 fun SimilarSeriesCell(navController: NavController, state: SeriesListState) {
    Spacer(modifier = Modifier.height(10.dp))
    TextT(title = "Séries Similares")
    Column(modifier = Modifier.padding(10.dp)
    ) {
        SeriesListScreenCell(navController  , state = state)
    }
}

@Composable
fun SimilarsMoviesCell(navController: NavController, state: MovieListState) {
    Spacer(modifier = Modifier.height(10.dp))
    TextT(title = "Filmes Similares")
    Column(modifier = Modifier.padding(10.dp)) {
        MovieListScreenCell(
            navController = navController,
            state = state
        )
    }
}

@Composable
fun ReviewsCell(reviews: List<Review> ){
    TextT(title = "Avaliações")
    Column(
        modifier = Modifier.padding( horizontal = 10.dp )
    ) {
        Spacer(modifier = Modifier.height(15.dp))
        ReviewListScreenCell(reviews = reviews)
    }
}

@Composable
fun ReviewListScreenCell(
    reviews: List<Review>
) {
    Box(
    ) {
        LazyRow( contentPadding = PaddingValues()){
            items(reviews) { review ->
                ReviewListItem(review
                )
            }
        }
    }
}

@Composable
 fun IconsContent(data: String, runtime: String, star: Double, genres: List<Genre>) {
    val average = when ( star ) {
        in 0.0..1.9 ->  "⭐"
        in 2.0..3.9 -> "⭐⭐"
        in 4.0..5.9 ->  "⭐⭐⭐"
        in 6.0..7.9 ->  "⭐⭐⭐⭐"
        in 8.0..10.0 ->  "⭐⭐⭐⭐⭐"
        else -> {}
    }
    Column {
        LazyRow(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            item { RowIcons(text = data, painterResource = R.drawable.ic_calendar  ) }
            item { RowIcons(text = runtime, painterResource = R.drawable.ic_clock  ) }
            item { RowIcons(text = average.toString(), painterResource = R.drawable.ic_star  ) }
        }
        Spacer(modifier = Modifier.height(10.dp))
        LazyRow(
            contentPadding = PaddingValues()
        ){
            items(genres) { index ->
                Text(text = index.name,
                    fontSize = 13.sp,
                    modifier = Modifier.padding(end = 3.dp))
            }
        }
    }
}

@Composable
private fun RowIcons(text: String, painterResource: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painterResource(painterResource),
            contentDescription = null,
            modifier = Modifier.requiredSize(25.dp)
        )
        Spacer(modifier = Modifier.width(7.dp))
        Text(
            text = text,
            fontSize = 13.sp,
            fontFamily = fontFamilyLato,
        )
    }
}