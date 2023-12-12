package com.example.gestordepeliculas.domain.model.movieserie.serie

import com.example.gestordepeliculas.data.remote.models.serie.Network


data class NetworkModel(
    val id: Int,
    val logo_path: String?,
    val name: String?,
    val origin_country: String?
)

fun Network.toDomain() = NetworkModel(
    id = id,
    logo_path = logo_path,
    name = name,
    origin_country = origin_country
)