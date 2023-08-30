package com.example.moviesaandseries.presentation.movie_detail

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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.moviesaandseries.R
import com.example.moviesaandseries.common.Constants
import com.example.moviesaandseries.data.remote.dto.Cast
import com.example.moviesaandseries.data.remote.dto.Crew
import com.example.moviesaandseries.data.remote.dto.Review
import com.example.moviesaandseries.domain.model.MovieDetail
import com.example.moviesaandseries.presentation.movie_list.MovieListScreenCell
import com.example.moviesaandseries.presentation.cast.CastListItem
import com.example.moviesaandseries.presentation.movie_list.MovieListState
import com.example.moviesaandseries.presentation.review.ReviewListItem


@Composable
fun MovieDetailScreen(
    navController: NavController,
    viewModel: MovieDetailViewModel = hiltViewModel()
){
    val state = viewModel.state.value
    var stateSimilar: MovieListState
    Box(modifier = Modifier.fillMaxSize()) {
        state.movie?.let { movie ->
            stateSimilar = MovieListState(movies = movie.similar.results)
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.White),
                contentPadding = PaddingValues(start = 15.dp, end = 15.dp, top = 15.dp)
            ) {
                item {
                    MainContent2(movie = movie)
                    Spacer(modifier = Modifier.height( 15.dp ))
                    CastCell(cast = movie.credits.cast)
                    Spacer(modifier = Modifier.height( 15.dp ))
                    Crew2(crew = movie.credits.crew)
                    Spacer(modifier = Modifier.height( 15.dp ))
                    Similars2(navController = navController, state = stateSimilar )
                    Spacer(modifier = Modifier.height( 15.dp ))
                    Reviews2(reviews = movie.reviews.results)
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
private fun MainContent2(movie: MovieDetail) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = if (!movie.title.isNullOrEmpty()) movie.title else "sem título",
                style = MaterialTheme.typography.headlineMedium,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Image(
                painterResource(R.drawable.ic_action_name),
                contentDescription = null,
                modifier = Modifier.requiredSize(40.dp)
            )
        }
        //data, time and star
        IconsContent2(movie)
        Spacer(modifier = Modifier.height(15.dp))
        //image
        Image(
            painter = rememberAsyncImagePainter(
                model = if (!movie.poster_path.isNullOrEmpty()) Constants.BASE_IMAGE_URL + movie.poster_path else R.drawable.flash
            ),
            contentScale = ContentScale.Crop,
            contentDescription = "movie image",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )
        Spacer(modifier = Modifier.height(15.dp))
        //overview
        Text(
            text = if (!movie.overview.isNullOrEmpty()) movie.overview else "sem overview",
            style = MaterialTheme.typography.headlineMedium,
            lineHeight = 25.sp,
            fontSize = 16.sp
        )
    }
}

@Composable
private fun CastCell(cast: List<Cast>) {
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
            items(cast) { castMember ->
                CastListItem(
                    cast = castMember,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                )
            }
        }
    }
}

@Composable
private fun Crew2(crew: List<Crew>) {
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
private fun Similars2(navController: NavController, state: MovieListState) {
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
private fun Reviews2(reviews: List<Review>) {

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
private fun IconsContent2(movie: MovieDetail) {
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
                text = if (!movie.release_date.isNullOrEmpty()) movie.release_date else "null",
                fontSize = 14.sp
            )
            Image(
                painterResource(R.drawable.ic_clock),
                contentDescription = null,
                modifier = Modifier.requiredSize(25.dp)
            )
            Text(
                text = if (!movie.runtime.toString().isNullOrEmpty() ) movie.runtime.toString() else "null",
                fontSize = 14.sp
            )
            Image(
                painterResource(R.drawable.ic_star),
                contentDescription = null,
                modifier = Modifier.requiredSize(25.dp)
            )
            val average = when ( movie.vote_average ) {
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
            items(movie.genres) { index ->
                Text(text = index.name,
                    fontSize = 13.sp,
                    modifier = Modifier.padding(end = 3.dp))
            }
        }
    }
}