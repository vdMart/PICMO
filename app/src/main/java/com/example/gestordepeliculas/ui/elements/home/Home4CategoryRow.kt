package com.example.gestordepeliculas.ui.elements.home

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.gestordepeliculas.domain.model.HomeCategory
import com.example.gestordepeliculas.domain.model.HomeCategoryMovie
import com.example.gestordepeliculas.domain.model.HomeCategoryPerson
import com.example.gestordepeliculas.domain.model.HomeCategorySerie
import com.example.gestordepeliculas.domain.model.HomeCategorySerieMovie
import com.example.gestordepeliculas.ui.state.home.HomeEvent
import com.example.gestordepeliculas.ui.state.home.HomeViewModel
import com.example.gestordepeliculas.ui.state.user.UserViewModel

@Composable
fun Home4CategoryRow(
    navController: NavController,
    userVM: UserViewModel,
    homeVM: HomeViewModel,
    homeCategory: HomeCategory,
    titleCategory: String,
) {
    HomeCategoryLineTitle(titleCategory)

    when(homeCategory) {

        /* Movie & Serie */
        is HomeCategorySerieMovie.SerieMovieDiscover -> {
            homeVM.onEvent(HomeEvent.GetListMoviesDiscover)
            homeVM.onEvent(HomeEvent.GetListSeriesDiscover)

            var lista = (homeVM.listMovieSeriePerson[HomeCategorySerie.SerieDiscover] ?: emptyList()) + (homeVM.listMovieSeriePerson[HomeCategoryMovie.MovieDiscover] ?: emptyList())

            Home5CategoryLazyRow(navController, lista)
        }
        is HomeCategorySerieMovie.SerieMovieFavorite -> {
            val userModelState by userVM.userModelState.collectAsState()

            homeVM.onEvent(HomeEvent.GetMoviesById((userModelState.userDetails.favoriteMovie).map { it.toInt() }, HomeCategoryMovie.MovieFavorite))
            homeVM.onEvent(HomeEvent.GetSeriesById((userModelState.userDetails.favoriteSerie).map { it.toInt() }, HomeCategorySerie.SerieFavorite))

            var lista = (homeVM.listMovieSeriePerson[HomeCategorySerie.SerieFavorite] ?: emptyList()) +
                        (homeVM.listMovieSeriePerson[HomeCategoryMovie.MovieFavorite] ?: emptyList())

            Home5CategoryLazyRow(navController, lista)
        }
        is HomeCategorySerieMovie.SerieMovieAgain -> {
            val userModelState by userVM.userModelState.collectAsState()

            homeVM.onEvent(HomeEvent.GetMoviesById((userModelState.userDetails.viewMovie).map { it.toInt() }, HomeCategoryMovie.MovieAgain))
            homeVM.onEvent(HomeEvent.GetSeriesById((userModelState.userDetails.viewSerie).map { it.toInt() }, HomeCategorySerie.SerieAgain))

            var lista = (homeVM.listMovieSeriePerson[HomeCategorySerie.SerieAgain] ?: emptyList()) +
                        (homeVM.listMovieSeriePerson[HomeCategoryMovie.MovieAgain] ?: emptyList())

            Home5CategoryLazyRow(navController, lista)
        }
        is HomeCategorySerieMovie.SerieMoviePending -> {
            val userModelState by userVM.userModelState.collectAsState()

            homeVM.onEvent(HomeEvent.GetMoviesById((userModelState.userDetails.pendingMovie).map { it.toInt() }, HomeCategoryMovie.MoviePending))
            homeVM.onEvent(HomeEvent.GetSeriesById((userModelState.userDetails.pendingSerie).map { it.toInt() }, HomeCategorySerie.SeriePending))

            var lista = (homeVM.listMovieSeriePerson[HomeCategorySerie.SeriePending] ?: emptyList()) +
                        (homeVM.listMovieSeriePerson[HomeCategoryMovie.MoviePending] ?: emptyList())

            Home5CategoryLazyRow(navController, lista)
        }

        /* Movie */
        is HomeCategoryMovie.MovieDiscover -> {
            val userModelState by userVM.userModelState.collectAsState()

            homeVM.onEvent(HomeEvent.GetListMoviesDiscover)

            var lista = homeVM.listMovieSeriePerson[homeCategory] ?: emptyList()

            Home5CategoryLazyRow(navController, lista)
        }
        is HomeCategoryMovie.MovieFavorite -> {
            val userModelState by userVM.userModelState.collectAsState()

            homeVM.onEvent(HomeEvent.GetMoviesById((userModelState.userDetails.favoriteMovie).map { it.toInt() }, HomeCategoryMovie.MovieFavorite))

            var lista = homeVM.listMovieSeriePerson[HomeCategoryMovie.MovieFavorite] ?: emptyList()

            Home5CategoryLazyRow(navController, lista)
        }
        is HomeCategoryMovie.MovieAgain -> {
            val userModelState by userVM.userModelState.collectAsState()

            homeVM.onEvent(HomeEvent.GetMoviesById((userModelState.userDetails.viewMovie).map { it.toInt() }, HomeCategoryMovie.MovieAgain))

            var lista = homeVM.listMovieSeriePerson[HomeCategoryMovie.MovieAgain] ?: emptyList()

            Home5CategoryLazyRow(navController, lista)
        }
        is HomeCategoryMovie.MoviePending -> {
            val userModelState by userVM.userModelState.collectAsState()

            homeVM.onEvent(HomeEvent.GetMoviesById((userModelState.userDetails.pendingMovie).map { it.toInt() }, HomeCategoryMovie.MoviePending))

            var lista = homeVM.listMovieSeriePerson[HomeCategoryMovie.MoviePending] ?: emptyList()

            Home5CategoryLazyRow(navController, lista)
        }

        /* Serie */
        is HomeCategorySerie.SerieDiscover -> {
            homeVM.onEvent(HomeEvent.GetListSeriesDiscover)

            var lista = homeVM.listMovieSeriePerson[HomeCategorySerie.SerieDiscover] ?: emptyList()

            Home5CategoryLazyRow(navController, lista)
        }
        is HomeCategorySerie.SerieFavorite -> {
            val userModelState by userVM.userModelState.collectAsState()

            homeVM.onEvent(HomeEvent.GetSeriesById((userModelState.userDetails.favoriteSerie).map { it.toInt() }, HomeCategorySerie.SerieFavorite))

            var lista = homeVM.listMovieSeriePerson[HomeCategorySerie.SerieFavorite] ?: emptyList()

            Home5CategoryLazyRow(navController, lista)
        }
        is HomeCategorySerie.SerieAgain -> {
            val userModelState by userVM.userModelState.collectAsState()

            homeVM.onEvent(HomeEvent.GetSeriesById((userModelState.userDetails.viewSerie).map { it.toInt() }, HomeCategorySerie.SerieAgain))

            var lista = homeVM.listMovieSeriePerson[HomeCategorySerie.SerieAgain] ?: emptyList()

        }
        is HomeCategorySerie.SeriePending -> {
            val userModelState by userVM.userModelState.collectAsState()

            homeVM.onEvent(HomeEvent.GetSeriesById((userModelState.userDetails.pendingSerie).map { it.toInt() }, HomeCategorySerie.SeriePending))

            var lista = homeVM.listMovieSeriePerson[HomeCategorySerie.SeriePending] ?: emptyList()

            Home5CategoryLazyRow(navController, lista)
        }

        /* Person */
        is HomeCategoryPerson.PersonActor -> { //TODO
            homeVM.onEvent(HomeEvent.GetListPersonPopular)

            var lista = homeVM.listMovieSeriePerson[homeCategory] ?: emptyList()

            Home5CategoryLazyRow(navController, lista)
        }
        is HomeCategoryPerson.PersonActorFavorite -> { //TODO
            val userModelState by userVM.userModelState.collectAsState()
            var favoriteActorID: List<String> = userModelState.userDetails.favoritePerson

            Text(text = "- Categoria: ${homeCategory.id} - Lista ID: $favoriteActorID")
            Home5CategoryLazyRow(navController, emptyList())
        }
        is HomeCategoryPerson.PersonDirector -> { //TODO
            homeVM.onEvent(HomeEvent.GetListPersonPopular)

            var lista = homeVM.listMovieSeriePerson[homeCategory] ?: emptyList()

            Home5CategoryLazyRow(navController, lista)
        }
        is HomeCategoryPerson.PersonDirectorFavorite -> { //TODO
            val userModelState by userVM.userModelState.collectAsState()
            var favoriteDirectorID: List<String> = userModelState.userDetails.favoritePerson

            Text(text = "- Categoria: ${homeCategory.id} - Lista ID: $favoriteDirectorID")
            Home5CategoryLazyRow(navController, emptyList())
        }
    }
}

@Composable
private fun HomeCategoryLineTitle(
    titleCategory: String,
) {
    Text(
        text = titleCategory,
        fontSize = 15.sp,
        fontWeight = FontWeight.Bold,
        overflow = TextOverflow.Ellipsis,
        maxLines = 1,
    )
    Spacer(modifier = Modifier.height(5.dp))
}