package com.example.gestordepeliculas.ui.elements.search.searchbar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.gestordepeliculas.domain.model.movieserie.movieseriepersonModel
import com.example.gestordepeliculas.ui.elements.scaffolds.tabscaffold.TabItems
import com.example.gestordepeliculas.ui.elements.search.SearchContentBody
import com.example.gestordepeliculas.ui.nav.Destination
import com.example.gestordepeliculas.ui.state.movie.MovieSerieEvent
import com.example.gestordepeliculas.ui.state.movie.MovieSerieViewModel

@Composable
fun SearchBarContent(
    navController: NavController,
    movieSerieVM: MovieSerieViewModel,
    query: String,
) {
    if (query.isNotEmpty()) {
        movieSerieVM.onEvent(MovieSerieEvent.GetFindMovie(query))
        movieSerieVM.onEvent(MovieSerieEvent.GetFindSerie(query))
        SearchBarContentBody(
            (movieSerieVM.movieList + movieSerieVM.serieList).sortedBy { it.id },
            navController
        )
    } else {
        Text(text = "No se ha encontrado información")
    }
}

@Composable
fun SearchBarContentBody(
    itemsList: List<movieseriepersonModel>,
    navController: NavController,
) {
    LazyColumn() {
        items(items = itemsList) {
            Row(
                modifier = Modifier
                    .padding(2.dp)
                    .fillMaxWidth()
                    .clickable {
                        navController.navigate(
                            Destination.Details_Screen.withArgs(
                                "${it.id}",
                                it.typeModel.getDisplayName()
                            )
                        )
                    },
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    model = "https://image.tmdb.org/t/p/w500${it.photo_path}",
                    contentDescription = null,
                    modifier = Modifier
                        .width(20.dp)
                        .height(30.dp)
                )
                Text(text = it.title_name ?: "")
            }
        }
    }
}
