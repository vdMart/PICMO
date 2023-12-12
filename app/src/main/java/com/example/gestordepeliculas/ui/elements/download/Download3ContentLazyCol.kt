package com.example.gestordepeliculas.ui.elements.download

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.gestordepeliculas.domain.model.movieserie.movieseriepersonModel

@Composable
fun Download3ContentLazyCol(
    listMovieSerie: List<movieseriepersonModel>,
    navController: NavController,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        //contentPadding = PaddingValues(5.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        items(items = listMovieSerie) { movieSerie ->
            DownloadItem(
                movieserieperson = movieSerie,
                tabItems = movieSerie.typeModel,
                navController = navController,
            )
            Spacer(modifier = Modifier.height(5.dp))
        }
    }
}
