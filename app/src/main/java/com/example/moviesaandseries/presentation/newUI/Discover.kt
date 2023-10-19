package com.example.moviesaandseries.presentation.newUI

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import com.example.moviesaandseries.R
import com.example.moviesaandseries.ui.theme.Coral70
import com.example.moviesaandseries.ui.theme.Orange53
import com.example.moviesaandseries.ui.theme.Turquoise48

data class Discover(
    val title: String,
    @DrawableRes val image: Int,
    val user: String,
    @DrawableRes val userImage: Int,
    val backgroundColor: Color
)

val discovers = listOf(
    Discover(
        title = "Get Smarter with Productivity Quizzes",
        image = R.drawable.logo,
        user = "John Doe",
        userImage = R.drawable.logo,
        backgroundColor = Turquoise48
    ),
    Discover(
        title = "Great ideas come from Brilliant Minds",
        image = R.drawable.logo,
        user = "Jane Doe",
        userImage = R.drawable.logo,
        backgroundColor = Orange53
    ),
    Discover(
        title = "Great ideas come from Brilliant Minds",
        image = R.drawable.logo,
        user = "Jane Doe",
        userImage = R.drawable.logo,
        backgroundColor = Coral70
    )
)



val trending = listOf(
    Discover(
        title = "Let's Memorize the Names of the Flowers",
        image = R.drawable.logo,
        user = "John Doe",
        userImage = R.drawable.logo,
        backgroundColor = Turquoise48
    ),
    Discover(
        title = "Earth is Our Home and Will Always Be",
        image = R.drawable.logo,
        user = "Jane Doe",
        userImage = R.drawable.logo,
        backgroundColor = Orange53
    ),
    Discover(
        title = "Recycle to Keep our World Clean as Always",
        image = R.drawable.logo,
        user = "Jane Doe",
        userImage = R.drawable.logo,
        backgroundColor = Coral70
    )
)

val topPics = listOf(
    Discover(
        title = "Save Life Around, Green Our Earth!",
        image = R.drawable.logo,
        user = "John Doe",
        userImage = R.drawable.logo,
        backgroundColor = Turquoise48
    ),
    Discover(
        title = "Earth is Our Home and Will Always Be",
        image = R.drawable.logo,
        user = "Jane Doe",
        userImage = R.drawable.logo,
        backgroundColor = Orange53
    ),
    Discover(
        title = "Recycle to Keep our World Clean as Always",
        image = R.drawable.logo,
        user = "Jane Doe",
        userImage = R.drawable.logo,
        backgroundColor = Orange53
    )
)

