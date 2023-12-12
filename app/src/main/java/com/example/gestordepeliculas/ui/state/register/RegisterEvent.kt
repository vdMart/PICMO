package com.example.gestordepeliculas.ui.state.register

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task

sealed class RegisterEvent {
    data class EnteredEmail(val email: String): RegisterEvent()
    data class EnteredPassword(val password: String): RegisterEvent()
    data class EnteredRepeatPassword(val repeatPassword: String): RegisterEvent()
    object TogglePasswordVisibility: RegisterEvent()
    object ToggleRepeatPasswordVisibility: RegisterEvent()
    object RegisterWithEmailAndPassword: RegisterEvent()
    data class LoginWithGoogleCredential(val task: Task<GoogleSignInAccount>): RegisterEvent()
    object SignInAnonymously: RegisterEvent()
}