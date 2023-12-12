package com.example.gestordepeliculas.ui.elements.details._common

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.gestordepeliculas.R
import com.example.gestordepeliculas.domain.model.movieserie.GenreModel
import com.example.gestordepeliculas.domain.model.movieserie.WatchProvidersModel
import com.example.gestordepeliculas.domain.model.movieserie.serie.NetworkModel
import com.example.gestordepeliculas.domain.model.user.UserModel
import com.example.gestordepeliculas.ui.elements.scaffolds.tabscaffold.TabItems
import com.example.gestordepeliculas.ui.elements.videoplayer.VideoViewModel
import com.example.gestordepeliculas.ui.nav.Destination
import com.example.gestordepeliculas.ui.state.user.UserEvent
import com.example.gestordepeliculas.ui.state.user.UserViewModel

@Composable
fun Title(
    title: String,
) {
    Text(
        text = title,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        overflow = TextOverflow.Ellipsis,
        maxLines = 2,
    )
}

@Composable
fun ReleaseDate(
    date: String,
) {
    Text(
        text = date,
        overflow = TextOverflow.Ellipsis,
        maxLines = 1,
    )
}

@Composable
fun Duration(
    duration: Int,
) {
    Text(
        text = StringBuilder().append("$duration").append(stringResource(R.string.abbr_minutes)).toString(),
        overflow = TextOverflow.Ellipsis,
        maxLines = 1,
    )
}

@Composable
fun Genre(
    genres: List<GenreModel>,
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        genres.forEach() {
            Text(
                text = if(it != genres.last()) "${it.name}, " else "${it.name}",
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
            )
        }
    }
}

@Composable
fun Provider(
    providers: WatchProvidersModel?,
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        providers?.rent?.forEach() {
            if(it != null) {
                Text(
                    text = if(it != providers.rent.last()) "${it.provider_name}, " else "${it.provider_name}",
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                )
            }
        }
    }
}

@Composable
fun Network(
    networks: List<NetworkModel>,
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        networks.forEach() {
            ImgNetworks(it.logo_path?:"")
        }
    }
}

@Composable
fun Sinopsis(overview: String) {
    Spacer(modifier = Modifier.height(10.dp))
    Column() {
        Text(
            text = stringResource(R.string.description),
            fontWeight = FontWeight.Bold,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )
        Text(
            text = overview,
            overflow = TextOverflow.Ellipsis,
            maxLines = 10,
        )
    }
}

@Composable
fun NumSeasons(
    nSeasons: Int,
) {
    Text(
        text = "$nSeasons" + stringResource(R.string.temporadas),
        overflow = TextOverflow.Ellipsis,
        maxLines = 1,
    )
}












@Composable
fun BtnPlayFile(
    painter: Painter,
    contentDescription: String? = null,
    textEnabled: String,
    textDisabled: String,
    isEnabled: Boolean,
    action: () -> Unit,
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp)
            .clip(RoundedCornerShape(50.dp))
            .background(Color(0xFFF1F1F1))
            .clickable { action() },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painter,
            contentDescription = contentDescription,
            modifier = Modifier
                .padding(6.dp)
                .size(34.dp),
            tint = Color.Black,
        )
        Text(
            text = if (isEnabled) { textDisabled }else { textEnabled },
            color = Color.Black,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun VideoSelector(
    idMovieSerie: String,
    typeModel: TabItems,
    userModelState: UserModel,
    videoVM: VideoViewModel,
    userVM: UserViewModel,
) {
    val selectVideoLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            when(typeModel) {
                TabItems.MOVIE -> {
                    Log.d("DebugTag", "Debug on: C.VideoSelector,\n  Entro en Movie")
                    userVM.onEvent(UserEvent.AddFileMovie(idMovieSerie, uri?:Uri.EMPTY))
                }
                TabItems.SERIE -> {
                    Log.d("DebugTag", "Debug on: C.VideoSelector,\n  Entro en Serie")
                    userVM.onEvent(UserEvent.AddFileSerie(idMovieSerie, uri?:Uri.EMPTY))
                }
                else -> {  }
            }
        }
    )

    BtnPlayFile(
        painter = painterResource(R.drawable.ic_file_video),
        contentDescription = null,
        textEnabled = "Cambiar fichero local",
        textDisabled = "Agregar fichero local",
        isEnabled = !userModelState.userDetails.movieFile.containsKey(idMovieSerie),
        action = { selectVideoLauncher.launch("video/mp4") },
    )

}

@Composable
fun VideoPlayer(
    idMovieSerie: String,
    stringContent: MutableMap<String, Uri>,
    navController: NavController,
    videoVM: VideoViewModel,
) {
    BtnPlayFile(
        painter = painterResource(R.drawable.ic_play_circle),
        contentDescription = null,
        textEnabled = "Reproducir",
        textDisabled = "Reprodución no disponible",
        isEnabled = !stringContent.containsKey(idMovieSerie),
        action = {
            //Log.d("DebugTag", "Debug on: REPRODUCTOR.VideoPlayer,\n  uri: ${uriContent} condicion: ${uriContent != Uri.EMPTY}")
            if (stringContent.containsKey(idMovieSerie)) {
                videoVM.playVideo(Uri.parse(stringContent[idMovieSerie].toString()) ?: Uri.EMPTY)
                //uriContent.let(videoVM::addVideoUri)
                navController.navigate(Destination.Videoplayer_Screen.route)
            }
        },
    )
}


