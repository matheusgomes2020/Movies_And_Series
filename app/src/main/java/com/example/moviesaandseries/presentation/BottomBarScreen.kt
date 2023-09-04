package com.example.moviesaandseries.presentation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Movies : BottomBarScreen(
        route = "MOVIES",
        title = "Movies",
        icon = Icons.Default.Home
    )

    object Series : BottomBarScreen(
        route = "SERIES",
        title = "Series",
        icon = Icons.Default.Person
    )

    object Favorites : BottomBarScreen(
        route = "FAVORITES",
        title = "Favorites",
        icon = Icons.Default.Settings
    )
}
