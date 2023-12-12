package com.example.gestordepeliculas.ui.state.user

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gestordepeliculas.data.local.UserPreferences
import com.example.gestordepeliculas.domain.model.user.UserDetails
import com.example.gestordepeliculas.domain.model.user.UserModel
import com.example.gestordepeliculas.domain.model.user.toDomain
import com.example.gestordepeliculas.domain.usecase.UserUseCase
import com.example.gestordepeliculas.ui.state.ResultVM
import com.example.gestordepeliculas.ui.state.StateVM
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userUseCase: UserUseCase,
    private val userPreferences: UserPreferences,
): ViewModel() {

    private var _userStateVM = MutableStateFlow<StateVM>(StateVM())
    val userStateVM: StateFlow<StateVM> = _userStateVM.asStateFlow()

    private var _userModelState = MutableStateFlow<UserModel>(UserModel())
    val userModelState: StateFlow<UserModel> = _userModelState.asStateFlow()

    private var _dirMovieSerie = MutableStateFlow<String>("")
    val dirMovieSerie: StateFlow<String> = _dirMovieSerie.asStateFlow()


    fun onEvent(event: UserEvent) {
        when (event) {

            /* User Actiosn */
            UserEvent.SignOut -> {
                viewModelScope.launch(Dispatchers.IO) {
                    _userStateVM.value = userStateVM.value.copy(isLoading = true)
                    val signOutResult = userUseCase.signOut()

                    when (signOutResult) {
                        is ResultVM.Success -> {
                            _userStateVM.value = _userStateVM.value.copy(errorException = null, isSuccessful = true)
                            Log.i("InfoTag", "Info on: UserEvent.SignOut, \n State: ${_userStateVM.value} \n Result: ${signOutResult.data}")
                        }

                        is ResultVM.Error -> {
                            _userStateVM.value = _userStateVM.value.copy(errorException = signOutResult.exception, isSuccessful = false)
                            Log.e("ErrorTag", "Error on: UserEvent.SignOut, \n Message: ${_userStateVM.value.errorException?.message}")
                        }
                    }

                    _userStateVM.value = userStateVM.value.copy(isLoading = false)
                }
            }

            UserEvent.UserUpdate -> {
                viewModelScope.launch(Dispatchers.IO) {
                    _userStateVM.value = userStateVM.value.copy(isLoading = true)

                    val userDetails = userUseCase.getUserDetails()
                    _userModelState.value = userUseCase.getCurrentUser().toDomain()
                    Log.i("InfoTag", "Info on: UserEvent.UserUpdate, \n State: ${_userStateVM.value} \n Result: ${_userModelState.value}")

                    when (userDetails) {
                        is ResultVM.Success -> {
                            _userModelState.value = userUseCase.getCurrentUser().toDomain(userDetails.data)
                            _userStateVM.value = _userStateVM.value.copy(errorException = null, isSuccessful = true)
                            Log.i("InfoTag", "Info on: UserEvent.UserUpdate, \n State: ${_userStateVM.value} \n Result: ${_userModelState.value}")
                        }

                        is ResultVM.Error -> {
                            if(!_userModelState.value.userDetails.isEmpty() && userUseCase.getCurrentUser().toDomain(UserDetails()).userDetails.isEmpty()) {
                                _userModelState.value = userUseCase.getCurrentUser().toDomain(UserDetails())
                            }else{
                                _userModelState.value = userUseCase.getCurrentUser().toDomain()
                            }

                            _userStateVM.value = _userStateVM.value.copy(errorException = userDetails.exception, isSuccessful = false)
                            Log.w("WarningTag", "Error on: UserEvent.UserUpdate, \n Message: ${_userStateVM.value.errorException?.message} \n Result: ${_userModelState.value}")
                        }
                    }

                    _userModelState.value = _userModelState.value.copy(token = userUseCase.getToken())
                    userPreferences.saveAuthToken(_userModelState.value.token)

                    _userStateVM.value = userStateVM.value.copy(isLoading = false)
                }
            }

            /* Set User Details */
            is UserEvent.ChangeTheme -> {
                viewModelScope.launch(Dispatchers.IO) {
                    _userModelState.value = _userModelState.value.copy(
                        userDetails = _userModelState.value.userDetails.copy(
                            theme = event.theme
                        )
                    )

                    updateFirestore()
                    Log.i("InfoTag", "Info on: UserViewModel.ChangeTheme,\n  State: ${userModelState.value.userDetails.theme}")
                }
            }

            is UserEvent.AddFavoriteMovie -> {
                viewModelScope.launch(Dispatchers.IO) {
                    val updatedDetails = _userModelState.value.userDetails.copy(
                        favoriteMovie = _userModelState.value.userDetails.favoriteMovie + event.idMovie
                    )
                    _userModelState.value = _userModelState.value.copy(
                        userDetails = updatedDetails
                    )

                    updateFirestore()
                    Log.i("InfoTag", "Info on: UserViewModel.AddFavoriteMovie,\n  State: ${userModelState.value.userDetails.favoriteMovie}")
                }
            }
            is UserEvent.RemoveFavoriteMovie -> {
                viewModelScope.launch(Dispatchers.IO) {
                    val updatedDetails = _userModelState.value.userDetails.copy(
                        favoriteMovie = _userModelState.value.userDetails.favoriteMovie - event.idMovie
                    )
                    _userModelState.value = _userModelState.value.copy(
                        userDetails = updatedDetails
                    )

                    updateFirestore()
                    Log.i("InfoTag", "Info on: UserViewModel.RemoveFavoriteMovie,\n  State: ${userModelState.value.userDetails.favoriteMovie}")
                }
            }
            is UserEvent.AddPendingMovie -> {
                viewModelScope.launch(Dispatchers.IO) {
                    val updatedDetails = _userModelState.value.userDetails.copy(
                        pendingMovie = _userModelState.value.userDetails.pendingMovie + event.idMovie
                    )
                    _userModelState.value = _userModelState.value.copy(
                        userDetails = updatedDetails
                    )

                    updateFirestore()
                    Log.i("InfoTag", "Info on: UserViewModel.AddPendingMovie,\n  State: ${userModelState.value.userDetails.pendingMovie}")
                }
            }
            is UserEvent.RemovePendingMovie -> {
                viewModelScope.launch(Dispatchers.IO) {
                    val updatedDetails = _userModelState.value.userDetails.copy(
                        pendingMovie = _userModelState.value.userDetails.pendingMovie - event.idMovie
                    )
                    _userModelState.value = _userModelState.value.copy(
                        userDetails = updatedDetails
                    )

                    updateFirestore()
                    Log.i("InfoTag", "Info on: UserViewModel.RemovePendingMovie,\n  State: ${userModelState.value.userDetails.pendingMovie}")
                }
            }
            is UserEvent.AddViewMovie -> {
                viewModelScope.launch(Dispatchers.IO) {
                    val updatedDetails = _userModelState.value.userDetails.copy(
                        viewMovie = _userModelState.value.userDetails.viewMovie + event.idMovie
                    )
                    _userModelState.value = _userModelState.value.copy(
                        userDetails = updatedDetails
                    )

                    updateFirestore()
                    Log.i("InfoTag", "Info on: UserViewModel.AddViewMovie,\n  State: ${userModelState.value.userDetails.viewMovie}")
                }
            }
            is UserEvent.RemoveViewMovie -> {
                viewModelScope.launch(Dispatchers.IO) {
                    val updatedDetails = _userModelState.value.userDetails.copy(
                        viewMovie = _userModelState.value.userDetails.viewMovie - event.idMovie
                    )
                    _userModelState.value = _userModelState.value.copy(
                        userDetails = updatedDetails
                    )

                    updateFirestore()
                    Log.i("InfoTag", "Info on: UserViewModel.RemoveViewMovie,\n  State: ${userModelState.value.userDetails.viewMovie}")
                }
            }
            is UserEvent.AddNoteMovie -> {
                viewModelScope.launch(Dispatchers.IO) {
                    val movieId = event.idMovie
                    val note = event.note

                    val updatedNotes = _userModelState.value.userDetails.noteMovie.toMutableMap()
                        .also { map ->
                            map[movieId] = note
                        }

                    val updatedDetails = _userModelState.value.userDetails.copy(
                        noteMovie = updatedNotes
                    )

                    _userModelState.value = _userModelState.value.copy(
                        userDetails = updatedDetails
                    )

                    updateFirestore()
                    Log.i("InfoTag", "Info on: UserViewModel.AddNoteMovie,\n  State: ${userModelState.value.userDetails.noteMovie}")
                }
            }

            is UserEvent.AddFavoriteSerie -> {
                viewModelScope.launch(Dispatchers.IO) {
                    val updatedDetails = _userModelState.value.userDetails.copy(
                        favoriteSerie = _userModelState.value.userDetails.favoriteSerie + event.idSerie
                    )
                    _userModelState.value = _userModelState.value.copy(
                        userDetails = updatedDetails
                    )

                    updateFirestore()
                    Log.i("InfoTag", "Info on: UserViewModel.AddFavoriteSerie,\n  State: ${userModelState.value.userDetails.favoriteSerie}")
                }
            }
            is UserEvent.RemoveFavoriteSerie -> {
                viewModelScope.launch(Dispatchers.IO) {
                    val updatedDetails = _userModelState.value.userDetails.copy(
                        favoriteSerie = _userModelState.value.userDetails.favoriteSerie - event.idSerie
                    )
                    _userModelState.value = _userModelState.value.copy(
                        userDetails = updatedDetails
                    )

                    updateFirestore()
                    Log.i("InfoTag", "Info on: UserViewModel.RemoveFavoriteSerie,\n  State: ${userModelState.value.userDetails.favoriteSerie}")
                }
            }
            is UserEvent.AddPendingSerie -> {
                viewModelScope.launch(Dispatchers.IO) {
                    val updatedDetails = _userModelState.value.userDetails.copy(
                        pendingSerie = _userModelState.value.userDetails.pendingSerie + event.idSerie
                    )
                    _userModelState.value = _userModelState.value.copy(
                        userDetails = updatedDetails
                    )

                    updateFirestore()
                    Log.i("InfoTag", "Info on: UserViewModel.AddPendingSerie,\n  State: ${userModelState.value.userDetails.pendingSerie}")
                }
            }
            is UserEvent.RemovePendingSerie -> {
                viewModelScope.launch(Dispatchers.IO) {
                    val updatedDetails = _userModelState.value.userDetails.copy(
                        pendingSerie = _userModelState.value.userDetails.pendingSerie - event.idSerie
                    )
                    _userModelState.value = _userModelState.value.copy(
                        userDetails = updatedDetails
                    )

                    updateFirestore()
                    Log.i("InfoTag", "Info on: UserViewModel.RemovePendingSerie,\n  State: ${userModelState.value.userDetails.pendingSerie}")
                }
            }
            is UserEvent.AddViewSerie -> {
                viewModelScope.launch(Dispatchers.IO) {
                    val updatedDetails = _userModelState.value.userDetails.copy(
                        viewSerie = _userModelState.value.userDetails.viewSerie + event.idSerie
                    )
                    _userModelState.value = _userModelState.value.copy(
                        userDetails = updatedDetails
                    )

                    updateFirestore()
                    Log.i("InfoTag", "Info on: UserViewModel.AddViewSerie,\n  State: ${userModelState.value.userDetails.viewSerie}")
                }
            }
            is UserEvent.RemoveViewSerie -> {
                viewModelScope.launch(Dispatchers.IO) {
                    val updatedDetails = _userModelState.value.userDetails.copy(
                        viewSerie = _userModelState.value.userDetails.viewSerie - event.idSerie
                    )
                    _userModelState.value = _userModelState.value.copy(
                        userDetails = updatedDetails
                    )

                    updateFirestore()
                    Log.i("InfoTag", "Info on: UserViewModel.RemoveViewSerie,\n  State: ${userModelState.value.userDetails.viewSerie}")
                }
            }
            is UserEvent.AddNoteSerie -> {
                viewModelScope.launch(Dispatchers.IO) {
                    val serieId = event.idSerie
                    val note = event.note

                    val updatedNotes = _userModelState.value.userDetails.noteSerie
                        .toMutableMap()
                        .also { map ->
                            map[serieId] = note
                        }

                    val updatedDetails = _userModelState.value.userDetails.copy(
                        noteMovie = updatedNotes
                    )

                    _userModelState.value = _userModelState.value.copy(
                        userDetails = updatedDetails
                    )

                    updateFirestore()
                    Log.i("InfoTag", "Info on: UserViewModel.AddNoteSerie,\n  State: ${userModelState.value.userDetails.noteSerie}")
                }
            }

            is UserEvent.AddFavoritePerson -> {
                viewModelScope.launch(Dispatchers.IO) {
                    val updatedDetails = _userModelState.value.userDetails.copy(
                        favoritePerson = _userModelState.value.userDetails.favoritePerson + event.idPerson
                    )
                    _userModelState.value = _userModelState.value.copy(
                        userDetails = updatedDetails
                    )

                    updateFirestore()
                    Log.i("InfoTag", "Info on: UserViewModel.AddFavoritePerson,\n  State: ${userModelState.value.userDetails.favoritePerson}")
                }
            }
            is UserEvent.RemoveFavoritePerson -> {
                viewModelScope.launch(Dispatchers.IO) {
                    val updatedDetails = _userModelState.value.userDetails.copy(
                        favoritePerson = _userModelState.value.userDetails.favoritePerson - event.idPerson
                    )
                    _userModelState.value = _userModelState.value.copy(
                        userDetails = updatedDetails
                    )

                    updateFirestore()
                    Log.i("InfoTag", "Info on: UserViewModel.RemoveFavoritePerson,\n  State: ${userModelState.value.userDetails.favoritePerson}")
                }
            }

            is UserEvent.AddFileMovie -> {
                viewModelScope.launch(Dispatchers.IO) {

                    val currentMap = userModelState.value.userDetails.movieFile
                    currentMap[event.idMovie] = event.uri

                    val updatedDetails = userModelState.value.userDetails.copy(
                        movieFile = currentMap
                    )
                    _userModelState.value = userModelState.value.copy(
                        userDetails = updatedDetails
                    )

                    updateFirestore()
                    Log.i("InfoTag", "Info on: UserViewModel.AddFileMovie,\n  State: ${userModelState.value.userDetails.movieFile}")
                }
            }

            is UserEvent.AddFileSerie -> {
                viewModelScope.launch(Dispatchers.IO) {
                    val currentMap = userModelState.value.userDetails.serieFile
                    currentMap[event.idSerie] = event.uri

                    val updatedDetails = userModelState.value.userDetails.copy(
                        serieFile = currentMap
                    )
                    _userModelState.value = userModelState.value.copy(
                        userDetails = updatedDetails
                    )

                    updateFirestore()
                    Log.i("InfoTag", "Info on: UserViewModel.AddFileSerie,\n  State: ${userModelState.value.userDetails.serieFile}")
                }
            }
        }
    }

    fun setDirMovieSerie(dir: String) {
        _dirMovieSerie.value = dir
        Log.i("InfoTag", "Info on: UserViewModel.setDirMovieSerie,\n  value: ${dirMovieSerie.value}")
    }

    suspend fun updateFirestore() {
        userUseCase.createUserDetails(userModelState.value.userDetails)
        Log.i("InfoTag", "Info on: UserViewModel.updateFirestore,\n  value: ${userModelState.value.userDetails}")
    }

}







/*
    fun signInWithUserPreferences()  {
        viewModelScope.launch(Dispatchers.IO) {
            _userStateVM.value = userStateVM.value.copy(isLoading = true)

            userPreferences.authToken.collect { authToken ->
                if(!authToken.isNullOrEmpty()) {
                    Log.d("DebugTag", "Token: $authToken")
                    val userResult = userUseCase.signInWithCustomToken(authToken)

                    when (userResult) {
                        is ResultVM.Success -> {
                            _userStateVM.value = userStateVM.value.copy(errorException = null, isSuccessful = true)
                            _userModelState.value = userResult.data.toDomain()
                            Log.i("InfoTag", "Info on: UserViewModel userPreferences, \n State: ${_userStateVM.value} \n Result: ${userResult.data.toDomain()}")
                        }

                        is ResultVM.Error -> {
                            _userStateVM.value = userStateVM.value.copy(errorException = userResult.exception, isSuccessful = false)
                            _userModelState.value = UserModel()
                            Log.e("ErrorTag", "Error on: UserViewModel userPreferences, \n Message: ${_userStateVM.value.errorException?.message}")
                        }
                    }
                }
            }
            _userStateVM.value = userStateVM.value.copy(isLoading = false)

        }
    }
*/