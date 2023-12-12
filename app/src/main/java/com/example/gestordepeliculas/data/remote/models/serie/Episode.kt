package com.example.gestordepeliculas.data.remote.models.serie

import kotlinx.serialization.Serializable

@Serializable
data class Episode(
    val id: Int,
    val name: String?,
    val overview: String?,
    val vote_average: Double?,
    val vote_count: Int?,
    val air_date: String?,
    val episode_number: Int?,
    val episode_type: String?,
    val production_code: String?,
    val runtime: Int?,
    val season_number: Int?,
    val show_id: Int?,
    val still_path: String?
)