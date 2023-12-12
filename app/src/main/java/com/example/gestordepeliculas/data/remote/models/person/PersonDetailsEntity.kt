package com.example.gestordepeliculas.data.remote.models.person

import kotlinx.serialization.Serializable

@Serializable
data class PersonDetailsEntity (
    val adult: Boolean?,
    val gender: Int?,
    val id: Int,
    val known_for_department: String?,
    val name: String,
   // val original_name: String?,
   // val poster_path: String?,
    val popularity: Double?,
    val profile_path: String?,

    val also_known_as: List<String>?,
    val biography: String?,
    val birthday: String?,
    val deathday: String?,
    val homepage: String?,
    val imdb_id: String?,
    val place_of_birth: String?,
)