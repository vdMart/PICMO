package com.example.gestordepeliculas.ui.state.register

import com.google.firebase.auth.AuthCredential

data class RegisterUiState(
    val emailState: String = "",
    val passwordState: String = "",
    val repeatPasswordState: String = "",
    val passwordVisibilityState: Boolean = false,
    val repeatPasswordVisibilityState: Boolean = false,
    val credential: AuthCredential? = null,
    val isValidEmail: Boolean = false,
    val isValidPassword: Boolean = false,
    val isValidRepeatPassword: Boolean = false,
    val isValidNickName: Boolean = false
){
    fun userValidated() = isValidEmail && isValidPassword && isValidRepeatPassword
}

