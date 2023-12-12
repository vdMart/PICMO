package com.example.gestordepeliculas.domain.model.movieserie.person

import com.example.gestordepeliculas.data.remote.models.person.KnownFor

data class KnownForModel(
    val adult: Boolean?,
    val backdrop_path: String?,
    val id: Int?,
    val title: String?,
    val original_language: String?,
    val original_title: String?,
    val overview: String?,
    val poster_path: String?,
    val media_type: String?,
    val genre_ids: List<Int>,
    val popularity: Float?,
    val release_date: String?,
    val video: Boolean?,
    val vote_average: Float?,
    val vote_count: Int?
)

fun KnownFor.toDomain() = KnownForModel(
    adult = adult,
    backdrop_path = backdrop_path,
    id = id,
    title = title,
    original_language = original_language,
    original_title = original_title,
    overview = overview,
    poster_path = poster_path,
    media_type = media_type,
    genre_ids = genre_ids?: emptyList(),
    popularity = popularity,
    release_date = release_date,
    video = video,
    vote_average = vote_average,
    vote_count = vote_count
)