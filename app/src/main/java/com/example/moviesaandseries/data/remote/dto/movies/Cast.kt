package com.example.moviesaandseries.data.remote.dto.movies

import android.os.Parcelable
import com.example.moviesaandseries.data.remote.dto.Images
import com.example.moviesaandseries.data.remote.dto.Role
import com.example.moviesaandseries.data.remote.dto.series.TvCredits
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue


@Parcelize
data class Cast(
    val adult: Boolean,
    val cast_id: Int,
    val character: String,
    val credit_id: String,
    val gender: Int,
    val id: Int,
    val known_for_department: String,
    val name: String,
    val order: Int,
    val original_name: String,
    val popularity: Double,
    val profile_path: String,

    val roles: @RawValue List<Role>,
    val total_episode_count: Int,
    val also_known_as: List<String>,
    val biography: String,
    val birthday: String,
    val deathday: @RawValue Any,
    val homepage: @RawValue Any,
    val images:  @RawValue Images,
    val imdb_id: String,
    val movie_credits: @RawValue MovieCredits,
    val place_of_birth: String,
    val tv_credits:  @RawValue TvCredits
): Parcelable