package com.example.gestordepeliculas.ui.elements.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.gestordepeliculas.domain.model.HomeCategory
import com.example.gestordepeliculas.ui.state.home.HomeViewModel
import com.example.gestordepeliculas.ui.state.movie.MovieSerieViewModel
import com.example.gestordepeliculas.ui.state.user.UserViewModel

@Composable
fun Home3ContentLazyCol(
    titleCategory: List<Pair<HomeCategory, String>>,
    navController: NavController,
    movieSerieVM: MovieSerieViewModel,
    userVM: UserViewModel,
    homeVM: HomeViewModel,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(30.dp),
        horizontalAlignment = Alignment.Start,
    ) {
        items(titleCategory) { (key, title) ->
            Home4CategoryRow(navController, userVM, homeVM, key, title)
        }
    }
}