package com.example.moviesaandseries.data.repository

import com.example.moviesaandseries.data.remote.CastApi
import com.example.moviesaandseries.data.remote.dto.person.PersonDetailDto
import com.example.moviesaandseries.domain.repository.PersonRepository
import javax.inject.Inject

class PersonRepositoryImpl @Inject constructor(
    private val api: CastApi
): PersonRepository {
    override suspend fun getPerson(castId: String): PersonDetailDto {
        return api.getPersonInfo(castId)
    }
}