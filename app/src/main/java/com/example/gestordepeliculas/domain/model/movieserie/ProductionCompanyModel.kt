package com.example.gestordepeliculas.domain.model.movieserie

import com.example.gestordepeliculas.data.remote.models.ProductionCompany


data class ProductionCompanyModel(
    val id: Int,
    val logo_path: String?,
    val name: String?,
    val origin_country: String?
)

fun ProductionCompany.toDomain() = ProductionCompanyModel(
    id = id,
    logo_path = logo_path,
    name = name,
    origin_country = origin_country
)