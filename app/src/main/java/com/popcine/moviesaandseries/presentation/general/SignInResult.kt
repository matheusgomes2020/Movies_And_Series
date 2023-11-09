package com.popcine.moviesaandseries.presentation.general

data class SignInResult(
    val data: UserData?,
    val errorMessage: String?
)

data class UserData(
    val userId: String,
    val email: String,
    var username: String?,
    val profilePictureUrl: String?
)