package com.example.gestordepeliculas.ui.state.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gestordepeliculas.domain.model.user.toDomain
import com.example.gestordepeliculas.domain.usecase.LoginUseCase
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
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
): ViewModel() {

    private var _loginStateVM = MutableStateFlow<StateVM>(StateVM())
    val loginStateVM: StateFlow<StateVM> = _loginStateVM.asStateFlow()

    private var _uiLoginState = MutableStateFlow<LoginUiState>(LoginUiState())
    val uiLoginState: StateFlow<LoginUiState> = _uiLoginState.asStateFlow()


    fun onEvent(event: LoginEvent) {
        when (event) {

            /* UI Login State */
            is LoginEvent.EnteredEmail -> {
                _uiLoginState.value = _uiLoginState.value.copy(emailState = event.email)
                Log.i("InfoTag", "Info on: LoginViewModel.EnteredEmail,\n State: ${_uiLoginState.value}")
            }

            is LoginEvent.EnteredPassword -> {
                _uiLoginState.value = _uiLoginState.value.copy(passwordState = event.password)
                Log.i("InfoTag", "Info on: LoginViewModel.EnteredPassword,\n State: ${_uiLoginState.value}")
            }

            LoginEvent.TogglePasswordVisibility -> {
                _uiLoginState.value = _uiLoginState.value.copy(passwordVisibilityState = !_uiLoginState.value.passwordVisibilityState)
                Log.i("InfoTag", "Info on: LoginViewModel.TogglePasswordVisibility,\n State: ${_uiLoginState.value}")
            }

            /* Login */
            LoginEvent.LoginWithEmailAndPassword -> {
                viewModelScope.launch(Dispatchers.IO) {

                    _loginStateVM.value = loginStateVM.value.copy(isLoading = true)
                    val loginResult = loginUseCase.signInWithEmailAndPassword(_uiLoginState.value.emailState, _uiLoginState.value.passwordState)

                    when (loginResult) {
                        is ResultVM.Success -> {
                            _loginStateVM.value = loginStateVM.value.copy(errorException = null, isSuccessful = true)
                            Log.i("InfoTag", "Info on: LoginEvent.LoginWithEmailAndPassword, \n State: ${_loginStateVM.value} \n Result: ${loginResult.data.toDomain()}")
                        }

                        is ResultVM.Error -> {
                            _loginStateVM.value = loginStateVM.value.copy(errorException = loginResult.exception, isSuccessful = false)
                            Log.e("ErrorTag", "Error on: LoginEvent.LoginWithEmailAndPassword, \n Message: ${_loginStateVM.value.errorException?.message}")
                        }
                    }
                    _loginStateVM.value = loginStateVM.value.copy(isLoading = false)

                }
            }

            is LoginEvent.LoginWithGoogleCredential -> {
                viewModelScope.launch(Dispatchers.IO) {

                    _loginStateVM.value = loginStateVM.value.copy(isLoading = true)
                    val loginResult = loginUseCase.signInWithGoogleCredential(event.task)

                    when (loginResult) {
                        is ResultVM.Success -> {
                            _loginStateVM.value = loginStateVM.value.copy(errorException = null, isSuccessful = true)
                            Log.i("InfoTag", "Info on: LoginEvent.LoginWithGoogleCredential, \n State: ${_loginStateVM.value} \n Result: ${loginResult.data.toDomain()}")
                        }

                        is ResultVM.Error -> {
                            _loginStateVM.value = loginStateVM.value.copy(errorException = loginResult.exception, isSuccessful = false)
                            Log.e("ErrorTag", "Error on: LoginEvent.LoginWithGoogleCredential, \n Message: ${_loginStateVM.value.errorException?.message}")
                        }
                    }
                    _loginStateVM.value = loginStateVM.value.copy(isLoading = false)

                }
            }

            LoginEvent.SignInAnonymously -> {
                viewModelScope.launch(Dispatchers.IO) {

                    _loginStateVM.value = loginStateVM.value.copy(isLoading = true)
                    val loginResult = loginUseCase.signInAnonymously()

                    when (loginResult) {
                        is ResultVM.Success -> {
                            _loginStateVM.value = loginStateVM.value.copy(errorException = null, isSuccessful = true)
                            Log.i("InfoTag", "Info on: LoginEvent.SignInAnonymously, \n State: ${_loginStateVM.value} \n Result: ${loginResult.data.toDomain()}")
                        }

                        is ResultVM.Error -> {
                            _loginStateVM.value = loginStateVM.value.copy(errorException = loginResult.exception, isSuccessful = false)
                            Log.e("ErrorTag", "Error on: LoginEvent.SignInAnonymously, \n Message: ${_loginStateVM.value.errorException?.message}")
                        }
                    }
                    _loginStateVM.value = loginStateVM.value.copy(isLoading = false)

                }
            }

            /* Options Login */
            LoginEvent.SendPasswordResetEmail -> {
                viewModelScope.launch(Dispatchers.IO) {

                    _loginStateVM.value = loginStateVM.value.copy(isLoading = true)
                    val resetPassResult = loginUseCase.sendPasswordResetEmail(_uiLoginState.value.emailState)

                    if (resetPassResult) {
                        Log.i("InfoTag", "Info on: LoginEvent.SignInAnonymously, \n State: ${_loginStateVM.value} \n Result: Correo de contraseña SI enviado.")
                    } else {
                        Log.e("ErrorTag", "Error on: LoginEvent.SendPasswordResetEmail, \n Message: Correo de contraseña NO enviado.")
                    }
                    _loginStateVM.value = loginStateVM.value.copy(isLoading = false)

                }
            }

            /* Login State */
            LoginEvent.Completed -> {
                _loginStateVM.value = loginStateVM.value.copy(isSuccessful = false)
                Log.i("InfoTag", "Info on: LoginViewModel.Completed ,\n State: ${_loginStateVM.value}")
            }
        }
    }


}
