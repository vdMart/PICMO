package com.example.gestordepeliculas.data.remote.models.person

import kotlinx.serialization.Serializable

@Serializable
data class KnownFor(
    val adult: Boolean?,
    val backdrop_path: String?,
    val id: Int?,
    val title: String?,
    val original_language: String?,
    val original_title: String?,
    val overview: String?,
    val poster_path: String?,
    val media_type: String?,
    val genre_ids: List<Int>?,
    val popularity: Float?,
    val release_date: String?,
    val video: Boolean?,
    val vote_average: Float?,
    val vote_count: Int?
)