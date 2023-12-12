package com.example.gestordepeliculas.ui.state

sealed class ResultVM<out T> {
    data class Success<out T>(val data: T) : ResultVM<T>()
    data class Error(val exception: Exception) : ResultVM<Nothing>()
}