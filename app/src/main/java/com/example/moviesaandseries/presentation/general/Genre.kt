package com.example.moviesaandseries.presentation.general

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
    val id: String,
    val type: Int
    // 1 - Genêro somente de filmes
    // 2 - Genêro somente de séries
    // 3 - Genêro de ambos
)
val genres = listOf(
    Genre(title = "Ação", image = R.drawable.logo, color = Grey62, id = "28", type = 1), ////
    Genre(title = "Aventura", image = R.drawable.logo, color = Coral70,id = "12", type = 1), ////
    Genre(title = "Ação e aventura", image = R.drawable.logo, color = DarkSlateBlue14,id = "10759", type = 2),///
    Genre(title = "Animação", image = R.drawable.logo, color = Amarelo2,id = "16", type = 3), //
    Genre(title = "Comédia", image = R.drawable.logo, color = Green67, id = "35", type = 3), //
    Genre(title = "Crime", image = R.drawable.logo, color = BlueGrey11,id = "80", type = 3), //
    Genre(title = "Documentário", image = R.drawable.logo, color = Green54,id = "99", type = 3),//
    Genre(title = "Drama", image = R.drawable.logo, color = Blue59,id = "18", type = 3), //
    Genre(title = "Família", image = R.drawable.logo, color = RoyalBlue65,id = "10751", type = 3), //
    Genre(title = "Faroeste", image = R.drawable.logo, color = Orange53,id = "37", type = 3), //
    Genre(title = "Fantasia", image = R.drawable.logo, color = Red123,id = "14", type = 1), ////
    Genre(title = "Ficção Científica", image = R.drawable.logo, color = Lavender96,id = "878", type = 1), ////
    Genre(title = "Ficção Científica e fantasia", image = R.drawable.logo, color = Lavender96,id = "10765", type = 2),///
    Genre(title = "Guerra", image = R.drawable.logo, color = Green67_A,id = "10752", type = 1), ////
    Genre(title = "Guerra e política", image = R.drawable.logo, color = Green67_A,id = "10768", type = 2),///
    Genre(title = "História", image = R.drawable.logo, color = DarkSlateBlue23,id = "36", type = 1),
    Genre(title = "Infantil", image = R.drawable.logo, color = DarkSlateBlue23,id = "10762", type = 2), ///
    Genre(title = "Musical", image = R.drawable.logo, color = Turquoise48,id = "10402", type = 1),////
    Genre(title = "Mistério", image = R.drawable.logo, color = RoyalBlue65_A,id = "9648", type = 3), //
    Genre(title = "Notícias", image = R.drawable.logo, color = RoyalBlue65_A,id = "10763", type = 2),///
    Genre(title = "Novela", image = R.drawable.logo, color = RoyalBlue65_A,id = "10766", type = 2),///
    Genre(title = "Romance", image = R.drawable.logo, color = Red65,id = "10749", type = 1), ////
    Genre(title = "Reality Show", image = R.drawable.logo, color = Red65,id = "10764", type = 2),///
    Genre(title = "Talk show", image = R.drawable.logo, color = Grey38,id = "10767", type = 2),///
    Genre(title = "Suspense", image = R.drawable.logo, color = Grey38,id = "53", type = 1), ////
    Genre(title = "Terror", image = R.drawable.logo, color = DarkGrey11,id = "27", type = 1), ////
    Genre(title = "TV", image = R.drawable.logo, color = Amarelo3,id = "10770", type = 1), ////
)



