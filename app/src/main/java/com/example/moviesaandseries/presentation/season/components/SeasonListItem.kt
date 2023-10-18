package com.example.moviesaandseries.presentation.season.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.moviesaandseries.common.Constants
import com.example.moviesaandseries.data.remote.dto.season.SeasonDto
import com.example.moviesaandseries.presentation.general.TextCards
import com.example.moviesaandseries.ui.theme.BlueGrey11
import com.example.moviesaandseries.ui.theme.fontFamily


@Composable
fun SeasonListItem(
    season: SeasonDto,
    onItemClick: (SeasonDto) -> Unit
) {

    Card(
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier
            .padding(horizontal = 5.dp)
            .clickable { onItemClick(season) }
            .background(color = if (isSystemInDarkTheme()) BlueGrey11 else Color.White),
    ) {

        Column(
            modifier = Modifier
                    .background(color = if (isSystemInDarkTheme())  BlueGrey11 else Color.White),
        ) {
            Image(painter = rememberAsyncImagePainter(
                model = Constants.BASE_IMAGE_URL + season.poster_path),
                contentScale = ContentScale.Crop,
                contentDescription = "movie image",
                modifier = Modifier
                    .width(110.dp)
                    .height(150.dp)
                    .clip(shape = RoundedCornerShape(15.dp)))
        }
        TextCards(title = "${season.season_number} - temporada")
    }
}