package com.example.gestordepeliculas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.gestordepeliculas.ui.elements.videoplayer.VideoViewModel
import com.example.gestordepeliculas.ui.nav.NavigationHost
import com.example.gestordepeliculas.ui.state.details.DetailsViewModel
import com.example.gestordepeliculas.ui.state.download.DownloadViewModel
import com.example.gestordepeliculas.ui.state.home.HomeViewModel
import com.example.gestordepeliculas.ui.state.login.LoginViewModel
import com.example.gestordepeliculas.ui.state.movie.MovieSerieViewModel
import com.example.gestordepeliculas.ui.state.register.RegisterViewModel
import com.example.gestordepeliculas.ui.state.user.UserViewModel
import com.example.gestordepeliculas.ui.theme.GestorDePeliculasTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val screenSplash = installSplashScreen()
        super.onCreate(savedInstanceState)

        setContent {
            MyApp(screenSplash)
        }
    }

}

@Composable
private fun MyApp(screenSplash: SplashScreen) {
    val videoVM = hiltViewModel<VideoViewModel>()
    val userVM: UserViewModel = hiltViewModel<UserViewModel>()
    val registerVM: RegisterViewModel = hiltViewModel<RegisterViewModel>()
    val loginVM: LoginViewModel = hiltViewModel<LoginViewModel>()
    val movieSerieVM: MovieSerieViewModel = hiltViewModel<MovieSerieViewModel>()
    val homeVM: HomeViewModel = hiltViewModel<HomeViewModel>()
    val detailsVM: DetailsViewModel = hiltViewModel<DetailsViewModel>()
    val downloadVM: DownloadViewModel = hiltViewModel<DownloadViewModel>()

    Splash(screenSplash, userVM)

    MyApplication(userVM, loginVM, registerVM, movieSerieVM, homeVM, detailsVM, videoVM, downloadVM)
}

@Composable
private fun MyApplication(
    userVM: UserViewModel,
    loginVM: LoginViewModel,
    registerVM: RegisterViewModel,
    movieSerieVM: MovieSerieViewModel,
    homeVM: HomeViewModel,
    detailsVM: DetailsViewModel,
    videoVM: VideoViewModel,
    downloadVM: DownloadViewModel,
) {

    val userModel by userVM.userModelState.collectAsState()

    GestorDePeliculasTheme(typeTheme = userModel.userDetails.theme) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            NavigationHost(userVM, loginVM, registerVM, movieSerieVM, homeVM, detailsVM, videoVM, downloadVM)
        }
    }

}