package com.example.gestordepeliculas.ui.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.gestordepeliculas.ui.elements.details._common.DetailsScreen
import com.example.gestordepeliculas.ui.elements.download.Download1Screen
import com.example.gestordepeliculas.ui.elements.home.Home1Screen
import com.example.gestordepeliculas.ui.elements.login.LoginScreen
import com.example.gestordepeliculas.ui.elements.main.MainScreen
import com.example.gestordepeliculas.ui.elements.profile.ProfileScreen
import com.example.gestordepeliculas.ui.elements.register.RegisterScreen
import com.example.gestordepeliculas.ui.elements.scaffolds.scaffold.OrientationScreen
import com.example.gestordepeliculas.ui.elements.search.SearchScreen
import com.example.gestordepeliculas.ui.elements.settings.SettingsScreen
import com.example.gestordepeliculas.ui.elements.videoplayer.VideoViewModel
import com.example.gestordepeliculas.ui.elements.videoplayer.VideoplayerScreen
import com.example.gestordepeliculas.ui.state.details.DetailsViewModel
import com.example.gestordepeliculas.ui.state.download.DownloadViewModel
import com.example.gestordepeliculas.ui.state.home.HomeViewModel
import com.example.gestordepeliculas.ui.state.login.LoginViewModel
import com.example.gestordepeliculas.ui.state.movie.MovieSerieViewModel
import com.example.gestordepeliculas.ui.state.register.RegisterViewModel
import com.example.gestordepeliculas.ui.state.user.UserViewModel

@Composable
fun NavigationHost(
    userVM: UserViewModel,
    loginVM: LoginViewModel,
    registerVM: RegisterViewModel,
    movieSerieVM: MovieSerieViewModel,
    homeVM: HomeViewModel,
    detailsVM: DetailsViewModel,
    videoVM: VideoViewModel,
    downloadVM: DownloadViewModel,
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Destination.Main_Screen.route) {
        composable(route = Destination.Main_Screen.route) {
            MainScreen(navController, userVM)
        }
        composable(route = Destination.Login_Screen.route) {
            LoginScreen(navController, userVM, loginVM)
        }
        composable(route = Destination.Register_Screen.route) {
            RegisterScreen(navController, userVM, registerVM)
        }
        composable(route = Destination.Home_Screen.route) {
            OrientationScreen(navController, userVM) { Home1Screen(navController, userVM, movieSerieVM, homeVM) }
        }
        composable(
            route = Destination.Details_Screen.route + "/{id}" + "/{type}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                    defaultValue = 0
                    nullable = false
                },
                navArgument("type") {
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = false
                },
            )
        ) {
            OrientationScreen(
                navController = navController,
                userVM = userVM,
                Content = {
                    DetailsScreen(
                        id = it.arguments?.getInt("id")?:0,
                        type = it.arguments?.getString("type")?:"",
                        navController = navController,
                        userVM = userVM,
                        detailsVM = detailsVM,
                        videoVM = videoVM,
                    )
                }
            )
        }

        composable(route = Destination.Settings_Screen.route) {
            OrientationScreen(navController, userVM) { SettingsScreen(navController, userVM) }
        }
        composable(route = Destination.Download_Screen.route) {
            OrientationScreen(navController, userVM) { Download1Screen(navController, userVM, movieSerieVM, downloadVM) }
        }
        composable(route = Destination.Search_Screen.route) {
            OrientationScreen(navController, userVM) { SearchScreen(navController, movieSerieVM) }
        }
        composable(route = Destination.Profile_Screen.route) {
            OrientationScreen(navController, userVM) { ProfileScreen(navController, userVM) }
        }
        composable(route = Destination.Videoplayer_Screen.route) {
            OrientationScreen(navController, userVM) { VideoplayerScreen(videoVM) }
        }
    }

}