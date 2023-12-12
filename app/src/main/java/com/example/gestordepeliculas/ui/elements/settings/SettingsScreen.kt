package com.example.gestordepeliculas.ui.elements.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.gestordepeliculas.ui.elements.videoplayer.VideoViewModel
import com.example.gestordepeliculas.ui.nav.Destination
import com.example.gestordepeliculas.ui.state.details.DetailsViewModel
import com.example.gestordepeliculas.ui.state.download.DownloadViewModel
import com.example.gestordepeliculas.ui.state.home.HomeViewModel
import com.example.gestordepeliculas.ui.state.login.LoginViewModel
import com.example.gestordepeliculas.ui.state.movie.MovieSerieViewModel
import com.example.gestordepeliculas.ui.state.register.RegisterViewModel
import com.example.gestordepeliculas.ui.state.user.UserViewModel

@Composable
fun SettingsScreen(
    navController: NavController,
    userVM: UserViewModel,
) {

    var checked by rememberSaveable { mutableStateOf(true) }

    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Top) {
        ListaDesplegable(userVM)
    }


}