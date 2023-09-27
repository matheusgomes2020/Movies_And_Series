package com.example.moviesaandseries.domain.use_case.get_person

import com.example.moviesaandseries.common.Resource
import com.example.moviesaandseries.data.remote.dto.person.toPerson
import com.example.moviesaandseries.domain.model.Person
import com.example.moviesaandseries.domain.repository.PersonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

import javax.inject.Inject

class GetPersonUseCase @Inject constructor(
    private val repository: PersonRepository
) {
    operator fun invoke(castId: String): Flow<Resource<Person>> = flow {

        try {
            emit(Resource.Loading<Person>())
            val person = repository.getPerson( castId ).toPerson()
            emit(Resource.Success<Person>(person))
        } catch(e: HttpException) {
            emit(Resource.Error<Person>(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(Resource.Error<Person>("Couldn't reach server. Check your internet connection."))
        }

    }
}