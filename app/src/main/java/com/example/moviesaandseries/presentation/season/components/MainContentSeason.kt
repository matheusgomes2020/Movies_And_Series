package com.example.moviesaandseries.presentation.season.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.moviesaandseries.R
import com.example.moviesaandseries.common.Constants
import com.example.moviesaandseries.presentation.general.TextBiografia

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
                .height(350.dp)
                .clip(shape = RoundedCornerShape(16.dp)),
            contentScale = ContentScale.FillBounds
        )
        if (overview != "sem overview") {
            Spacer(modifier = Modifier.height( 16.dp ))
            //Text(text = overview, maxLines = 3)
            TextBiografia(title = overview)
        }
    }
}