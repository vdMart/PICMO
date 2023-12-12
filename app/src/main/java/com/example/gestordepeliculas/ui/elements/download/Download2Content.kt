package com.example.gestordepeliculas.ui.elements.download

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import com.example.gestordepeliculas.ui.elements.scaffolds.tabscaffold.TabItems
import com.example.gestordepeliculas.ui.state.download.DownloadEvent
import com.example.gestordepeliculas.ui.state.download.DownloadViewModel
import com.example.gestordepeliculas.ui.state.movie.MovieSerieViewModel
import com.example.gestordepeliculas.ui.state.user.UserViewModel

@Composable
fun Download2Content(
    tabItems: List<TabItems>,
    indexSelected: Int,
    navController: NavController,
    userVM: UserViewModel,
    movieSerieVM: MovieSerieViewModel,
    downloadVM: DownloadViewModel,
) {
    val userModelState by userVM.userModelState.collectAsState()
    var movieMap = userModelState.userDetails.movieFile.keys.toList()
    var serieMap = userModelState.userDetails.movieFile.keys.toList()

    when(tabItems[indexSelected]) {
        TabItems.ALL -> {
            downloadVM.onEvent(DownloadEvent.GetMoviesById(movieMap.map { it.toInt() }))
            downloadVM.onEvent(DownloadEvent.GetSeriesById(serieMap.map { it.toInt() }))
            Download3ContentLazyCol(downloadVM.listMovie + downloadVM.listSerie, navController)

        }
        TabItems.MOVIE -> {
            downloadVM.onEvent(DownloadEvent.GetMoviesById(movieMap.map { it.toInt() }))
            Download3ContentLazyCol(downloadVM.listMovie, navController)

        }
        TabItems.SERIE -> {
            downloadVM.onEvent(DownloadEvent.GetSeriesById(serieMap.map { it.toInt() }))
            Download3ContentLazyCol(downloadVM.listSerie, navController)
        }
        else -> {}
    }

}
