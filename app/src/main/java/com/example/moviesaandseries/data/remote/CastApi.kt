package com.example.moviesaandseries.data.remote

import com.example.moviesaandseries.common.Constants
import com.example.moviesaandseries.data.remote.dto.person.PersonDetailDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CastApi {
    @GET("person/{personId}?language=" + Constants.LANGUAGE + "&api_key=" + Constants.API_KEY + "&append_to_response=images,movie_credits,tv_credits")
    suspend fun getPersonInfo(@Path("personId") castId: String ): PersonDetailDto
}