package com.example.gestordepeliculas.ui.state.login

data class LoginUiState(
    val emailState: String = "",
    val passwordState: String = "",
    val passwordVisibilityState: Boolean = false,
)