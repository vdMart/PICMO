package com.example.gestordepeliculas.ui.elements.details._common

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.gestordepeliculas.R
import com.example.gestordepeliculas.ui.elements.details.person.PersonScreen
import com.example.gestordepeliculas.ui.elements.details.movie.MovieScreen
import com.example.gestordepeliculas.ui.elements.details.serie.SerieScreen
import com.example.gestordepeliculas.ui.elements.scaffolds.tabscaffold.TabItems
import com.example.gestordepeliculas.ui.elements.videoplayer.VideoViewModel
import com.example.gestordepeliculas.ui.state.details.DetailsEvent
import com.example.gestordepeliculas.ui.state.details.DetailsViewModel
import com.example.gestordepeliculas.ui.state.download.DownloadViewModel
import com.example.gestordepeliculas.ui.state.home.HomeViewModel
import com.example.gestordepeliculas.ui.state.login.LoginViewModel
import com.example.gestordepeliculas.ui.state.movie.MovieSerieViewModel
import com.example.gestordepeliculas.ui.state.register.RegisterViewModel
import com.example.gestordepeliculas.ui.state.user.UserViewModel

@Composable
fun DetailsScreen(
    id: Int,
    type: String,
    navController: NavController,
    userVM: UserViewModel,
    detailsVM: DetailsViewModel,
    videoVM: VideoViewModel,
) {

    when(type) {
        TabItems.MOVIE.getDisplayName() -> {
            detailsVM.onEvent(DetailsEvent.GetMovieById(id))
            detailsVM.onEvent(DetailsEvent.GetMovieProviderById(id))
            if (detailsVM.movie != null) {
                MovieScreen(movie = detailsVM.movie!!, providers = detailsVM.providers, userVM = userVM, navController = navController, videoVM = videoVM)
            }else {
                Text(text = stringResource(R.string.movie_details_not_found))
            }
        }
        TabItems.SERIE.getDisplayName() -> {
            detailsVM.onEvent(DetailsEvent.GetSerieById(id))
            detailsVM.onEvent(DetailsEvent.GetSerieProviderById(id))
            if (detailsVM.serie != null) {
                SerieScreen(serie = detailsVM.serie!!, userVM = userVM, providers = detailsVM.providers, navController = navController, videoVM = videoVM)
            }else {
                Text(text = stringResource(R.string.serie_details_not_found))
            }
        }
        TabItems.PERSON.getDisplayName() -> {
                detailsVM.onEvent(DetailsEvent.GetPersonById(id))
            if (detailsVM.person != null) {
                PersonScreen(person = detailsVM.person!!, userVM = userVM)
            }else {
                Text(text = stringResource(R.string.person_details_not_found))
            }
        }
    }

}
