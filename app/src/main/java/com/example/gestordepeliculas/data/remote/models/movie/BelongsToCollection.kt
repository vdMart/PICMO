package com.example.gestordepeliculas.data.remote.models.movie

import kotlinx.serialization.Serializable

@Serializable
data class BelongsToCollection(
    val id: Int,
    val name: String?,
    val poster_path: String?,
    val backdrop_path: String?
)