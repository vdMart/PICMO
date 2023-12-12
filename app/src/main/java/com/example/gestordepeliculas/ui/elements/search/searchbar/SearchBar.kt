package com.example.gestordepeliculas.ui.elements.search.searchbar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.gestordepeliculas.ui.state.movie.MovieSerieViewModel

@Composable
fun HeaderSearchBar(
    movieSerieVM: MovieSerieViewModel,
    navController: NavController,
    query: String,
    queryOnChange: (String) -> Unit,
) {
    var context = LocalContext.current
    var active by rememberSaveable() { mutableStateOf(false) }
    SearchBarHeader(navController, movieSerieVM, query, queryOnChange, active, { active=it })
}