package com.popcine.moviesaandseries.domain.model

data class SeriesFirebase(
    var id: String,
    val posterPath: String,
    val name: String,
    var userId: String,
    var idFirebase: String


) {
    constructor() : this("", "", "", "", "")
    constructor(id: Int, name: String, poster_path: String, idFirebase: String, userId: String) : this()
}


