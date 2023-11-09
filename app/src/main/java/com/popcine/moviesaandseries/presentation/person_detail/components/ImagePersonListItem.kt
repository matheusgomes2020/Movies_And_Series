package com.popcine.moviesaandseries.presentation.person_detail.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.popcine.moviesaandseries.R
import com.popcine.moviesaandseries.common.Constants
import com.popcine.moviesaandseries.data.remote.dto.Profile

@Composable
fun ImagePersonListItem(
    profile: Profile,
    modifier: Modifier = Modifier,
    onItemClick: (Profile) -> Unit
) {
    Card(
        modifier = modifier
            .clickable { onItemClick(profile) }
    ) {
        Image(
            painter = rememberAsyncImagePainter(
                model = if (!profile.file_path.isNullOrEmpty()) Constants.BASE_IMAGE_URL + profile.file_path else R.drawable.padrao
            ),
            contentScale = ContentScale.Crop,
            contentDescription = "profile image",
            modifier = Modifier
                .width(165.dp)
                .height(200.dp)
        )
    }
}