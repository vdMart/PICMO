package com.example.gestordepeliculas.domain.model.movieserie.movie

import com.example.gestordepeliculas.data.remote.models.movie.MovieDetails
import com.example.gestordepeliculas.data.remote.models.movie.MovieResult
import com.example.gestordepeliculas.domain.model.movieserie.GenreModel
import com.example.gestordepeliculas.domain.model.movieserie.ProductionCompanyModel
import com.example.gestordepeliculas.domain.model.movieserie.ProductionCountryModel
import com.example.gestordepeliculas.domain.model.movieserie.SpokenLanguageModel
import com.example.gestordepeliculas.domain.model.movieserie.WatchProvidersModel
import com.example.gestordepeliculas.domain.model.movieserie.movieseriepersonModel
import com.example.gestordepeliculas.domain.model.movieserie.toDomain
import com.example.gestordepeliculas.ui.elements.scaffolds.tabscaffold.TabItems

data class MovieModel(
    override val typeModel: TabItems = TabItems.MOVIE,
    override val id: Int,
    override val title_name: String?,
    override val photo_path: String?,

    val adult: Boolean?,
    val backdrop_path: String?,
    val belongs_to_collection: BelongsToCollectionModel?,
    val budget: Int?,
    val genre_ids: List<Int>,
    val genres: List<GenreModel>,
    val homepage: String?,
    val imdb_id: String?,
    val original_language: String?,
    val original_title: String?,
    val overview: String?,
    val popularity: Double?,
    val production_companies: List<ProductionCompanyModel>,
    val production_countries: List<ProductionCountryModel>,
    val release_date: String?,
    val revenue: Int?,
    val runtime: Int?,
    val spoken_languages: List<SpokenLanguageModel>,
    val status: String?,
    val tagline: String?,
    val title: String?,
    val video: Boolean?,
    val vote_average: Double?,
    val vote_count: Int?,
    val providers: WatchProvidersModel?,
): movieseriepersonModel

fun MovieResult.toDomain() = MovieModel(
    id = id,
    title_name = title,
    photo_path = poster_path,

    adult = adult,
    backdrop_path = backdrop_path,
    belongs_to_collection = null,
    budget = null,
    genre_ids = genre_ids?:emptyList<Int>(),
    genres = emptyList<GenreModel>(),
    homepage = null,
    imdb_id = null,
    original_language = original_language,
    original_title = original_title,
    overview = overview,
    popularity = popularity,
    production_companies = emptyList<ProductionCompanyModel>(),
    production_countries = emptyList<ProductionCountryModel>(),
    release_date = release_date,
    revenue = null,
    runtime = null,
    spoken_languages = emptyList<SpokenLanguageModel>(),
    status = null,
    tagline = null,
    title = title,
    video = video,
    vote_average = vote_average,
    vote_count = vote_count,
    providers = null,
)

fun MovieDetails.toDomain() = MovieModel(
    id = id,
    title_name = title,
    photo_path = poster_path,

    adult = adult,
    backdrop_path = backdrop_path,
    belongs_to_collection = belongs_to_collection?.toDomain()?:null,
    budget = budget,
    genre_ids = emptyList<Int>(),
    genres = genres?.map { it.toDomain() }?: emptyList<GenreModel>(),
    homepage = homepage,
    imdb_id = imdb_id,
    original_language = original_language,
    original_title = original_title,
    overview = overview,
    popularity = popularity,
    production_companies = production_companies?.map { it.toDomain() }?: emptyList<ProductionCompanyModel>(),
    production_countries = production_countries?.map { it.toDomain() }?: emptyList<ProductionCountryModel>(),
    release_date = release_date,
    revenue = revenue,
    runtime = runtime,
    spoken_languages = spoken_languages?.map { it.toDomain() }?: emptyList<SpokenLanguageModel>(),
    status = status,
    tagline = tagline,
    title = title,
    video = video,
    vote_average = vote_average,
    vote_count = vote_count,
    providers = null,
)