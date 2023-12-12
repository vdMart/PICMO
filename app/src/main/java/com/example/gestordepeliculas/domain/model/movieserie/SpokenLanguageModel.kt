package com.example.gestordepeliculas.domain.model.movieserie

import com.example.gestordepeliculas.data.remote.models.SpokenLanguage


data class SpokenLanguageModel(
    val english_name: String?,
    val iso_639_1: String?,
    val name: String?
)

fun SpokenLanguage.toDomain() = SpokenLanguageModel(
    english_name = english_name,
    iso_639_1 = iso_639_1,
    name = name
)