package com.example.gestordepeliculas.ui.state

data class StateVM (
    val isLoading: Boolean = false,
    val isSuccessful: Boolean = false,
    val errorException: Exception? = null,
)