package com.example.gestordepeliculas.data.repository

import com.example.gestordepeliculas.domain.model.user.UserDetails
import com.example.gestordepeliculas.ui.state.ResultVM

interface FirestoreRepository {
    suspend fun createUserDetails(userDetails: UserDetails): ResultVM<Boolean>
    suspend fun getUserDetails(): ResultVM<UserDetails>
}