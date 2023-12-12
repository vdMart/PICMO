package com.example.gestordepeliculas.domain.usecase

import com.example.gestordepeliculas.data.repository.AuthRepository
import com.example.gestordepeliculas.ui.state.ResultVM
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {

    suspend fun createUserWithEmailAndPassword(email: String, password: String): ResultVM<FirebaseUser> {
        return authRepository.createUserWithEmailAndPassword(email, password)
    }

    suspend fun signInWithGoogleCredential(task: Task<GoogleSignInAccount>): ResultVM<FirebaseUser> {
        return authRepository.signInWithGoogleCredential(task = task)
    }

    suspend fun signInAnonymously(): ResultVM<FirebaseUser> {
        return authRepository.signInAnonymously()
    }

}