package com.example.moviesaandseries.presentation.movie_detail.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.moviesaandseries.R
import com.example.moviesaandseries.common.Constants
import com.example.moviesaandseries.data.remote.dto.Cast

@Composable
fun CastListItem(
    cast: Cast,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Image(painter = rememberAsyncImagePainter(
            model = if (!cast.profile_path.isNullOrEmpty()) Constants.BASE_IMAGE_URL + cast.profile_path else R.drawable.flash
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
            fontSize = 14.sp
        )

    }
}

