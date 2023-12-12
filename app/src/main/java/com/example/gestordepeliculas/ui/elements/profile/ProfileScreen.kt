package com.example.gestordepeliculas.ui.elements.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.gestordepeliculas.R
import com.example.gestordepeliculas.ui.state.user.UserEvent
import com.example.gestordepeliculas.ui.state.user.UserViewModel

@Composable
fun ProfileScreen(
    navController: NavController,
    userVM: UserViewModel,
) {
    val userModel = userVM.userModelState.collectAsState()

    Column() {
        Text(text = "Nombre: ${userModel.value.email}")

        Button(onClick = {
            userVM.onEvent(UserEvent.SignOut)
            userVM.onEvent(UserEvent.UserUpdate)
            //navController.navigate(Destination.Login_Screen.route)
        }) {
            Text(text = stringResource(R.string.logout_btn))
        }
    }

}