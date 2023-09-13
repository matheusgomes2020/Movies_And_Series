package com.example.moviesaandseries.presentation.series_detail

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
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
import com.example.moviesaandseries.common.navigation2.AppGraph
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
import com.example.moviesaandseries.presentation.series_list.SeriesListState



@Composable
 fun MainContent(
    nomeOrTitle: String,
    overview: String,
    posterPath: String,
    data: String,
    runtime: String,
    star: Double,
    genres: List<Genre>
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
        //image
        Image(
            painter = rememberAsyncImagePainter(
                model = if (!posterPath.equals("sem poster")) Constants.BASE_IMAGE_URL + posterPath else R.drawable.flash
            ),
            contentScale = ContentScale.Crop,
            contentDescription = "poster image",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )
        Spacer(modifier = Modifier.height(15.dp))
        //overview
        Text(
            text = overview,
            style = MaterialTheme.typography.headlineMedium,
            lineHeight = 25.sp,
            fontSize = 16.sp
        )
    }
}

@Composable
 fun CastCell(
    navController: NavController,
    cast: List<Cast>) {
    Column {
        Text(
            text = "Elenco",
            style = MaterialTheme.typography.headlineMedium,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(15.dp))
        LazyRow(
            contentPadding = PaddingValues()
        ) {
            items(cast) { cast ->
                CastListItem(
                    cast = cast,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
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
 fun CrewCell(crew: List<Crew>) {
    Column {
        var director = ""
        var roteiro = ""
        for (i in crew) if ( i.job == "Director" ) director = i.name
        for (i in crew) if ( i.department == "Writing" ) roteiro += i.name + "\n"
        Text(
            text = "Direção",
            style = MaterialTheme.typography.headlineMedium,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Text(text = director,
            fontSize = 17.sp,
            modifier = Modifier.padding(top = 5.dp, start = 7.dp))

        Spacer(modifier = Modifier.height( 15.dp ))
        Text(
            text = "Roteiro",
            style = MaterialTheme.typography.headlineMedium,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Text(text = roteiro,
            fontSize = 17.sp,
            modifier = Modifier.padding(top = 5.dp, start = 7.dp),
            maxLines = 5)
    }
}

@Composable
fun SeasonsCell(
    navController: NavController,
    seriesId: String, numeroTemporadas: Int, seasons: List<SeasonDto>, state: SeasonListState) {
    Column {

        Text(
            text = "$numeroTemporadas - temporadas",
            style = MaterialTheme.typography.headlineMedium,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height( 15.dp ))
        SeasonListScreenCell(navController = navController, seriesId = seriesId , state = state )

    }
}

@Composable
 fun SimilarsSeriesCell(navController: NavController, state: SeriesListState) {
    Column {
        Text(
            text = "Séries Similares",
            style = MaterialTheme.typography.headlineMedium,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(15.dp))
        //SeriesListScreenCell(navController  , state = state)
    }
}

@Composable
fun SimilarsMoviesCell(navController: NavController, state: MovieListState) {
    Column {
        Text(
            text = "Filmes Similares",
            style = MaterialTheme.typography.headlineMedium,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(15.dp))
        MovieListScreenCell(navController = navController , state = state)
    }
}

@Composable
 fun ReviewsCell(reviews: List<Review>) {

    Column {
        Text(
            text = "Avaliações",
            style = MaterialTheme.typography.headlineMedium,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(15.dp))
        LazyRow(contentPadding = PaddingValues() ){
            items(reviews) { review ->
                ReviewListItem(review, modifier = Modifier)
            }
        }
    }
}

@Composable
 fun IconsContent(data: String, runtime: String, star: Double, genres: List<Genre>) {
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
                text = runtime,
                fontSize = 14.sp
            )
            Image(
                painterResource(R.drawable.ic_star),
                contentDescription = null,
                modifier = Modifier.requiredSize(25.dp)
            )
            val average = when ( star ) {
                in 0.0..1.9 ->  "⭐"
                in 2.0..3.9 -> "⭐⭐"
                in 4.0..5.9 ->  "⭐⭐⭐"
                in 6.0..7.9 ->  "⭐⭐⭐⭐"
                in 8.0..10.0 ->  "⭐⭐⭐⭐⭐"
                else -> {}
            }
            Text(
                text = average.toString(),
                fontSize = 14.sp
            )
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