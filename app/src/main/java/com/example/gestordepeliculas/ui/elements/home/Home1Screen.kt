package com.example.gestordepeliculas.ui.elements.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.gestordepeliculas.ui.elements.scaffolds.tabscaffold.TabItems
import com.example.gestordepeliculas.ui.elements.scaffolds.tabscaffold.TabScaffold
import com.example.gestordepeliculas.ui.state.home.HomeViewModel
import com.example.gestordepeliculas.ui.state.movie.MovieSerieViewModel
import com.example.gestordepeliculas.ui.state.user.UserViewModel

@Composable
fun Home1Screen(
    navController: NavController,
    userVM: UserViewModel,
    movieSerieVM: MovieSerieViewModel,
    homeVM: HomeViewModel,
) {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TabScaffold(
            navController = navController,
            movieSerieVM = movieSerieVM,
            tabItems = TabItems.values().toList(),
            content = { tabItems, indexSelected, navController, movieSerieVM ->
                Home2Content(tabItems, indexSelected, navController, userVM, movieSerieVM, homeVM)
            }
        )
    }

}