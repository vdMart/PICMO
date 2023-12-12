package com.example.gestordepeliculas.domain.model

import com.example.gestordepeliculas.ui.elements.scaffolds.tabscaffold.TabItems

sealed class HomeCategoryMovie(val id: String, val typeContent: TabItems): HomeCategory {
    object MovieDiscover:HomeCategoryMovie("movie_discover", TabItems.MOVIE)
    object MovieFavorite:HomeCategoryMovie("movie_favorite", TabItems.MOVIE)
    object MovieAgain:HomeCategoryMovie("movie_again", TabItems.MOVIE)
    object MoviePending:HomeCategoryMovie ("movie_pending", TabItems.MOVIE)
}