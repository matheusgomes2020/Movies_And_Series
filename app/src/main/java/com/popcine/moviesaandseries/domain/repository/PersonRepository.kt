package com.popcine.moviesaandseries.domain.repository

import com.popcine.moviesaandseries.data.remote.dto.person.PersonDetailDto

interface PersonRepository {

    suspend fun getPerson(castId: String): PersonDetailDto

}