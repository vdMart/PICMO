package com.example.gestordepeliculas.data.remote.models.movie

import kotlinx.serialization.Serializable

@Serializable
class TrailerResponse(val id: Int, val results: List<TrailerMovieResult>)

@Serializable
data class TrailerMovieResult(
    val iso_639_1: String?,
    val iso_3166_1: String?,
    val name: String?,
    val key: String?,
    val site: String?,
    val size: Int?,
    val type: String?,
    val official: Boolean?,
    val published_at: String?,
    val id: String?
)