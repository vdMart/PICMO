package com.example.gestordepeliculas.ui.state.user

import android.net.Uri
import com.example.gestordepeliculas.domain.model.user.UserThemes

sealed class UserEvent {
    object SignOut: UserEvent()
    object UserUpdate: UserEvent()

    data class ChangeTheme(val theme: UserThemes): UserEvent()

    /* MOVIE */
    data class AddFavoriteMovie(val idMovie: String): UserEvent()
    data class RemoveFavoriteMovie(val idMovie: String): UserEvent()
    data class AddPendingMovie(val idMovie: String): UserEvent()
    data class RemovePendingMovie(val idMovie: String): UserEvent()
    data class AddViewMovie(val idMovie: String): UserEvent()
    data class RemoveViewMovie(val idMovie: String): UserEvent()
    data class AddNoteMovie(val idMovie: String, val note: Double): UserEvent()
    data class AddFileMovie(val idMovie: String, val uri: Uri): UserEvent()

    /* Serie */
    data class AddFavoriteSerie(val idSerie: String): UserEvent()
    data class RemoveFavoriteSerie(val idSerie: String): UserEvent()
    data class AddPendingSerie(val idSerie: String): UserEvent()
    data class RemovePendingSerie(val idSerie: String): UserEvent()
    data class AddViewSerie(val idSerie: String): UserEvent()
    data class RemoveViewSerie(val idSerie: String): UserEvent()
    data class AddNoteSerie(val idSerie: String, val note: Double): UserEvent()
    data class AddFileSerie(val idSerie: String, val uri: Uri): UserEvent()

    /* Person */
    data class AddFavoritePerson(val idPerson: String): UserEvent()
    data class RemoveFavoritePerson(val idPerson: String): UserEvent()

}