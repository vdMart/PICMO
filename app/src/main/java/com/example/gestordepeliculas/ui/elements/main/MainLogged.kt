package com.example.gestordepeliculas.ui.elements.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.gestordepeliculas.R
import com.example.gestordepeliculas.ui.nav.Destination

@Composable
fun MainLogged(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = "Inicio de sesión correcto")
        Text(text = stringResource(R.string.welcome))
        navController.navigate(Destination.Home_Screen.route) {
            popUpTo(0) {
                inclusive = true
            }
        }
    }
}