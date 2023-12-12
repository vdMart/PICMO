package com.example.gestordepeliculas.ui.elements.details.serie

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
import com.example.gestordepeliculas.domain.model.movieserie.serie.NetworkModel
import com.example.gestordepeliculas.domain.model.movieserie.serie.SeasonModel
import com.example.gestordepeliculas.domain.model.user.UserModel
import com.example.gestordepeliculas.ui.elements.details._common.Genre
import com.example.gestordepeliculas.ui.elements.details._common.ImgPoster
import com.example.gestordepeliculas.ui.elements.details._common.Network
import com.example.gestordepeliculas.ui.elements.details._common.NumSeasons
import com.example.gestordepeliculas.ui.elements.details._common.Provider
import com.example.gestordepeliculas.ui.elements.details._common.ReleaseDate
import com.example.gestordepeliculas.ui.elements.details._common.Sinopsis
import com.example.gestordepeliculas.ui.elements.details._common.Title
import com.example.gestordepeliculas.ui.elements.scaffolds.tabscaffold.TabItems
import com.example.gestordepeliculas.ui.elements.videoplayer.VideoViewModel
import com.example.gestordepeliculas.ui.state.user.UserEvent
import com.example.gestordepeliculas.ui.state.user.UserViewModel

@Composable
fun SerieDetails(
    idSerie: String,
    name: String,
    typeModel: TabItems,
    poster_path: String,
    firstAirDate: String,
    genres: List<GenreModel>,
    seasons: List<SeasonModel>,
    nSeasons: Int,
    networks: List<NetworkModel>,
    providers: WatchProvidersModel?,
    overview: String,
    userVM: UserViewModel,
    videoVM: VideoViewModel,
    navController: NavController,
) {

    val userModelState by userVM.userModelState.collectAsState()

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.Top
    ) {

        ImgPoster(poster_path)

        Column(modifier = Modifier.padding(start = 5.dp)) {
            Title(name)
            ReleaseDate(firstAirDate)
            Genre(genres)
            NumSeasons(nSeasons)
            Provider(providers)
            Network(networks)

            Row(horizontalArrangement = Arrangement.SpaceAround) {
                MakeFavoriteSerie(userVM, idSerie, userModelState)
                MakePendingSerie(userVM, idSerie, userModelState)
                MakeViewSerie(userVM, idSerie, userModelState)
            }
        }
    }

    Sinopsis(overview)

    SeasonContent(idSerie, typeModel, seasons, userModelState, videoVM, userVM, navController)

}




@Composable
private fun MakeFavoriteSerie(
    userVM: UserViewModel,
    idSerie: String,
    userModelState: UserModel
) {
    IconButton(
        onClick = {
            if (userVM.userModelState.value.userDetails.favoriteSerie.contains(idSerie)) {
                userVM.onEvent(UserEvent.RemoveFavoriteSerie(idSerie))
            } else {
                userVM.onEvent(UserEvent.AddFavoriteSerie(idSerie))
            }
        },
        modifier = Modifier.padding(bottom = 8.dp)
    ) {
        //TODO: cambiar a: userSongList
        if (userModelState.userDetails.favoriteSerie.contains(idSerie)) {
            Icon(imageVector = Icons.Outlined.Favorite, contentDescription = null)
        } else {
            Icon(imageVector = Icons.TwoTone.Favorite, contentDescription = null)
        }
    }
}

@Composable
private fun MakePendingSerie(
    userVM: UserViewModel,
    idSerie: String,
    userModelState: UserModel
) {
    IconButton(
        onClick = {
            if (userVM.userModelState.value.userDetails.pendingSerie.contains(idSerie)) {
                userVM.onEvent(UserEvent.RemovePendingSerie(idSerie))
            } else {
                userVM.onEvent(UserEvent.AddPendingSerie(idSerie))
            }
        },
        modifier = Modifier.padding(bottom = 8.dp)
    ) {
        if (userModelState.userDetails.pendingSerie.contains(idSerie)) {
            Icon(painter = painterResource(R.drawable.ic_playlist_check), contentDescription = null)
        } else {
            Icon(painter = painterResource(R.drawable.ic_playlist_add), contentDescription = null)
        }
    }
}

@Composable
private fun MakeViewSerie(
    userVM: UserViewModel,
    idSerie: String,
    userModelState: UserModel
) {
    IconButton(
        onClick = {
            if (userVM.userModelState.value.userDetails.viewSerie.contains(idSerie)) {
                userVM.onEvent(UserEvent.RemoveViewSerie(idSerie))
            } else {
                userVM.onEvent(UserEvent.AddViewSerie(idSerie))
            }
        },
        modifier = Modifier.padding(bottom = 8.dp)
    ) {
        if (userModelState.userDetails.viewSerie.contains(idSerie)) {
            Icon(painter = painterResource(R.drawable.ic_visibility), contentDescription = null)
        } else {
            Icon(painter = painterResource(R.drawable.ic_visibility_off), contentDescription = null)
        }
    }
}
