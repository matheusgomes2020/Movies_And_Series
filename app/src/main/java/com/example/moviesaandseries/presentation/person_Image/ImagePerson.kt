package com.example.moviesaandseries.presentation.person_Image

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.moviesaandseries.R
import com.example.moviesaandseries.common.Constants

@Composable
fun ImagePersonScreen(
    image: String,
    navController: NavController,

) {
    Box(modifier = Modifier.fillMaxSize()) {
            Card(
                modifier = Modifier
                    //.padding(8.dp)
            ) {
                Image(
                    painter = rememberAsyncImagePainter(
                        model = if (!image.isNullOrEmpty()) Constants.BASE_IMAGE_URL + "/$image" else R.drawable.flash
                    ),
                    contentScale = ContentScale.Crop,
                    contentDescription = "person  image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(500.dp)
                )

            }
        }
}
