package com.example.gestordepeliculas.domain.model.movieserie.serie

import com.example.gestordepeliculas.data.remote.models.serie.Episode


data class EpisodeModel(
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

fun Episode.toDomain() = EpisodeModel(
    id = id,
    name = name,
    overview = overview,
    vote_average = vote_average,
    vote_count = vote_count,
    air_date = air_date,
    episode_number = episode_number,
    episode_type = episode_type,
    production_code = production_code,
    runtime = runtime,
    season_number = season_number,
    show_id = show_id,
    still_path = still_path
)