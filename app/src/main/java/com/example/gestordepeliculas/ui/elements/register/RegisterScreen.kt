package com.example.gestordepeliculas.ui.elements.register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.gestordepeliculas.ui.nav.Destination
import com.example.gestordepeliculas.ui.state.register.RegisterViewModel
import com.example.gestordepeliculas.ui.state.user.UserEvent
import com.example.gestordepeliculas.ui.state.user.UserViewModel

@Composable
fun RegisterScreen(
    navController: NavController,
    userVM: UserViewModel,
    registerVM: RegisterViewModel,
) {

    val uiState = registerVM.uiRegisterState.collectAsState()
    val registerState = registerVM.registerStateVM.collectAsState()
    val userModelState = userVM.userModelState.collectAsState()
    val userState = userVM.userStateVM.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        if (userModelState.value.onLogged) {
            Text(text = "Registro correcto", color = Color.Green)
            Text(text = "¡Bienvenido!", color = Color.Green)
            navController.navigate(Destination.Home_Screen.route) {
                popUpTo(0) {
                    inclusive = true
                }
            }
        }

        if (registerState.value.isLoading || userState.value.isLoading) {
            CircularProgressIndicator()
            userVM.onEvent(UserEvent.UserUpdate) //TODO: Cuidado quizas falle si el tiempo de carga es mayor. en ese caso utilizar el LoginCompleted

        } else if (!userModelState.value.onLogged) {
            RegisterBody(registerVM, registerState, uiState, navController)
        }

    }

}
