package com.example.moviesaandseries.presentation.newUI

import androidx.annotation.DrawableRes
import com.example.moviesaandseries.R


data class MOviesAndSeriesT(
    @DrawableRes val image: Int,
    val title: String
)
val moviesAndSeriesT = listOf(
    MOviesAndSeriesT(title = "The Flash", image = R.drawable.logo),
    MOviesAndSeriesT(title = "Barbie", image = R.drawable.logo),
    MOviesAndSeriesT(title = "Missão impossível a era dos limões", image = R.drawable.logo),
    MOviesAndSeriesT(title = "Missão impossível a era dos limões", image = R.drawable.logo),
    MOviesAndSeriesT(title = "Missão impossível a era dos limões", image = R.drawable.logo),
    MOviesAndSeriesT(title = "Missão impossível a era dos limões", image = R.drawable.logo),
)