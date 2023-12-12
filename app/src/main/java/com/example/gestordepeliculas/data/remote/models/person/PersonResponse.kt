package com.example.gestordepeliculas.data.remote.models.person

import kotlinx.serialization.Serializable

@Serializable
data class PersonResponse(
    val page: Int,
    val results: List<PersonResult>,
    val total_pages: Int,
    val total_results: Int
)

@Serializable
data class PersonResult(
    val adult: Boolean?,
    val gender: Int?,
    val id: Int,
    val known_for_department: String?,
    val name: String?,
    val original_name: String?,
    val popularity: Float?,
    val profile_path: String?,
)

