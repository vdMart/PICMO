package com.example.gestordepeliculas.domain.model.movieserie.movie

import com.example.gestordepeliculas.data.remote.models.movie.BelongsToCollection

data class BelongsToCollectionModel(
    val id: Int,
    val name: String?,
    val poster_path: String?,
    val backdrop_path: String?,
)

fun BelongsToCollection.toDomain() = BelongsToCollectionModel(
    id = id,
    name = name,
    poster_path = poster_path,
    backdrop_path = backdrop_path,
)