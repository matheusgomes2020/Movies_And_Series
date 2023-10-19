package com.example.moviesaandseries.presentation.newUI

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import com.example.moviesaandseries.R
import com.example.moviesaandseries.ui.theme.Coral70
import com.example.moviesaandseries.ui.theme.Green67
import com.example.moviesaandseries.ui.theme.Turquoise48

data class Genre(
    @DrawableRes val image: Int,
    @DrawableRes val color: Color,
    val title: String
)
val genres = listOf(
    Genre(title = "Ação", image = R.drawable.logo, color = Turquoise48),
    Genre(title = "Terror", image = R.drawable.logo, color = Green67),
    Genre(title = "Aventura", image = R.drawable.logo, color = Coral70 ),

)