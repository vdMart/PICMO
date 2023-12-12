package com.example.gestordepeliculas.domain.model.movieserie.serie

import com.example.gestordepeliculas.data.remote.models.serie.Creator


data class CreatorModel(
    val id: Int,
    val credit_id: String?,
    val name: String?,
    val gender: Int?,
    val profile_path: String?
)

fun Creator.toDomain() = CreatorModel(
    id = id,
    credit_id = credit_id,
    name = name,
    gender = gender,
    profile_path = profile_path
)