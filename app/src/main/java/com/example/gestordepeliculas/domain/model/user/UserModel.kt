package com.example.gestordepeliculas.domain.model.user

import com.example.gestordepeliculas.R
import com.google.firebase.auth.FirebaseUser

data class UserModel(
    var token: String = "",
    var onLogged: Boolean = false,
    var nickname:String = "",
    var email: String = "",
    var profilePictureUrl:String = R.drawable.ic_user.toString(),
    var userDetails: UserDetails = UserDetails()
)


fun FirebaseUser?.toDomain(userDetails: UserDetails) = UserModel(
    onLogged = this != null,
    nickname = this?.displayName.orEmpty(),
    email = this?.email.orEmpty(),
    profilePictureUrl = this?.photoUrl?.toString()?:R.drawable.ic_user.toString(),
    userDetails = userDetails,
)

fun FirebaseUser?.toDomain() = UserModel(
    onLogged = this != null,
    nickname = this?.displayName.orEmpty(),
    email = this?.email.orEmpty(),
    profilePictureUrl = this?.photoUrl?.toString()?:R.drawable.ic_user.toString(),
    userDetails = UserDetails(),
)
