package com.popcine.moviesaandseries.domain.repository

import com.google.firebase.auth.FirebaseUser
import com.popcine.moviesaandseries.presentation.signIn.Resource

interface AuthRepository {
    val currentUser: FirebaseUser?
    suspend fun login(email: String, password: String): Resource<FirebaseUser>
    suspend fun signup(name: String, email: String, password: String): Resource<FirebaseUser>
    fun logout()
}