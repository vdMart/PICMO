package com.example.gestordepeliculas.domain.model.movieserie

import com.example.gestordepeliculas.data.remote.models.Genre


data class GenreModel(
    val id: Int,
    val name: String?
)

fun Genre.toDomain() = GenreModel(
    id = id,
    name = name,
)