package com.popcine.moviesaandseries.data.repository

import com.popcine.moviesaandseries.data.remote.CastApi
import com.popcine.moviesaandseries.data.remote.dto.person.PersonDetailDto
import com.popcine.moviesaandseries.domain.repository.PersonRepository
import javax.inject.Inject

class PersonRepositoryImpl @Inject constructor(
    private val api: CastApi
): PersonRepository {
    override suspend fun getPerson(castId: String): PersonDetailDto {
        return api.getPersonInfo(castId)
    }
}