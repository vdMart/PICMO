package com.example.gestordepeliculas.ui.elements.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.gestordepeliculas.domain.model.movieserie.movieseriepersonModel

@Composable
fun Home5CategoryLazyRow(
    navController: NavController,
    movieSeriePersonList: List<movieseriepersonModel>,
) {
    LazyRow(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        items(items = movieSeriePersonList.sortedBy { it.title_name }) { movieSerie ->
            Home6Item(
                movieserieperson = movieSerie,
                tabItems = movieSerie.typeModel,
                navController = navController,
            )
        }

    }
}