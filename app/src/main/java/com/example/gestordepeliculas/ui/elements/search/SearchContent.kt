package com.example.gestordepeliculas.ui.elements.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.gestordepeliculas.domain.model.movieserie.movieseriepersonModel
import com.example.gestordepeliculas.ui.elements.scaffolds.tabscaffold.TabItems
import com.example.gestordepeliculas.ui.nav.Destination
import com.example.gestordepeliculas.ui.state.movie.MovieSerieEvent
import com.example.gestordepeliculas.ui.state.movie.MovieSerieViewModel

@Composable
fun SearchContent(
    tabItems: List<TabItems>,
    indexSelected: Int,
    navController: NavController,
    movieSerieVM: MovieSerieViewModel,
    query: String,
) {

    if (query.isNotEmpty()) {
        when(tabItems[indexSelected]){
            TabItems.ALL -> {
                movieSerieVM.onEvent(MovieSerieEvent.GetFindMovie(query))
                movieSerieVM.onEvent(MovieSerieEvent.GetFindSerie(query))
                SearchContentBody((movieSerieVM.movieList + movieSerieVM.serieList).sortedBy { it.id }, navController)
            }
            TabItems.MOVIE -> {
                movieSerieVM.onEvent(MovieSerieEvent.GetFindMovie(query))
                SearchContentBody(movieSerieVM.movieList, navController)
            }
            TabItems.SERIE -> {
                movieSerieVM.onEvent(MovieSerieEvent.GetFindSerie(query))
                SearchContentBody(movieSerieVM.serieList, navController)
            }
            TabItems.PERSON -> {
                movieSerieVM.onEvent(MovieSerieEvent.GetFindPerson(query))
                SearchContentBody(movieSerieVM.personList, navController)
            }
        }
    }else {
        Text(text = "No se ha encontrado información")
    }

}


@Composable
fun SearchContentBody(
    itemsList: List<movieseriepersonModel>,
    navController: NavController,
) {
    LazyVerticalGrid(columns = GridCells.Fixed(3), contentPadding = PaddingValues(4.dp)){
        items(items = itemsList) {
            Row(
                modifier = Modifier
                    .padding(2.dp)
                    .fillMaxSize()
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
                if(it.photo_path.isNullOrEmpty()) {
                    Text(
                        modifier = Modifier.padding(start = 5.dp).height(height = 52.dp).align(Alignment.CenterVertically),
                        text = it.title_name?:"",
                        overflow = TextOverflow.Ellipsis
                    )
                }else {
                    AsyncImage(
                        modifier = Modifier.fillMaxSize(),
                        model = "https://image.tmdb.org/t/p/w500${it.photo_path}",
                        contentDescription = null,
                    )
                }
            }
        }
    }
}