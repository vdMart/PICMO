package com.example.gestordepeliculas.ui.state.login

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task

sealed class LoginEvent {
    object Completed: LoginEvent()
    data class EnteredEmail(val email: String): LoginEvent()
    data class EnteredPassword(val password: String): LoginEvent()
    object TogglePasswordVisibility: LoginEvent()
    object LoginWithEmailAndPassword: LoginEvent()
    data class LoginWithGoogleCredential(val task: Task<GoogleSignInAccount>): LoginEvent()
    object SignInAnonymously: LoginEvent()
    object SendPasswordResetEmail: LoginEvent()
}