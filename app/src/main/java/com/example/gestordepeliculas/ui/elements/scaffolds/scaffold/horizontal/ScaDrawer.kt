package com.example.gestordepeliculas.ui.elements.scaffolds.scaffold.horizontal

import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavController
import com.example.gestordepeliculas.ui.state.user.UserViewModel

@Composable
fun ScaDrawer(
    navController: NavController,
    userVM: UserViewModel,
    Contenido: @Composable () -> Unit
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = { ModalDrawerSheet { ScaItemDrawer(drawerState, scope, navController, userVM) } },
        content = { BarFAB(scope, drawerState, navController, Contenido) }
    )
}