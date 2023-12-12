package com.example.gestordepeliculas.data.repository

import android.net.Uri
import android.util.Log
import com.example.gestordepeliculas.domain.model.user.UserDetails
import com.example.gestordepeliculas.domain.model.user.UserLang
import com.example.gestordepeliculas.domain.model.user.UserThemes
import com.example.gestordepeliculas.ui.state.ResultVM
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirestoreRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val auth: FirebaseAuth
): FirestoreRepository {

    private val collName = "detail_user"

    override suspend fun createUserDetails(userDetails: UserDetails): ResultVM<Boolean> {
        val userId: String? = auth.currentUser?.uid
        return try {
            if(userId != null) { /*TODO: Las cuentas de google si ya existe se sobreescribe?*/
                firestore.collection(collName).document(userId).set(userDetails.toMap()).await()
                    .run {
                        Log.i("InfoTag", "Info on: FirestoreRepositoryImpl createUserDetails,\n  Result: ${true},\n  on: ${collName}")
                        ResultVM.Success(true)
                    }
            } else {
                throw Exception("userId is $userId")
            }
        } catch (ex: Exception) {
            Log.e("ErrorTag", "Error on: FirestoreRepositoryImpl createUserDetails, \n Message: ${ex.message}")
            ResultVM.Error(ex)
        }
    }


    override suspend fun getUserDetails(): ResultVM<UserDetails> {
        val userId: String? = auth.currentUser?.uid
        return try {
            if(userId != null) {
                Log.i("InfoTag", "Info on: FirestoreRepositoryImpl.getUserDetails,\n  Result: ${true},\n  on: ${collName}")
                firestore.collection(collName).document(userId).get().await().run {
                    ResultVM.Success(
                        UserDetails(
                            theme =          data?.get("theme")           as? UserThemes   ?: UserThemes.SYSTEM,
                            language =       data?.get("language")        as? UserLang     ?: UserLang.SYSTEM,
                            favoriteMovie =  data?.get("movie_favorite")  as? List<String> ?: emptyList(),
                            viewMovie =      data?.get("movie_again")     as? List<String> ?: emptyList(),
                            pendingMovie =   data?.get("movie_pending")   as? List<String> ?: emptyList(),
                            noteMovie =      data?.get("movie_note")      as? MutableMap<String, Double> ?: mutableMapOf(),
                            favoriteSerie =  data?.get("serie_favorite")  as? List<String> ?: emptyList(),
                            viewSerie =      data?.get("serie_again")     as? List<String> ?: emptyList(),
                            pendingSerie =   data?.get("serie_pending")   as? List<String> ?: emptyList(),
                            noteSerie =      data?.get("serie_note")      as? MutableMap<String, Double> ?: mutableMapOf(),
                            favoritePerson = data?.get("person_favorite") as? List<String> ?: emptyList(),
                            movieFile =      data?.get("movie_file")      as? MutableMap<String, Uri> ?: mutableMapOf(),
                            serieFile =      data?.get("episode_file")    as? MutableMap<String, Uri> ?: mutableMapOf(),
                        )
                    )
                }
            } else {
                throw Exception("userId is $userId")
            }
        } catch (ex: Exception) {
            Log.e("ErrorTag", "Error on: FirestoreRepositoryImpl getUserDetails, \n Message: ${ex.message}")
            ResultVM.Error(ex)
        }
    }

}