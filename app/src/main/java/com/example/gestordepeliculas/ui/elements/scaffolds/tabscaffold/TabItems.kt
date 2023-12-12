package com.example.gestordepeliculas.ui.elements.scaffolds.tabscaffold

enum class TabItems {
    ALL, MOVIE, SERIE, PERSON;

    fun getDisplayName() = when(this) {
        ALL -> "Todo"
        MOVIE -> "Película"
        SERIE -> "Serie"
        PERSON -> "Persona"
    }

}