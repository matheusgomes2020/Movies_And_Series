package com.example.moviesaandseries.domain.repository

import com.example.moviesaandseries.data.remote.dto.person.PersonDetailDto

interface PersonRepository {

    suspend fun getPerson(castId: String): PersonDetailDto

}