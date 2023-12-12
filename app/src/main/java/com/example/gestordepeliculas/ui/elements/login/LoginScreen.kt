package com.example.gestordepeliculas.ui.elements.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.gestordepeliculas.R
import com.example.gestordepeliculas.ui.nav.Destination
import com.example.gestordepeliculas.ui.state.login.LoginViewModel
import com.example.gestordepeliculas.ui.state.user.UserEvent
import com.example.gestordepeliculas.ui.state.user.UserViewModel

@Composable
fun LoginScreen(
    navController: NavController,
    userVM: UserViewModel,
    loginVM: LoginViewModel,
) {

    val uiState = loginVM.uiLoginState.collectAsState()
    val loginState = loginVM.loginStateVM.collectAsState()
    val userModelState = userVM.userModelState.collectAsState()
    val userState = userVM.userStateVM.collectAsState()

    userVM.onEvent(UserEvent.UserUpdate)

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        if (userModelState.value.onLogged) {
            Text(text = "Inicio de sesión correcto")
            Text(text = stringResource(R.string.welcome))
            navController.navigate(Destination.Home_Screen.route) {
                popUpTo(0) {
                    inclusive = true
                }
            }
        }

        if (loginState.value.isLoading || userState.value.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            userVM.onEvent(UserEvent.UserUpdate) //TODO: Cuidado quizas falle si el tiempo de carga es mayor. en ese caso utilizar el LoginCompleted

        } else if (!userModelState.value.onLogged) {
            LoginBody(uiState, loginVM, loginState, navController)

        }

    }

}
