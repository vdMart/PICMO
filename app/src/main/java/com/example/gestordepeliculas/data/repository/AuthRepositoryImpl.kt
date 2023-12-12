package com.example.gestordepeliculas.data.repository

import android.util.Log
import com.example.gestordepeliculas.ui.state.ResultVM
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.singleOrNull
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth
): AuthRepository {

    override val backingAuth: FirebaseAuth
        get() = auth
    /* TODO: */
    override suspend fun getAuth(): FirebaseAuth {
        return backingAuth
    }


    override suspend fun getToken(): String {
        return flow<String?> {
            FirebaseAuth.getInstance().currentUser?.let {
                emit(it.getIdToken(true).await().token)
            }
        }.singleOrNull() ?: ""
    }


    override suspend fun signInWithEmailAndPassword(
        email: String,
        password: String
    ): ResultVM<FirebaseUser> {
        return try {
            auth.signInWithEmailAndPassword(email, password).await().run {
                Log.i("InfoTag", "Info on: AuthRepository signInWithEmailAndPassword, \n Result: ${this}")
                ResultVM.Success(this.user ?: throw Exception("Error on signInWithEmailAndPassword"))
            }
        } catch (ex: Exception) {
            Log.e("ErrorTag", "Error on: AuthRepository signInWithEmailAndPassword, \n Message: ${ex.message}")
            ResultVM.Error(ex)
        }
    }

    override suspend fun signInWithGoogleCredential(task: Task<GoogleSignInAccount>): ResultVM<FirebaseUser> {
        return try {
            val account = task.getResult(ApiException::class.java)
            val credential = GoogleAuthProvider.getCredential(account.idToken, null)
            auth.signInWithCredential(credential).await().run {
                Log.i("InfoTag", "Info on: AuthRepository signInWithGoogleCredential, \n Result: ${this}")
                ResultVM.Success(this.user ?: throw Exception("Error on signInWithGoogleCredential"))
            }
        } catch (ex: Exception) {
            Log.e("ErrorTag", "Error on: AuthRepository signInWithGoogleCredential, \n Message: ${ex.message}")
            ResultVM.Error(ex)
        }
    }

    override suspend fun signInAnonymously(): ResultVM<FirebaseUser> {
        return try {
            auth.signInAnonymously().await().run {
                Log.i("InfoTag", "Info on: AuthRepository signInAnonymously, \n Result: ${this}")
                ResultVM.Success(this.user ?: throw Exception("Error on signInAnonymously"))
            }
        } catch(ex: Exception) {
            Log.e("ErrorTag", "Error on: AuthRepository signInAnonymously, \n Message: ${ex.message}")
            ResultVM.Error(ex)
        }
    }

    override suspend fun signInWithCustomToken(authToken: String): ResultVM<FirebaseUser> {
        return try {
            auth.signInWithCustomToken(authToken.orEmpty()).await().run {
                Log.i("InfoTag", "Info on: AuthRepository signInWithCustomToken, \n Result: ${this}")
                ResultVM.Success(this.user ?: throw Exception("Error on signInWithCustomToken"))
            }
        } catch (ex: Exception) {
            Log.e("ErrorTag", "Error on: AuthRepository signInWithCustomToken, \n Message: ${ex.message}")
            ResultVM.Error(ex)
        }
    }

    override suspend fun createUserWithEmailAndPassword(
        email: String,
        password: String
    ): ResultVM<FirebaseUser> {
        return try {
            auth.createUserWithEmailAndPassword(email, password).await().run {
                Log.i("InfoTag", "Info on: AuthRepository createUserWithEmailAndPassword, \n Result: ${this}")
                ResultVM.Success(this.user ?: throw Exception("Error on createUserWithEmailAndPassword"))
            }
        } catch (ex: Exception) {
            Log.e("ErrorTag", "Error on: AuthRepository createUserWithEmailAndPassword, \n Message: ${ex.message}")
            ResultVM.Error(ex)
        }
    }

    override suspend fun sendPasswordResetEmail(
        email: String,
        callback: (Boolean) -> Unit
    ) {
        try {
            auth.sendPasswordResetEmail(email).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.i("InfoTag", "Info on: AuthRepository sendPasswordResetEmail, \n Result: ${task}")
                    callback(true)
                } else {
                    callback(false)
                    Log.e("ErrorTag", "Error on: AuthRepository sendPasswordResetEmail, \n Result: ${task}")
                }
            }
        } catch (ex: Exception) {
            Log.e("ErrorTag", "Error on: AuthRepository sendPasswordResetEmail, \n Message: ${ex.message}")
            ResultVM.Error(ex)
        }
    }

    override suspend fun signOut(): ResultVM<Boolean> {
        return try {
            auth.signOut()
            Log.i("InfoTag", "Info on: AuthRepository signOut, \n Result: ${true}")
            ResultVM.Success(true)
        } catch (ex: Exception) {
            Log.e("ErrorTag", "Error on: AuthRepository sendPasswordResetEmail, \n Message: ${ex.message}")
            ResultVM.Error(ex)
        }
    }

}







/*
    override suspend fun sendEmailVerification(user: FirebaseUser): Boolean {
        return try {
            user.sendEmailVerification().isSuccessful
        } catch(ex: Exception) {
            Log.i("InfoTag", "Excepcion en sendEmailVerification: ${ex.message}")
            false
        }
    }

    override suspend fun isEmailVerified(user: FirebaseUser): Boolean {
        return try {
            user.isEmailVerified
        } catch(ex: Exception) {
            Log.i("InfoTag", "Excepcion en isEmailVerified: ${ex.message}")
            false
        }
    }
    */




/*
    override suspend fun sendPasswordResetEmail(email: String): Result<FirebaseUser> {
        return try {
            val result = auth.sendPasswordResetEmail(email).await()
            Log.i("InfoTag", "Login con signInAnonymously")
            Result.Success(result.user ?: throw Exception("Error on signInAnonymously"))
        } catch(ex: Exception) {
            Log.i("InfoTag", "Excepcion en signInAnonymously: ${ex.message}")
            Result.Error(ex)
        }
    }

    override suspend fun sendSignInLinkToEmail(email: String): Result<FirebaseUser> {
        return try {
            val result = auth.sendSignInLinkToEmail(email).await()
            Log.i("InfoTag", "Login con signInAnonymously")
            Result.Success(result.user ?: throw Exception("Error on signInAnonymously"))
        } catch(ex: Exception) {
            Log.i("InfoTag", "Excepcion en signInAnonymously: ${ex.message}")
            Result.Error(ex)
        }
    }

    override suspend fun isSignInWithEmailLink(email: String): Result<FirebaseUser> {
        return try {
            val result = auth.isSignInWithEmailLink(email).await()
            Log.i("InfoTag", "Login con signInAnonymously")
            Result.Success(result.user ?: throw Exception("Error on signInAnonymously"))
        } catch(ex: Exception) {
            Log.i("InfoTag", "Excepcion en signInAnonymously: ${ex.message}")
            Result.Error(ex)
        }
    }
*/
