package com.example.gestordepeliculas.ui.elements.scaffolds.scaffold.vertical

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.gestordepeliculas.ui.state.user.UserViewModel

@Composable
fun VerticalScaffold(
    navController: NavController,
    userVM: UserViewModel,
    Contenido: @Composable () -> Unit
) {
    BarFAB(navController, Contenido)
}