package com.example.gestordepeliculas.ui.elements.scaffolds.tabscaffold

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.gestordepeliculas.ui.state.movie.MovieSerieViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabPage(
    navController: NavController,
    movieSerieVM: MovieSerieViewModel,
    tabItems: List<TabItems>,
    selectedTabIndex: Int,
    function: (Int) -> Unit,
    content: @Composable (List<TabItems>, Int, NavController, MovieSerieViewModel) -> Unit
) {

    val pageState = rememberPagerState{ tabItems.size }

    LaunchedEffect(key1 = selectedTabIndex) {
        pageState.animateScrollToPage(selectedTabIndex)
    }
    LaunchedEffect(key1 = pageState.currentPage, pageState.isScrollInProgress) {
        if (!pageState.isScrollInProgress) {
            function(pageState.currentPage)
        }
    }

    HorizontalPager(
        state = pageState,
        modifier = Modifier.fillMaxSize()
    ){indexSelected ->
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            content(tabItems, indexSelected, navController, movieSerieVM)
        }
    }

}