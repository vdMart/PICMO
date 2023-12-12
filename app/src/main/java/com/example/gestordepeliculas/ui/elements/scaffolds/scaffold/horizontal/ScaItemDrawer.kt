package com.example.gestordepeliculas.ui.elements.scaffolds.scaffold.horizontal

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import coil.compose.AsyncImage
import com.example.gestordepeliculas.R
import com.example.gestordepeliculas.ui.nav.Destination
import com.example.gestordepeliculas.ui.state.user.UserViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun ScaItemDrawer(drawerState: DrawerState, scope: CoroutineScope, navController: NavController, userVM: UserViewModel,) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val userState = userVM.userModelState.collectAsState()

    Spacer(Modifier.height(10.dp))
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                scope.launch { drawerState.close() }
                navController.navigate(route = Destination.Profile_Screen.route)
            },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = userState.value.profilePictureUrl,
            contentDescription = "Profile picture",
            modifier = Modifier
                .size(150.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Text(text = "nik: ${userState.value.nickname}")
        Text(text = "email: ${userState.value.email}")
    }

    Spacer(Modifier.height(10.dp))
    NavigationDrawerItem(
        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding),
        selected = currentDestination?.hierarchy?.any { it.route == Destination.Home_Screen.route } == true,
        onClick = {
            scope.launch { drawerState.close() }
            navController.navigate(route = Destination.Home_Screen.route)
        },
        icon = { Icon(imageVector = Icons.Default.Home, contentDescription = null) },
        label = { Text(text = "Página Principal") },
    )

    Spacer(Modifier.height(10.dp))
    NavigationDrawerItem(
        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding),
        selected = currentDestination?.hierarchy?.any { it.route == Destination.Download_Screen.route } == true,
        onClick = {
            scope.launch { drawerState.close() }
            navController.navigate(route = Destination.Download_Screen.route)
        },
        icon = { Icon(imageVector = ImageVector.vectorResource(id = R.drawable.ic_download), contentDescription = null, modifier = Modifier.size(24.dp)) },
        label = { Text(text = "Descargas") },
    )

    Spacer(Modifier.height(10.dp))
    NavigationDrawerItem(
        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding),
        selected = currentDestination?.hierarchy?.any { it.route == Destination.Settings_Screen.route } == true,
        onClick = {
            scope.launch { drawerState.close() }
            navController.navigate(route = Destination.Settings_Screen.route)
        },
        icon = { Icon(imageVector = Icons.Default.Settings, contentDescription = null) },
        label = { Text(text = "Ajustes") },
    )
}