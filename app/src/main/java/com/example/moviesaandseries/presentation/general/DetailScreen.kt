package com.example.moviesaandseries.presentation.series_detail

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.isUnspecified
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
import com.example.moviesaandseries.presentation.review.ReviewListItem3
import com.example.moviesaandseries.presentation.season.SeasonListScreenCell
import com.example.moviesaandseries.presentation.season.SeasonListState
import com.example.moviesaandseries.presentation.season.components.SeasonListItem
import com.example.moviesaandseries.presentation.series_list.SeriesListScreenCell
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
 fun CrewCell(
    director: String,
    crew: List<Crew>) {
    Column {
        var roteiro = ""
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
 fun SimilarSeriesCell(navController: NavController, state: SeriesListState) {
    Column {
        Text(
            text = "Séries Similares",
            style = MaterialTheme.typography.headlineMedium,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(15.dp))
        SeriesListScreenCell(navController  , state = state)
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
fun ReviewsCell2( reviews: List<Review> ){
    Column {
        Text(
            text = "Avaliações",
            style = MaterialTheme.typography.headlineMedium,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(15.dp))
        ReviewListScreenCell(reviews = reviews)
    }
}

@Composable
fun ReviewListScreenCell(
    reviews: List<Review>
    //state: SeasonListState
) {
    Box(
        //  modifier = Modifier.fillMaxSize()
    ) {

        LazyRow( contentPadding = PaddingValues()){
            items(reviews) { review ->
                ReviewListItem5(review,
                    // modifier = Modifier
                )
            }
        }
//        if ( state.error.isNotBlank() ) {
//            Text(
//                text = state.error,
//                color = MaterialTheme.colorScheme.error,
//                textAlign = TextAlign.Center,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(horizontal = 20.dp)
//                    .align(Alignment.Center)
//            )
//        }
//        if(state.isLoading) {
//            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
//        }
    }
}

@Composable
fun ReviewListItem5(review: Review) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {

//        var totalPageTextWidth by remember { mutableStateOf<Int?>(null) }
//        val widthModifier = totalPageTextWidth?.let { width ->
//            with(LocalDensity.current) {
//                Modifier.width(width.toDp())
//            }
//        } ?: Modifier
//        Text(
//            text = "1000",
//            onTextLayout = { totalPageTextWidth = it.size.width }
//        )
//        Text(
//            text = "1",
//            modifier = widthModifier
//        )


        Text(text = review.author)
        Text(text = review.created_at)
        Text(text = review.content, modifier = Modifier.width(390.dp),
         maxLines = 3)
//      AutoResizedText(
//            text = review.content,
//            style = MaterialTheme.typography.headlineMedium,
//        )
        //Text(text = review.content,onTextLayout = { totalPageTextWidth = it.size.width })
        //Text(text = review.content,modifier = widthModifier)
    }

}

@Composable
fun AutoResizedText(
    text: String,
    style: TextStyle = MaterialTheme.typography.headlineMedium,
    modifier: Modifier = Modifier,
    color: Color = style.color
) {
    var resizedTextStyle by remember {
        mutableStateOf(style)
    }
    var shouldDraw by remember {
        mutableStateOf(false)
    }

    val defaultFontSize = MaterialTheme.typography.headlineMedium.fontSize

    Text(
        text = text,
        color = color,
        modifier = modifier.drawWithContent {
            if (shouldDraw) {
                drawContent()
            }
        },
        softWrap = false,
        style = resizedTextStyle,
        onTextLayout = { result ->
            if (result.didOverflowWidth) {
                if (style.fontSize.isUnspecified) {
                    resizedTextStyle = resizedTextStyle.copy(
                        fontSize = defaultFontSize
                    )
                }
                resizedTextStyle = resizedTextStyle.copy(
                    fontSize = resizedTextStyle.fontSize * 0.95
                )
            } else {
                shouldDraw = true
            }
        }
    )
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