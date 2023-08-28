package com.example.moviesaandseries.data.remote.dto


data class Images(
    val backdrops: List<Profile>,
    val logos: List<Image>,
    val posters: List<Image>,
    val profiles: List<Image>,
    val stills: List<Profile>
)