package com.example.gestordepeliculas.ui.elements.details.movie

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.twotone.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.gestordepeliculas.R
import com.example.gestordepeliculas.domain.model.movieserie.GenreModel
import com.example.gestordepeliculas.domain.model.movieserie.WatchProvidersModel
import com.example.gestordepeliculas.domain.model.user.UserModel
import com.example.gestordepeliculas.ui.elements.details._common.Duration
import com.example.gestordepeliculas.ui.elements.details._common.Genre
import com.example.gestordepeliculas.ui.elements.details._common.ImgPoster
import com.example.gestordepeliculas.ui.elements.details._common.Provider
import com.example.gestordepeliculas.ui.elements.details._common.ReleaseDate
import com.example.gestordepeliculas.ui.elements.details._common.Sinopsis
import com.example.gestordepeliculas.ui.elements.details._common.Title
import com.example.gestordepeliculas.ui.elements.details._common.VideoPlayer
import com.example.gestordepeliculas.ui.elements.details._common.VideoSelector
import com.example.gestordepeliculas.ui.elements.scaffolds.tabscaffold.TabItems
import com.example.gestordepeliculas.ui.elements.videoplayer.VideoViewModel
import com.example.gestordepeliculas.ui.state.user.UserEvent
import com.example.gestordepeliculas.ui.state.user.UserViewModel

@Composable
fun MovieDetails(
    idMovie: String,
    title: String,
    typeModel: TabItems,
    photo_path: String,
    releaseDate: String,
    runtime: Int,
    genres: List<GenreModel>,
    providers: WatchProvidersModel?,
    overview: String,
    userVM: UserViewModel,
    videoVM: VideoViewModel,
    navController: NavController,
) {
    val userModelState by userVM.userModelState.collectAsState()
    val videoItem by videoVM.videoItem.collectAsState()

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.Top
    ) {

        ImgPoster(photo_path)

        Column(modifier = Modifier.padding(start = 5.dp)) {
            Title(title)
            ReleaseDate(releaseDate)
            Duration(runtime)
            Genre(genres)
            Provider(providers)

            VideoSelector(
                idMovieSerie = idMovie,
                typeModel = typeModel,
                userModelState = userModelState,
                videoVM = videoVM,
                userVM = userVM,
            )
            VideoPlayer(
                idMovieSerie = idMovie,
                stringContent = userModelState.userDetails.movieFile,
                navController = navController,
                videoVM = videoVM,
            )

            Row(horizontalArrangement = Arrangement.SpaceAround) {
                MakeFavoriteMovie(userVM, idMovie, userModelState)
                MakePendingMovie(userVM, idMovie, userModelState)
                MakeViewMovie(userVM, idMovie, userModelState)
            }
        }
    }
    Sinopsis(overview)

}




@Composable
private fun MakeFavoriteMovie(
    userVM: UserViewModel,
    idMovie: String,
    userModelState: UserModel
) {
    IconButton(
        onClick = {
            if (userVM.userModelState.value.userDetails.favoriteMovie.contains(idMovie)) {
                userVM.onEvent(UserEvent.RemoveFavoriteMovie(idMovie))
            } else {
                userVM.onEvent(UserEvent.AddFavoriteMovie(idMovie))
            }
        },
        modifier = Modifier.padding(bottom = 8.dp)
    ) {
        //TODO: cambiar a: userSongList
        if (userModelState.userDetails.favoriteMovie.contains(idMovie)) {
            Icon(imageVector = Icons.Outlined.Favorite, contentDescription = null)
        } else {
            Icon(imageVector = Icons.TwoTone.Favorite, contentDescription = null)
        }
    }
}
@Composable
private fun MakePendingMovie(
    userVM: UserViewModel,
    idMovie: String,
    userModelState: UserModel
) {
    IconButton(
        onClick = {
            if (userVM.userModelState.value.userDetails.pendingMovie.contains(idMovie)) {
                userVM.onEvent(UserEvent.RemovePendingMovie(idMovie))
            } else {
                userVM.onEvent(UserEvent.AddPendingMovie(idMovie))
            }
        },
        modifier = Modifier.padding(bottom = 8.dp)
    ) {
        if (userModelState.userDetails.pendingMovie.contains(idMovie)) {
            Icon(painter = painterResource(R.drawable.ic_playlist_check), contentDescription = null)
        } else {
            Icon(painter = painterResource(R.drawable.ic_playlist_add), contentDescription = null)
        }
    }
}
@Composable
private fun MakeViewMovie(
    userVM: UserViewModel,
    idMovie: String,
    userModelState: UserModel
) {
    IconButton(
        onClick = {
            if (userVM.userModelState.value.userDetails.viewMovie.contains(idMovie)) {
                userVM.onEvent(UserEvent.RemoveViewMovie(idMovie))
            } else {
                userVM.onEvent(UserEvent.AddViewMovie(idMovie))
            }
        },
        modifier = Modifier.padding(bottom = 8.dp)
    ) {
        if (userModelState.userDetails.viewMovie.contains(idMovie)) {
            Icon(painter = painterResource(R.drawable.ic_visibility_off), contentDescription = null)
        } else {
            Icon(painter = painterResource(R.drawable.ic_visibility), contentDescription = null)
        }
    }
}
