package com.example.moviesaandseries.domain.model

data class MovieFirebase(
    var id: String,
    val posterPath: String,
    val tipo: String,
    val title: String,
    var userId: String,
    var idFirebase: String


) {
    constructor() : this("", "", "", "", "", "")
    constructor(id: Int, title: String, poster_path: String, tipo: String, idFirebase: String, userId: String) : this()
}


