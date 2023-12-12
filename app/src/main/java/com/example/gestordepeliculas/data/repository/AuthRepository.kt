package com.example.gestordepeliculas.data.repository

import com.example.gestordepeliculas.ui.state.ResultVM
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

interface AuthRepository {
    val backingAuth: FirebaseAuth /* TODO: */
    suspend fun getAuth(): FirebaseAuth
    suspend fun getToken(): String
    suspend fun signInWithEmailAndPassword(email: String, password: String): ResultVM<FirebaseUser>
    suspend fun signInWithGoogleCredential(task: Task<GoogleSignInAccount>): ResultVM<FirebaseUser>
    suspend fun signInAnonymously(): ResultVM<FirebaseUser>
    suspend fun signInWithCustomToken(authToken: String): ResultVM<FirebaseUser>
    suspend fun createUserWithEmailAndPassword(email: String, password: String): ResultVM<FirebaseUser>
    suspend fun sendPasswordResetEmail(email: String, callback: (Boolean) -> Unit)
    suspend fun signOut(): ResultVM<Boolean>
    //suspend fun sendEmailVerification(user: FirebaseUser): Boolean
    //suspend fun isEmailVerified(user: FirebaseUser): Boolean
}