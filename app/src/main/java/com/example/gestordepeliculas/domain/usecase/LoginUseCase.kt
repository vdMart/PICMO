package com.example.gestordepeliculas.domain.usecase

import com.example.gestordepeliculas.data.repository.AuthRepository
import com.example.gestordepeliculas.ui.state.ResultVM
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {

    suspend fun signInWithEmailAndPassword(email: String, password: String): ResultVM<FirebaseUser> {
        return authRepository.signInWithEmailAndPassword(email = email, password = password)
    }

    suspend fun signInWithGoogleCredential(task: Task<GoogleSignInAccount>): ResultVM<FirebaseUser> {
        return authRepository.signInWithGoogleCredential(task = task)
    }

    suspend fun signInAnonymously(): ResultVM<FirebaseUser> {
        return authRepository.signInAnonymously()
    }

    suspend fun sendPasswordResetEmail(email: String): Boolean {
        var ok = false
        authRepository.sendPasswordResetEmail(email, {ok = it})
        return ok
    }

}