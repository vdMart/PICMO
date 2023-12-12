package com.example.gestordepeliculas.ui.elements.scaffolds.scaffold.horizontal

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.gestordepeliculas.ui.state.user.UserViewModel

@Composable
fun HorizontalScaffold(
    navController: NavController,
    userVM: UserViewModel,
    Contenido: @Composable () -> Unit
) {
    ScaDrawer(navController, userVM, Contenido)
}