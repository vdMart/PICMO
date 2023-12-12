package com.example.gestordepeliculas.ui.elements.details.movie

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
import com.example.gestordepeliculas.domain.model.movieserie.movie.MovieModel
import com.example.gestordepeliculas.ui.elements.videoplayer.VideoViewModel
import com.example.gestordepeliculas.ui.state.user.UserViewModel

@Composable
fun MovieScreen(
    movie: MovieModel,
    providers: WatchProvidersModel?,
    userVM: UserViewModel,
    navController: NavController,
    videoVM: VideoViewModel,
) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        item {
            MovieDetails(
                idMovie = movie.id.toString(),
                title = movie.title ?: "",
                typeModel = movie.typeModel,
                photo_path = movie.photo_path ?: "",
                releaseDate = movie.release_date ?: "",
                runtime = movie.runtime ?: 0,
                genres = movie.genres,
                providers = providers,
                overview = movie.overview ?: "",
                userVM = userVM,
                navController = navController,
                videoVM = videoVM,
            )
        }
    }

}
