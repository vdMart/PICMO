package com.example.gestordepeliculas.domain.model.movieserie.person

import com.example.gestordepeliculas.data.remote.models.person.PersonDetailsEntity
import com.example.gestordepeliculas.data.remote.models.person.PersonResult
import com.example.gestordepeliculas.domain.model.movieserie.movieseriepersonModel
import com.example.gestordepeliculas.ui.elements.scaffolds.tabscaffold.TabItems

data class PersonModel (
    override val typeModel: TabItems = TabItems.PERSON,
    override val id: Int,
    override val title_name: String?,
    override val photo_path: String?,

    val adult: Boolean?,
    val also_known_as: List<String>,
    val biography: String?,
    val birthday: String?,
    val deathday: String?,
    val gender: Int?,
    val homepage: String?,
    val imdb_id: String?,
    val known_for_department: String?,
    val name: String?,
    val place_of_birth: String?,
    val popularity: Double?,
    val original_name: String?,
    val known_for: List<KnownForModel>
): movieseriepersonModel

fun PersonDetailsEntity.toDomain() = PersonModel(
    id = id,
    title_name = name,
    photo_path = profile_path,

    adult = adult,
    also_known_as = also_known_as?:emptyList(),
    biography = biography,
    birthday = birthday,
    deathday = deathday,
    gender = gender,
    homepage = homepage,
    imdb_id = imdb_id,
    known_for_department = known_for_department,
    name = name,
    place_of_birth = place_of_birth,
    popularity = popularity,
    original_name = null,
    known_for = emptyList<KnownForModel>(),
)


fun PersonResult.toDomain() = PersonModel(
    id = id,
    title_name = name,
    photo_path = profile_path,
    adult = adult,
    also_known_as = emptyList(),
    biography = null,
    birthday = null,
    deathday = null,
    gender = gender,
    homepage = null,
    imdb_id = null,
    known_for_department = known_for_department,
    name = name,
    place_of_birth = null,
    popularity = popularity?.toDouble(),
    original_name = original_name,
    known_for = emptyList<KnownForModel>(),
)