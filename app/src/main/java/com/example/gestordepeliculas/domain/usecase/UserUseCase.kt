package com.example.gestordepeliculas.domain.usecase

import com.example.gestordepeliculas.data.repository.AuthRepository
import com.example.gestordepeliculas.data.repository.FirestoreRepository
import com.example.gestordepeliculas.domain.model.user.UserDetails
import com.example.gestordepeliculas.ui.state.ResultVM
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class UserUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val firestoreRepository: FirestoreRepository
) {

    suspend fun getCurrentUser(): FirebaseUser? {
        return authRepository.getAuth().currentUser
    }

    suspend fun getToken(): String {
        return authRepository.getToken()
    }

    suspend fun getUserDetails(): ResultVM<UserDetails> {
        return firestoreRepository.getUserDetails()
    }

    suspend fun createUserDetails(userDetails: UserDetails): ResultVM<Boolean> {
        return firestoreRepository.createUserDetails(userDetails)
    }

    suspend fun signOut(): ResultVM<Boolean> {
        return authRepository.signOut()
    }

    suspend fun signInWithCustomToken(authToken: String): ResultVM<FirebaseUser> {
        return authRepository.signInWithCustomToken(authToken)
    }

}