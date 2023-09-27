package com.example.moviesaandseries.presentation.cast.castImage

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.moviesaandseries.R
import com.example.moviesaandseries.common.Constants

@Composable
fun ImagePersonScreen(
    image: String,
    navController: NavController,
    //viewModel: ImagePersonViewModel = hiltViewModel()

) {
    //val state = viewModel.state.value
    Box(modifier = Modifier.fillMaxSize()) {
        Log.d("IEIEIE", "ImagePersonScreen: $image")
       // state.image?.let { image ->
            Card(
                modifier = Modifier
                    .padding(8.dp)
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
//    }

}
