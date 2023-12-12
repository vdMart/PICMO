package com.example.gestordepeliculas.utils

import android.util.Patterns
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntSize
import com.example.gestordepeliculas.domain.model.movieserie.movie.MovieModel
import com.example.gestordepeliculas.domain.model.movieserie.movieseriepersonModel
import com.example.gestordepeliculas.ui.state.details.DetailsEvent
import com.example.gestordepeliculas.ui.state.details.DetailsViewModel

private const val MIN_SIZE_PASSWORD = 5
private const val MIN_SIZE_NICKNAME = 3
fun String.isValidPassword() = this.length > MIN_SIZE_PASSWORD
fun String.isValidEmail() = Patterns.EMAIL_ADDRESS.matcher(this).matches()
fun String.isValidNickName() = this.length > MIN_SIZE_NICKNAME

fun Modifier.shimmerEffect(): Modifier = composed {
    var size by remember {
        mutableStateOf(IntSize.Zero)
    }
    val transition = rememberInfiniteTransition()
    val startOffsetX by transition.animateFloat(
        initialValue = -2 * size.width.toFloat(),
        targetValue = 2 * size.width.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(1000)
        )
    )

    background(
        brush = Brush.linearGradient(
            colors = listOf(
                Color(0xFFB8B5B5),
                Color(0xFF8F8B8B),
                Color(0xFFB8B5B5),
            ),
            start = Offset(startOffsetX, 0f),
            end = Offset(startOffsetX + size.width.toFloat(), size.height.toFloat())
        )
    )
        .onGloballyPositioned {
            size = it.size
        }
}