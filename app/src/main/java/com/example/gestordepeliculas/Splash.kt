package com.example.gestordepeliculas

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.core.splashscreen.SplashScreen
import com.example.gestordepeliculas.ui.state.user.UserEvent
import com.example.gestordepeliculas.ui.state.user.UserViewModel

@Composable
fun Splash(screenSplash: SplashScreen, userVM: UserViewModel) {

    userVM.onEvent(UserEvent.UserUpdate)

    val userState = userVM.userStateVM.collectAsState()
    //val userModel = userVM.userModelState.collectAsState()
    //val movieSerie = movieSerieVM.movieSerieStateVM.collectAsState()

    screenSplash.setKeepOnScreenCondition {
        userState.value.isLoading
        /*
        if(!userState.value.isLoading) {
            if(userModel.value.onLogged) {
                movieSerieVM.onEvent(MovieSerieEvent.GetListMovie)
                movieSerieVM.onEvent(MovieSerieEvent.GetListSerie)
                movieSerie.value.isLoading
            }else { false }
        }else { true }
         */
    }

}