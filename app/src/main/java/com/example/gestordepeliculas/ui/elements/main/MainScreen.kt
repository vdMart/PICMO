package com.example.gestordepeliculas.ui.elements.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import com.example.gestordepeliculas.ui.state.user.UserViewModel

@Composable
fun MainScreen(
    navController: NavController,
    userVM: UserViewModel
) {

    val userModelState = userVM.userModelState.collectAsState()

    if (userModelState.value.onLogged) {
        MainLogged(navController)
    } else {
        MainUnLogged(navController)
    }

}
