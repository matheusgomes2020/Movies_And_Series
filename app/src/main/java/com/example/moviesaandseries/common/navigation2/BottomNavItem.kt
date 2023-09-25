package com.example.moviesaandseries.common.navigation2

import com.example.moviesaandseries.R

sealed class BottomNavItem(
    var title: String,
    var icon: Int
) {
    object Movies :
        BottomNavItem(
            "Movies",
            R.drawable.ic_movie
        )

    object Series :
        BottomNavItem(
            "Series",
            R.drawable.ic_tv
        )

    object Favorites :
        BottomNavItem(
            "Favorites",
            R.drawable.ic_boomark
        )

}