package com.example.gestordepeliculas.ui.elements.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.gestordepeliculas.ui.elements.scaffolds.tabscaffold.TabItems
import com.example.gestordepeliculas.ui.elements.scaffolds.tabscaffold.TabScaffold
import com.example.gestordepeliculas.ui.elements.search.searchbar.HeaderSearchBar
import com.example.gestordepeliculas.ui.state.movie.MovieSerieViewModel

@Composable
fun SearchScreen(
    navController: NavController,
    movieSerieVM: MovieSerieViewModel,
) {

    var query by rememberSaveable() { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        HeaderSearchBar(movieSerieVM = movieSerieVM, navController = navController, query, { query=it })
        TabScaffold(
            navController = navController,
            movieSerieVM = movieSerieVM,
            tabItems = TabItems.values().toList(),
            content = { tabItems, index, navController, movieSerieVM ->
                SearchContent(tabItems, index, navController, movieSerieVM, query)
            }
        )
    }

}
