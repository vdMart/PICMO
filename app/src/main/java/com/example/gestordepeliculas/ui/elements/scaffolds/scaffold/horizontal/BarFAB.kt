package com.example.gestordepeliculas.ui.elements.scaffolds.scaffold.horizontal

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import kotlinx.coroutines.CoroutineScope

@Composable
fun BarFAB(scope: CoroutineScope, drawerState: DrawerState, navController: NavController, Contenido: @Composable () -> Unit){
    Scaffold(
        topBar = { ScaTopBar(drawerState, scope, navController) },
    ) {
        Surface(modifier = Modifier
            .padding(it)
            .fillMaxSize()) {
            Contenido()
        }
    }
}