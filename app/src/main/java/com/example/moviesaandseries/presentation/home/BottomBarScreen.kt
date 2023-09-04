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
    val icon: ImageVector
) {
    object Movies : BottomBarScreen(
        route = "MOVIES",
        title = "Filmes",
        icon = Icons.Default.Home
    )

    object Series : BottomBarScreen(
        route = "SERIES",
        title = "Series",
        icon = Icons.Default.MailOutline
    )

    object Favorites : BottomBarScreen(
        route = "FAVORITES",
        title = "Favoritos",
        icon = Icons.Default.Favorite
    )
}
