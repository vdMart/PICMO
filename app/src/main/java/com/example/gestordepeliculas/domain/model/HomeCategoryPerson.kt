package com.example.gestordepeliculas.domain.model

import com.example.gestordepeliculas.ui.elements.scaffolds.tabscaffold.TabItems

sealed class HomeCategoryPerson(val id:String, val typeContent: TabItems): HomeCategory {
    object PersonDirector:HomeCategoryPerson("person_director", TabItems.PERSON)
    object PersonDirectorFavorite:HomeCategoryPerson("person_director_favorite", TabItems.PERSON)
    object PersonActor:HomeCategoryPerson("person_actor", TabItems.PERSON)
    object PersonActorFavorite:HomeCategoryPerson("person_actor_favorite", TabItems.PERSON)

}
