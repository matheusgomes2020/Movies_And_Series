package com.example.moviesaandseries.presentation.signIn

data class SignInState(
    val isSignInSuccessful: Boolean = false,
    val signInError: String? = null
)