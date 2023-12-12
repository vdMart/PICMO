package com.example.gestordepeliculas.data.remote.models.serie

import com.example.gestordepeliculas.data.remote.models.Genre
import com.example.gestordepeliculas.data.remote.models.ProductionCompany
import com.example.gestordepeliculas.data.remote.models.ProductionCountry
import com.example.gestordepeliculas.data.remote.models.SpokenLanguage
import kotlinx.serialization.Serializable

@Serializable
data class SerieDetails (
    val backdrop_path: String?,
    val first_air_date: String?,
    val id: Int,
    val name: String?,
    val origin_country: List<String>?,
    val original_language: String?,
    val original_name: String?,
    val overview: String?,
    val popularity: Double?,
    val poster_path: String?,
    val vote_average: Double?,
    val vote_count: Int?,

    val adult: Boolean?,
    val created_by: List<Creator>?,
    val episode_run_time: List<Int>?,
    val genres: List<Genre>?,
    val homepage: String?,
    val in_production: Boolean?,
    val languages: List<String>?,
    val last_air_date: String?,
    val last_episode_to_air: Episode?,
    val next_episode_to_air: Episode?,
    val networks: List<Network>?,
    val number_of_episodes: Int?,
    val number_of_seasons: Int?,
    val production_companies: List<ProductionCompany>?,
    val production_countries: List<ProductionCountry>?,
    val seasons: List<Season>?,
    val spoken_languages: List<SpokenLanguage>?,
    val status: String?,
    val tagline: String?,
    val type: String?,
)