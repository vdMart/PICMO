package com.example.gestordepeliculas.ui.elements.details._common

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun ImgNetworks(networkPath: String) {
    AsyncImage(
        model = "https://image.tmdb.org/t/p/w500${networkPath}",
        contentDescription = null,
        modifier = Modifier.size(50.dp).padding(end = 10.dp)
    )
}