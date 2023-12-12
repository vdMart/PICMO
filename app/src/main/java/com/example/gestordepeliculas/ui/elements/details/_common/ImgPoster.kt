package com.example.gestordepeliculas.ui.elements.details._common

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun ImgPoster(posterPath: String) {
    AsyncImage(
        model = "https://image.tmdb.org/t/p/w500${posterPath}",
        contentDescription = null,
        modifier = Modifier
            .height(300.dp)
            .width(200.dp)
    )
}
