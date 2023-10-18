package com.example.moviesaandseries.presentation.home

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.moviesaandseries.R

sealed class BottomBarScreen

(
val route: String,
val label: String,
@DrawableRes
val icon: Int
) {
    object Movies : BottomBarScreen("movies", "filmes", R.drawable.movie_d)
    object Series : BottomBarScreen("series", "séries", R.drawable.tv_d)
    object Favorites : BottomBarScreen("favorites", "favoritos", icon = R.drawable.favorite_d)

    companion object {
        const val MOVIES = "movies"
        const val SERIES = "series"
        const val FAVORITES = "favorites"
    }


}

//(
//val route: String,
//val title: String,
//val icon: Int
//) {
//    object Movies : BottomBarScreen(
//        route = "movies",
//        title = "Filmes",
//        icon = R.drawable.movie_d
//    )
//
//    object Series : BottomBarScreen(
//        route = "series",
//        title = "Series",
//        icon = R.drawable.tv_d
//    )
//
//    object Favorites : BottomBarScreen(
//        route = "favorites",
//        title = "Favoritos",
//        icon = R.drawable.favorite_d
//    )
//}

//sealed class BottomBarScreen(
//    val route: String,
//    val title: String,
//    val icon: Int
//) {
//    object Movies : BottomBarScreen(
//        route = "movies",
//        title = "Filmes",
//        icon = R.drawable.movie_d
//    )
//
//    object Series : BottomBarScreen(
//        route = "series",
//        title = "Series",
//        icon = R.drawable.tv_d
//    )
//
//    object Favorites : BottomBarScreen(
//        route = "favorites",
//        title = "Favoritos",
//        icon = R.drawable.favorite_d
//    )
//}
