package com.example.gestordepeliculas.domain.model

import com.example.gestordepeliculas.ui.elements.scaffolds.tabscaffold.TabItems

sealed class HomeCategorySerieMovie(val title:String, val typeContent: TabItems): HomeCategory {
    object SerieMovieDiscover: HomeCategorySerie("seriemovie_discover", TabItems.ALL)
    object SerieMovieFavorite: HomeCategorySerie("seriemovie_favorite", TabItems.ALL)
    object SerieMovieAgain: HomeCategorySerie("seriemovie_again", TabItems.ALL)
    object SerieMoviePending: HomeCategorySerie("seriemovie_pending", TabItems.ALL)
}
