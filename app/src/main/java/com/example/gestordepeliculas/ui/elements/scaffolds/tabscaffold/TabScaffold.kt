package com.example.gestordepeliculas.ui.elements.scaffolds.tabscaffold

import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import com.example.gestordepeliculas.ui.state.movie.MovieSerieViewModel

@Composable
fun TabScaffold(
    navController: NavController,
    movieSerieVM: MovieSerieViewModel,
    tabItems: List<TabItems>,
    content: @Composable (List<TabItems>, Int, NavController, MovieSerieViewModel) -> Unit
) {

    var selectedTabIndex by rememberSaveable { mutableIntStateOf(0) }

    ScrollableTabRow(selectedTabIndex = selectedTabIndex) {
        tabItems.forEachIndexed { index, item ->
            Tab(
                selected = true,
                onClick = { selectedTabIndex = index },
                text = { Text(text = item.getDisplayName()) },
            )
        }

    }

    TabPage(
        navController = navController,
        movieSerieVM = movieSerieVM,
        tabItems = tabItems,
        selectedTabIndex = selectedTabIndex,
        function = { selectedTabIndex = it },
        content = { tabItems, indexSelected, navController, movieSerieVM ->
            content(tabItems, indexSelected, navController, movieSerieVM)
        }
    )
}