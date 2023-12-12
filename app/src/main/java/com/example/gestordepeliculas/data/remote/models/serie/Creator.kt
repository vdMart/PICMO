package com.example.gestordepeliculas.data.remote.models.serie

import kotlinx.serialization.Serializable

@Serializable
data class Creator(
    val id: Int,
    val credit_id: String?,
    val name: String?,
    val gender: Int?,
    val profile_path: String?
)