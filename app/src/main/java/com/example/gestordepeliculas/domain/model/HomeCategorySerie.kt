package com.example.gestordepeliculas.domain.model

import com.example.gestordepeliculas.ui.elements.scaffolds.tabscaffold.TabItems

sealed class HomeCategorySerie(val id:String, val typeContent: TabItems): HomeCategory {
    object SerieDiscover:HomeCategorySerie("serie_discover", TabItems.SERIE)
    object SerieFavorite:HomeCategorySerie("serie_favorite", TabItems.SERIE)
    object SerieAgain:HomeCategorySerie("serie_again", TabItems.SERIE)
    object SeriePending:HomeCategorySerie("serie_pending", TabItems.SERIE)
}