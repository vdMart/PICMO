package com.example.gestordepeliculas.data.remote.models.serie

import kotlinx.serialization.Serializable

@Serializable
data class Network(
    val id: Int,
    val logo_path: String?,
    val name: String?,
    val origin_country: String?
)