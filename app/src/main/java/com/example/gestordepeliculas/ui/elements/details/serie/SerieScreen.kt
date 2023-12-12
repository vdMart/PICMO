package com.example.gestordepeliculas.ui.elements.details.serie

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.gestordepeliculas.domain.model.movieserie.WatchProvidersModel
import com.example.gestordepeliculas.domain.model.movieserie.serie.SerieModel
import com.example.gestordepeliculas.ui.elements.videoplayer.VideoViewModel
import com.example.gestordepeliculas.ui.state.user.UserViewModel

@Composable
fun SerieScreen(
    serie: SerieModel,
    userVM: UserViewModel,
    providers: WatchProvidersModel?,
    navController: NavController,
    videoVM: VideoViewModel,
) {

    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(5.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        item {
            SerieDetails(
                idSerie = "${serie.id}",
                name = serie.name ?: "",
                typeModel = serie.typeModel,
                poster_path = serie.poster_path ?: "",
                firstAirDate = serie.first_air_date ?: "",
                genres = serie.genres,
                seasons = serie.seasons,
                nSeasons = serie.number_of_seasons ?: 0,
                networks = serie.networks,
                providers = providers,
                overview = serie.overview ?: "",
                userVM = userVM,
                videoVM = videoVM,
                navController = navController,
            )
        }
    }
}
