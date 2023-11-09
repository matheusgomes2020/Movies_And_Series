package com.popcine.moviesaandseries.presentation.home

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.popcine.moviesaandseries.R

sealed class Screen(
    val route: String,
    @StringRes val label: Int,
    @DrawableRes val icon: Int
) {
    object Movies : Screen(MOVIES, R.string.movies, R.drawable.movie_d)
    object Series : Screen(SERIES, R.string.series, R.drawable.tv_d)
    object Favorites : Screen(FAVORITES, R.string.favorites, icon = R.drawable.favorite_d)

    companion object {
        const val MOVIES = "movies"
        const val SERIES = "series"
        const val FAVORITES = "favorites"
    }


}
