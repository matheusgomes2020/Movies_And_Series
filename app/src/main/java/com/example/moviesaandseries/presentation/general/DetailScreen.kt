package com.example.moviesaandseries.presentation.general

import androidx.compose.foundation.Image
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
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
import com.example.moviesaandseries.presentation.cast_grid.SharedCastGridViewModel
import com.example.moviesaandseries.presentation.review.ReviewListScreenCell
import com.example.moviesaandseries.ui.theme.fontFamily3

@Composable
fun MainContent(isVideo: Boolean, logo: String, overview: String, posterPath: String,
    data: String, runtime: String, star: Double, genres: List<Genre>,
) {
     //data, time and star
    IconsContent( data, runtime, star, genres, logo )
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
                .clip(shape = RoundedCornerShape(16.dp))
        )
    }
    Column(Modifier.fillMaxWidth()
    ) {
        if (logo != "sem logo") {
            Spacer(modifier = Modifier.height(8.dp))
            Image(
                painter = rememberAsyncImagePainter(
                    model =  Constants.BASE_IMAGE_URL + logo
                ),
                contentScale = ContentScale.None,
                contentDescription = "production company",
                modifier = Modifier
                    .width(100.dp)
                    .height(30.dp)
                    .align(Alignment.End)
                    .clip(shape = RoundedCornerShape(8.dp))
            )
        }
    }
    Spacer(modifier = Modifier.height(8.dp))
    //overview
    if (overview != "sem overview") {
        TextBiografia(title = overview )
    }
}

@Composable
fun CastCell(navController: NavController, sharedViewModel: SharedCastGridViewModel, cast: List<Cast>, text: String
            // , type: String
) {
    CustomPadding(verticalPadding = 0.dp, horizontalPadding = DpDimensions.Normal) {
        SubtitleHeader(
            title = text,
            modifier = Modifier.fillMaxWidth(),
            isIconVisible = true,
            isSystemInDarkTheme = true,
            onClick = {
                sharedViewModel.getCast(newCast = cast)
                navController.navigate(AppGraph.cast_grid.GRID)
                //navController.navigate(AppGraph.cast_grid.GRID +"/${cast}")
                //navController.navigate(AppGraph.cast_grid.GRID + "/${id}/${type}")
                //navController.navigate(AppGraph.cast_grid.GRID + "/${id}")
            }
        )
    }
    CastListCell( navController = navController, cast = cast )
    Spacer(modifier = Modifier.height(DpDimensions.Small))
}

@Composable
 fun CrewCell(directorOrCreatedBy: String, isDirector: Boolean, crew: List<Crew>) {
    CustomPadding(verticalPadding = 0.dp, horizontalPadding = DpDimensions.Normal) {
        Column {
            var roteiro = ""
            for (i in crew) if (i.department == "Writing") {
                roteiro += i.name + "\n"
            }
            if (directorOrCreatedBy != "Ninguém") {
                SubtitleHeader(
                    title = if (isDirector) "Direção" else "Criada por",
                    modifier = Modifier.fillMaxWidth(),
                    isSystemInDarkTheme = true,
                    isIconVisible = true,
                    onClick = {
                    }
                )
                Text(
                    text = directorOrCreatedBy.trim(),
                    fontSize = 15.sp,
                    fontFamily = fontFamily3,
                    modifier = Modifier.padding(start = 7.dp)
                )
                Spacer(modifier = Modifier.height(10.dp))
            }
            if (!roteiro.isNullOrEmpty()) {
                Text(
                    text = "Roteiro",
                    style = MaterialTheme.typography.headlineMedium,
                )
                Text(
                    text = roteiro.trim(),
                    fontSize = 15.sp,
                    fontFamily = fontFamily3,
                    modifier = Modifier.padding(top = 5.dp, start = 7.dp),
                    maxLines = 5
                )
            }
        }
    }
}



@Composable
fun ReviewsCell(reviews: List<Review> ){
    CustomPadding(verticalPadding = 0.dp, horizontalPadding = DpDimensions.Normal) {
        SubtitleHeader(
            title = "Avaliações",
            modifier = Modifier.fillMaxWidth(),
            isSystemInDarkTheme = true,
            isIconVisible = true,
            onClick = {}
        )
        ReviewListScreenCell(reviews = reviews)
    }
}

@Composable
 fun IconsContent(data: String, runtime: String, star: Double, genres: List<Genre>, logo: String) {
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
            verticalAlignment = Alignment.CenterVertically
        ){
            if (data != "null") {
                item { RowIcons(text = data, painterResource = R.drawable.ic_calendar  )
                    Spacer(modifier = Modifier.width(8.dp))}
            }
            if (runtime != "null" && runtime!= "0") {
                item { RowIcons(text = runtime, painterResource = R.drawable.ic_clock  )
                    Spacer(modifier = Modifier.width(8.dp))}
            }
            if (star != null) {
                item { RowIcons(text = average.toString(), painterResource = R.drawable.ic_star  ) }
            }
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
fun RowIcons(text: String, painterResource: Int) {
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
            //fontFamily = fontFamily3,
        )
    }
}