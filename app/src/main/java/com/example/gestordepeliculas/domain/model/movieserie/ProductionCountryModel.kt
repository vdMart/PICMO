package com.example.gestordepeliculas.domain.model.movieserie

import com.example.gestordepeliculas.data.remote.models.ProductionCountry


data class ProductionCountryModel(
    val iso_3166_1: String?,
    val name: String?
)

fun ProductionCountry.toDomain() = ProductionCountryModel(
    iso_3166_1 = iso_3166_1,
    name = name
)