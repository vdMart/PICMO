package com.example.gestordepeliculas.ui.elements.scaffolds.scaffold

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalConfiguration
import androidx.navigation.NavController
import com.example.gestordepeliculas.ui.elements.scaffolds.scaffold.horizontal.HorizontalScaffold
import com.example.gestordepeliculas.ui.elements.scaffolds.scaffold.vertical.VerticalScaffold
import com.example.gestordepeliculas.ui.nav.Destination
import com.example.gestordepeliculas.ui.state.user.UserViewModel

@Composable
fun OrientationScreen(
    navController: NavController,
    userVM: UserViewModel,
    Content: @Composable () -> Unit,
) {

    val userModelState = userVM.userModelState.collectAsState()

    if(!userModelState.value.onLogged){
        navController.navigate(Destination.Main_Screen.route) {
            popUpTo(Destination.Main_Screen.route) {
                inclusive = true
            }
        }
    }

    when(LocalConfiguration.current.orientation){
        Configuration.ORIENTATION_LANDSCAPE -> { //Horizontal
            HorizontalScaffold(navController = navController, userVM) {
                Content()
            }
        }
        Configuration.ORIENTATION_PORTRAIT -> { //Vertical
            VerticalScaffold(navController = navController, userVM) {
                Content()
            }
        }
        else -> throw RuntimeException() //opcional
    }

}
