package com.example.gestordepeliculas.data.remote.models.serie

import kotlinx.serialization.Serializable

@Serializable
data class SerieResponse(
    val page: Int,
    val results: List<SerieResult>,
    val total_pages: Int,
    val total_results: Int
)

@Serializable
data class SerieResult(
    val backdrop_path: String?,
    val first_air_date: String?,
    val genre_ids: List<Int>?,
    val id: Int,
    val name: String?,
    val origin_country: List<String>?,
    val original_language: String?,
    val original_name: String?,
    val overview: String?,
    val popularity: Double?,
    val poster_path: String?,
    val vote_average: Double?,
    val vote_count: Int?
)