package com.example.gestordepeliculas.domain.model.movieserie.serie

import com.example.gestordepeliculas.data.remote.models.serie.Season


data class SeasonModel(
    val air_date: String?,
    val episode_count: Int?,
    val id: Int,
    val name: String?,
    val overview: String?,
    val poster_path: String?,
    val season_number: Int?,
    val vote_average: Double?
)

fun Season.toDomain() = SeasonModel(
    air_date = air_date,
    episode_count = episode_count,
    id = id,
    name = name,
    overview = overview,
    poster_path = poster_path,
    season_number = season_number,
    vote_average = vote_average
)