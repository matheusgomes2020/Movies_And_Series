package com.example.moviesaandseries.presentation.newUI

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import com.example.moviesaandseries.R
import com.example.moviesaandseries.ui.theme.Amarelo2
import com.example.moviesaandseries.ui.theme.Amarelo3
import com.example.moviesaandseries.ui.theme.Blue59
import com.example.moviesaandseries.ui.theme.BlueGrey11
import com.example.moviesaandseries.ui.theme.Coral70
import com.example.moviesaandseries.ui.theme.DarkGrey11
import com.example.moviesaandseries.ui.theme.DarkSlateBlue14
import com.example.moviesaandseries.ui.theme.DarkSlateBlue23
import com.example.moviesaandseries.ui.theme.Green54
import com.example.moviesaandseries.ui.theme.Green67
import com.example.moviesaandseries.ui.theme.Green67_A
import com.example.moviesaandseries.ui.theme.Grey38
import com.example.moviesaandseries.ui.theme.Grey62
import com.example.moviesaandseries.ui.theme.Lavender96
import com.example.moviesaandseries.ui.theme.Orange53
import com.example.moviesaandseries.ui.theme.Red123
import com.example.moviesaandseries.ui.theme.Red65
import com.example.moviesaandseries.ui.theme.RoyalBlue65
import com.example.moviesaandseries.ui.theme.RoyalBlue65_A
import com.example.moviesaandseries.ui.theme.Turquoise48

data class Genre(
    @DrawableRes val image: Int,
    @DrawableRes val color: Color,
    val title: String,
    val id: String
)
val genres = listOf(
    Genre(title = "Ação", image = R.drawable.logo, color = Grey62, id = "28"),
    Genre(title = "Aventura", image = R.drawable.logo, color = Coral70,id = "12"),
    Genre(title = "Animação", image = R.drawable.logo, color = Amarelo2,id = "16"),
    Genre(title = "Comédia", image = R.drawable.logo, color = Green67, id = "35"),
    Genre(title = "Crime", image = R.drawable.logo, color = BlueGrey11,id = "80"),
    Genre(title = "Documentário", image = R.drawable.logo, color = Green54,id = "99"),
    Genre(title = "Drama", image = R.drawable.logo, color = Blue59,id = "18"),
    Genre(title = "Família", image = R.drawable.logo, color = RoyalBlue65,id = "10751"),
    Genre(title = "Faroeste", image = R.drawable.logo, color = Orange53,id = "37"),
    Genre(title = "Fantasia", image = R.drawable.logo, color = Red123,id = "14"),
    Genre(title = "Ficção Científica", image = R.drawable.logo, color = Lavender96,id = "878"),
    Genre(title = "Guerra", image = R.drawable.logo, color = Green67_A,id = "10752"),
    Genre(title = "História", image = R.drawable.logo, color = DarkSlateBlue23,id = "36"),
    Genre(title = "Musical", image = R.drawable.logo, color = Turquoise48,id = "10402"),
    Genre(title = "Mistério", image = R.drawable.logo, color = RoyalBlue65_A,id = "9648"),
    Genre(title = "Romance", image = R.drawable.logo, color = Red65,id = "10749"),
    Genre(title = "Suspense", image = R.drawable.logo, color = Grey38,id = "53"),
    Genre(title = "Terror", image = R.drawable.logo, color = DarkGrey11,id = "27"),
    Genre(title = "TV", image = R.drawable.logo, color = Amarelo3,id = "10770"),
)



