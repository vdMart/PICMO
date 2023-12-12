package com.example.gestordepeliculas.data.remote.models

import kotlinx.serialization.Serializable

@Serializable
data class Genre(
    val id: Int,
    val name: String?
)