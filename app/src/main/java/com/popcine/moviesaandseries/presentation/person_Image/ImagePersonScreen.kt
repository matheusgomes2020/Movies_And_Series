package com.popcine.moviesaandseries.presentation.person_Image

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.popcine.moviesaandseries.R
import com.popcine.moviesaandseries.common.Constants
import com.popcine.moviesaandseries.presentation.general.AppBarWithBack
import com.popcine.moviesaandseries.presentation.general.CustomPadding
import com.popcine.moviesaandseries.presentation.general.DpDimensions
import com.popcine.moviesaandseries.ui.theme.BlueGrey11
import com.popcine.moviesaandseries.ui.theme.DarkGrey11

@Composable
fun ImagePersonScreen(
    image: String,
    navController: NavController,
    isSystemInDarkTheme: Boolean

) {

    val systemUiController = rememberSystemUiController()
    val useDarkIcons = !isSystemInDarkTheme

    SideEffect {
        systemUiController.setSystemBarsColor(
            color = if (useDarkIcons)
                Color.White else DarkGrey11,
            darkIcons = useDarkIcons
        )
    }

    Scaffold(
        topBar = {
            AppBarWithBack(title = "Imagem",
                backIcon = Icons.Default.ArrowBack,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                //.horizontalScroll(rememberScrollState())
                .fillMaxSize()
                .background(
                    color = if (useDarkIcons)
                        Color.White else DarkGrey11
                )
        ) {
            CustomPadding(
                verticalPadding = 0.dp,
                horizontalPadding = DpDimensions.Normal
            ) {
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
    }

}
