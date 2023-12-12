package com.example.gestordepeliculas.ui.elements.scaffolds.scaffold.vertical

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.gestordepeliculas.R
import com.example.gestordepeliculas.ui.nav.Destination

@Composable
fun ScaBottomBar(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        tonalElevation = 3.0.dp,
    ) {
        NavigationBarItem(
            selected = currentDestination?.hierarchy?.any { it.route == Destination.Home_Screen.route } == true,
            onClick = { navController.navigate(route = Destination.Home_Screen.route) },
            icon = {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                    Text(
                        modifier = Modifier.padding(start = 4.dp),
                        fontSize = 10.sp,
                        textAlign = TextAlign.Center,
                        text = "Principal"
                    )
                }
            },


            )
        NavigationBarItem(
            selected = currentDestination?.hierarchy?.any { it.route == Destination.Settings_Screen.route } == true,
            onClick = { navController.navigate(route = Destination.Settings_Screen.route) },
            icon = {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                    Text(
                        modifier = Modifier.padding(start = 4.dp),
                        fontSize = 10.sp,
                        textAlign = TextAlign.Center,
                        text = "Ajustes"
                    )
                }
            }
        )
        NavigationBarItem(
            selected = currentDestination?.hierarchy?.any { it.route == Destination.Download_Screen.route } == true,
            onClick = { navController.navigate(route = Destination.Download_Screen.route) },
            icon = {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_download),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                    Text(
                        modifier = Modifier.padding(start = 4.dp),
                        fontSize = 10.sp,
                        textAlign = TextAlign.Center,
                        text = "Descargas"
                    )
                }
            }
        )
    }
}