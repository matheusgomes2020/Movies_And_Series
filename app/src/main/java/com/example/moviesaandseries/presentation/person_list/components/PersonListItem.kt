package com.example.moviesaandseries.presentation.person_list.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.moviesaandseries.R
import com.example.moviesaandseries.common.Constants
import com.example.moviesaandseries.data.remote.dto.movies.Cast
import com.example.moviesaandseries.presentation.general.DpDimensions
import com.example.moviesaandseries.ui.theme.fontFamily3

@Composable
fun PersonListItem(
    cast: Cast,
    modifier: Modifier = Modifier,
    onItemClick: (Cast) -> Unit
) {
    Column(
        modifier = modifier
            .padding()
            .clickable { onItemClick(cast) },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Image(painter = rememberAsyncImagePainter(
            model = if (!cast.profile_path.isNullOrEmpty()) Constants.BASE_IMAGE_URL + cast.profile_path else R.drawable.logo
        ),
            contentScale = ContentScale.Crop,
            contentDescription = "person image",
            modifier = Modifier
                .width(110.dp)
                .height(130.dp)
                .clip(RoundedCornerShape(10.dp))
        )
        Spacer(modifier = Modifier.height(1.dp))
        Text(
            if (!cast.name.isNullOrEmpty()) cast.name else "sem nome",
            style = MaterialTheme.typography.bodyMedium,
            fontSize = 14.sp,
            fontFamily = fontFamily3,
            modifier = Modifier
                .width(110.dp),
            maxLines = 1
        )

    }
}

@Composable
fun PersonListItemNewUI(
    cast: Cast,
    onItemClick: (Cast) -> Unit
) {

    Surface(
        shape = RoundedCornerShape(DpDimensions.Dp20),
        modifier = Modifier
            .width(120.dp)
            .height(140.dp),
        onClick = { onItemClick(cast) }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .paint(
                    painter = rememberAsyncImagePainter(model = if (!cast.profile_path.isNullOrEmpty()) Constants.BASE_IMAGE_URL + cast.profile_path else R.drawable.logo) ,
                    contentScale = ContentScale.Crop
                )
                .background(
                    brush = Brush.verticalGradient(
                        listOf(
                            Color.Transparent,
                            Color.Transparent,
                            Color.Black
                        )
                    ),
                )
                .clip(RoundedCornerShape(DpDimensions.Small)),
            contentAlignment = Alignment.BottomStart
        ) {

            Column(
                modifier = Modifier.padding(DpDimensions.Small)
            ) {
                Text(
                    modifier = Modifier.width(110.dp),
                    text = if (!cast.name.isNullOrEmpty()) cast.name else "sem nome",
                    style = MaterialTheme.typography.titleSmall,
                    color = Color.White,
                    maxLines = 1
                )
            }
        }
    }
}

