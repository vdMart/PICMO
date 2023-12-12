package com.example.gestordepeliculas.domain.model.user

import android.net.Uri

data class UserDetails(
    var theme: UserThemes = UserThemes.SYSTEM,
    var language: UserLang = UserLang.SYSTEM,
    var favoriteMovie: List<String> = emptyList(),
    var viewMovie: List<String> = emptyList(),
    var pendingMovie : List<String> = emptyList(),
    var noteMovie: MutableMap<String, Double> = mutableMapOf(),
    var favoriteSerie: List<String> = emptyList(),
    var viewSerie: List<String> = emptyList(),
    var pendingSerie : List<String> = emptyList(),
    var noteSerie: MutableMap<String, Double> = mutableMapOf(),
    var favoritePerson: List<String> = emptyList(),
    var movieFile: MutableMap<String, Uri> = mutableMapOf(),
    var serieFile: MutableMap<String, Uri> = mutableMapOf(),
) {

    fun toMap(): MutableMap<String, Any> {
        return mutableMapOf(
            "theme" to this.theme,
            "language" to this.language,
            "movie_favorite" to this.favoriteMovie,
            "movie_again" to this.viewMovie,
            "movie_pending" to this.pendingMovie,
            "movie_note" to this.noteMovie,
            "serie_favorite" to this.favoriteSerie,
            "serie_again" to this.viewSerie,
            "serie_note" to this.noteSerie,
            "serie_pending" to this.pendingSerie,
            "person_favorite" to this.favoritePerson,
            "movie_file" to this.movieFile,
            "episode_file" to this.serieFile,
        )
    }

    fun isEmpty(): Boolean {
        return this.theme == UserThemes.SYSTEM && this.favoriteMovie.isEmpty() && this.viewMovie.isEmpty() && this.noteMovie.isEmpty()
    }

}