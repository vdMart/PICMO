package com.example.gestordepeliculas.ui.elements.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.gestordepeliculas.R
import com.example.gestordepeliculas.domain.model.HomeCategoryMovie
import com.example.gestordepeliculas.domain.model.HomeCategoryPerson
import com.example.gestordepeliculas.domain.model.HomeCategorySerie
import com.example.gestordepeliculas.domain.model.HomeCategorySerieMovie
import com.example.gestordepeliculas.ui.elements.scaffolds.tabscaffold.TabItems
import com.example.gestordepeliculas.ui.state.home.HomeViewModel
import com.example.gestordepeliculas.ui.state.movie.MovieSerieViewModel
import com.example.gestordepeliculas.ui.state.user.UserViewModel

@Composable
fun Home2Content(
    tabItems: List<TabItems>,
    indexSelected: Int,
    navController: NavController,
    userVM: UserViewModel,
    movieSerieVM: MovieSerieViewModel,
    homeVM: HomeViewModel,
) {
    when(tabItems[indexSelected]) {
        TabItems.ALL -> {
            val titleCategoryMovieSerie = mapOf(
                HomeCategorySerieMovie.SerieMovieDiscover to stringResource(R.string.title_category_seriemovie_discover),
                HomeCategorySerieMovie.SerieMovieAgain to stringResource(R.string.title_category_seriemovie_again),
                HomeCategorySerieMovie.SerieMovieFavorite to stringResource(R.string.title_category_seriemovie_favorite),
                HomeCategorySerieMovie.SerieMoviePending to stringResource(R.string.title_category_seriemovie_pending),
            ).toList()
            Home3ContentLazyCol(titleCategoryMovieSerie, navController, movieSerieVM, userVM, homeVM)
        }
        TabItems.MOVIE -> {
            val titleCategoryMovie = mapOf(
                HomeCategoryMovie.MovieDiscover to stringResource(R.string.title_category_movie_discover),
                HomeCategoryMovie.MovieAgain to stringResource(R.string.title_category_movie_again),
                HomeCategoryMovie.MovieFavorite to stringResource(R.string.title_category_movie_favorite),
                HomeCategoryMovie.MoviePending to stringResource(R.string.title_category_movie_pending),
            ).toList()
            Home3ContentLazyCol(titleCategoryMovie, navController, movieSerieVM, userVM, homeVM)
        }
        TabItems.SERIE -> {
            val titleCategorySerie = mapOf(
                HomeCategorySerie.SerieDiscover to stringResource(R.string.title_category_serie_discover),
                HomeCategorySerie.SerieAgain to stringResource(R.string.title_category_serie_again),
                HomeCategorySerie.SerieFavorite to stringResource(R.string.title_category_serie_favorite),
                HomeCategorySerie.SeriePending to stringResource(R.string.title_category_serie_pending),
            ).toList()
            Home3ContentLazyCol(titleCategorySerie, navController, movieSerieVM, userVM, homeVM)
        }
        TabItems.PERSON -> {
            val titleCategoryPerson = mapOf( /*TODO: Adaptar a Personas*/
                HomeCategoryPerson.PersonDirector to stringResource(R.string.directores),
                HomeCategoryPerson.PersonActor to stringResource(R.string.actores),
                HomeCategoryPerson.PersonDirectorFavorite to stringResource(R.string.directores_favoritos),
                HomeCategoryPerson.PersonActorFavorite to stringResource(R.string.actores_favoritos),
            ).toList()
            Home3ContentLazyCol(titleCategoryPerson, navController, movieSerieVM, userVM, homeVM)
        }
    }
}