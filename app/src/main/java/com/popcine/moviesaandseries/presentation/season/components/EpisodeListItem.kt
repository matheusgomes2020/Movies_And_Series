package com.popcine.moviesaandseries.presentation.season.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.popcine.moviesaandseries.R
import com.popcine.moviesaandseries.common.Constants.BASE_IMAGE_URL
import com.popcine.moviesaandseries.data.remote.dto.episode.EpisodeDto
import com.popcine.moviesaandseries.ui.theme.BlueGrey11
import com.popcine.moviesaandseries.ui.theme.fontFamily3

@Composable
fun EpisodeListItem(episode: EpisodeDto, onItemClick: (EpisodeDto) -> Unit
){
    Row(
        modifier = Modifier
            .padding(
                bottom = 5.dp
            )
            .clickable {
                onItemClick(episode)
            }
            .background(color = if (isSystemInDarkTheme()) BlueGrey11 else Color.White),
    ) {
        Image(painter = rememberAsyncImagePainter(
            model = if (!episode.still_path.isNullOrEmpty()) "$BASE_IMAGE_URL${episode.still_path}" else R.drawable.logo
        ),
            contentDescription = null,
            modifier = Modifier
                .requiredSize(110.dp)
                .clip(shape = RoundedCornerShape(15.dp)),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier.padding(
                vertical = 10.dp,
                horizontal = 10.dp
            )
        ) {
            Text(text = "${episode.episode_number} - ${episode.name}",
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                fontFamily = fontFamily3,
                maxLines = 1,
                color =  if (isSystemInDarkTheme()) Color.White  else BlueGrey11)
            Text(text = if (!episode.overview.isNullOrEmpty()) episode.overview else "Sinopse não disponível",
                modifier = Modifier.padding(
                    top = 7.dp
                ),
                fontSize = 13.sp,
                maxLines = 3
            , fontFamily = fontFamily3,
                color =  if (isSystemInDarkTheme()) Color.White  else BlueGrey11)
        }
    }
}