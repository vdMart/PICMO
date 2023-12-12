package com.example.gestordepeliculas.ui.elements.scaffolds.scaffold.vertical

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.gestordepeliculas.R
import com.example.gestordepeliculas.ui.elements.scaffolds.scaffold.TitleScaffold
import com.example.gestordepeliculas.ui.nav.Destination

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaTopBar(navController: NavController) {
    Surface(tonalElevation = 1.5.dp, shadowElevation = 6.0.dp, modifier = Modifier.height(55.dp)) {
        TopAppBar(
            title = { TitleScaffold() },
            actions = {
                IconButton(modifier = Modifier.size(24.dp), onClick = { /*TODO*/ }) {
                    Icon(painter = painterResource(id = R.drawable.ic_cast), "cast icon")
                }
                Spacer(modifier = Modifier.size(ButtonDefaults.IconSize))
                IconButton(modifier = Modifier.size(24.dp), onClick = { navController.navigate(
                    Destination.Search_Screen.route) }) {
                    Icon(Icons.Filled.Search, "search icon")
                }
                Spacer(modifier = Modifier.size(ButtonDefaults.IconSize))
                IconButton(modifier = Modifier.size(24.dp), onClick = { navController.navigate(
                    Destination.Profile_Screen.route) }) {
                    Icon(Icons.Filled.Person, "photo icon")
                    //AsyncImage(model = R.drawable.person_fill0_wght400_grad0_opsz48 , contentDescription = "foto perfil")
                }
            },
            windowInsets = WindowInsets(2.dp, 15.dp,10.dp, 7.dp)
        )
    }
}