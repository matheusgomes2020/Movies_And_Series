package com.example.moviesaandseries.presentation.home

import androidx.annotation.DrawableRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import com.example.moviesaandseries.R

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: Int
) {
    object Movies : BottomBarScreen(
        route = "movies",
        title = "Filmes",
        icon = R.drawable.ic_movie
    )

    object Series : BottomBarScreen(
        route = "series",
        title = "Series",
        icon = R.drawable.ic_tv
    )

    object Favorites : BottomBarScreen(
        route = "favorites",
        title = "Favoritos",
        icon = R.drawable.ic_boomark
    )
}
