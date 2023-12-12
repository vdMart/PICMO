package com.example.gestordepeliculas.ui.elements.details._common

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.graphicsLayer
import coil.compose.AsyncImage

@Composable
fun ImgHeader(backdropPath: String) {

    Row(modifier = Modifier
        .fadingEdge()
        .fillMaxWidth()
    ) {
        AsyncImage(
            model = "https://image.tmdb.org/t/p/w500${backdropPath}",
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
        )
    }

}

fun Modifier.fadingEdge(): Modifier {
    val topFade = Brush.verticalGradient(0f to Color.Transparent, 0.3f to Color.Red)
    val bottomFade = Brush.verticalGradient(
        0f to Color.Transparent,
        0f to Color.Red,
        0.7f to Color.Red,
        1f to Color.Transparent
    )
    val topBottomFade = Brush.verticalGradient(0f to Color.Transparent, 0.3f to Color.Red, 0.7f to Color.Red, 1f to Color.Transparent)
    val leftRightFade = Brush.horizontalGradient(0f to Color.Transparent, 0.1f to Color.Red, 0.9f to Color.Red, 1f to Color.Transparent)
    val radialFade = Brush.radialGradient(0f to Color.Red, 0.5f to Color.Transparent)

    return this
        .graphicsLayer(compositingStrategy = CompositingStrategy.Offscreen)
        .drawWithContent {
            drawContent()
            drawRect(brush = bottomFade, blendMode = BlendMode.DstIn)
        }
}