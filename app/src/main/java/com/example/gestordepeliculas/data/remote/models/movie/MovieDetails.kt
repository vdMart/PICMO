package com.example.gestordepeliculas.data.remote.models.movie

import com.example.gestordepeliculas.data.remote.models.Genre
import com.example.gestordepeliculas.data.remote.models.ProductionCompany
import com.example.gestordepeliculas.data.remote.models.ProductionCountry
import com.example.gestordepeliculas.data.remote.models.SpokenLanguage
import kotlinx.serialization.Serializable

@Serializable
data class MovieDetails (
    val adult: Boolean?,
    val backdrop_path: String?,
    val genres: List<Genre>?,
    val id: Int,
    val original_language: String?,
    val original_title: String?,
    val overview: String?,
    val popularity: Double?,
    val poster_path: String?,
    val release_date: String?,
    val title: String?,
    val video: Boolean?,
    val vote_average: Double?,
    val vote_count: Int?,
    val belongs_to_collection: BelongsToCollection?,
    val budget: Int?,
    val homepage: String?,
    val imdb_id: String?,
    val production_companies: List<ProductionCompany>?,
    val production_countries: List<ProductionCountry>?,
    val revenue: Int?,
    val runtime: Int?,
    val spoken_languages: List<SpokenLanguage>?,
    val status: String?,
    val tagline: String?,
)

