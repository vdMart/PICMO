package com.example.gestordepeliculas.ui.state.register

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gestordepeliculas.domain.model.user.toDomain
import com.example.gestordepeliculas.domain.usecase.RegisterUseCase
import com.example.gestordepeliculas.domain.usecase.UserUseCase
import com.example.gestordepeliculas.ui.state.ResultVM
import com.example.gestordepeliculas.ui.state.StateVM
import com.example.gestordepeliculas.utils.isValidEmail
import com.example.gestordepeliculas.utils.isValidPassword
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase,
    private val userUseCase: UserUseCase
): ViewModel() {

    private var _registerStateVM = MutableStateFlow<StateVM>(StateVM())
    val registerStateVM: StateFlow<StateVM> = _registerStateVM.asStateFlow()

    private var _uiRegisterState = MutableStateFlow<RegisterUiState>(RegisterUiState())
    val uiRegisterState: StateFlow<RegisterUiState> = _uiRegisterState.asStateFlow()


    fun onEvent(event: RegisterEvent) {
        when (event) {

            /* UI Register State */
            is RegisterEvent.EnteredEmail -> {
                _uiRegisterState.value = uiRegisterState.value.copy(emailState = event.email, isValidEmail = event.email.isValidEmail())
                Log.i("InfoTag", "Info on: RegisterViewModel.EnteredEmail,\n State: ${_uiRegisterState.value}")
            }

            is RegisterEvent.EnteredPassword -> {
                _uiRegisterState.value = uiRegisterState.value.copy(passwordState = event.password, isValidPassword = event.password.isValidPassword())
                _uiRegisterState.value = uiRegisterState.value.copy(isValidRepeatPassword = uiRegisterState.value.repeatPasswordState==event.password)
                Log.i("InfoTag", "Info on: RegisterViewModel.EnteredPassword,\n State: ${_uiRegisterState.value}")
            }

            is RegisterEvent.EnteredRepeatPassword -> {
                _uiRegisterState.value = uiRegisterState.value.copy(repeatPasswordState = event.repeatPassword, isValidRepeatPassword = event.repeatPassword==uiRegisterState.value.passwordState)
                Log.i("InfoTag", "Info on: RegisterViewModel.EnteredRepeatPassword,\n State: ${_uiRegisterState.value}")
            }

            RegisterEvent.TogglePasswordVisibility -> {
                _uiRegisterState.value = uiRegisterState.value.copy(passwordVisibilityState = !uiRegisterState.value.passwordVisibilityState)
                Log.i("InfoTag", "Info on: RegisterViewModel.TogglePasswordVisibility,\n State: ${_uiRegisterState.value}")
            }

            RegisterEvent.ToggleRepeatPasswordVisibility -> {
                _uiRegisterState.value = uiRegisterState.value.copy(repeatPasswordVisibilityState = !uiRegisterState.value.repeatPasswordVisibilityState)
                Log.i("InfoTag", "Info on: RegisterViewModel.ToggleRepeatPasswordVisibility,\n State: ${_uiRegisterState.value}")
            }

            /* Register */
            RegisterEvent.RegisterWithEmailAndPassword -> {
                viewModelScope.launch {

                    _registerStateVM.value = registerStateVM.value.copy(isLoading = true)
                    val registerResult = registerUseCase.createUserWithEmailAndPassword(uiRegisterState.value.emailState, uiRegisterState.value.passwordState)

                    when (registerResult) {
                        is ResultVM.Success -> {
                            _registerStateVM.value = registerStateVM.value.copy(errorException = null, isSuccessful = true)
                            Log.i("InfoTag", "Info on: RegisterEvent.RegisterWithEmailAndPassword, \n State: ${_registerStateVM.value} \n Result: ${registerResult.data.toDomain()}")

                            val userDetailsResult = userUseCase.createUserDetails(registerResult.data.toDomain().userDetails)
                            when (userDetailsResult) {
                                is ResultVM.Success -> {
                                    _registerStateVM.value = registerStateVM.value.copy(errorException = null, isSuccessful = true)
                                    Log.i("InfoTag", "Info on: RegisterEvent.RegisterWithEmailAndPassword.createUserDetails, \n State: ${_registerStateVM.value} \n Result: ${registerResult.data.toDomain()}")
                                }

                                is ResultVM.Error -> {
                                    _registerStateVM.value = registerStateVM.value.copy(errorException = userDetailsResult.exception, isSuccessful = false)
                                    Log.e("ErrorTag", "Error on: RegisterEvent.RegisterWithEmailAndPassword.createUserDetails, \n Message: ${_registerStateVM.value.errorException?.message}")
                                }
                            }

                        }

                        is ResultVM.Error -> {
                            _registerStateVM.value = registerStateVM.value.copy(errorException = registerResult.exception, isSuccessful = false)
                            Log.e("ErrorTag", "Error on: RegisterEvent.RegisterWithEmailAndPassword, \n Message: ${_registerStateVM.value.errorException?.message}")
                        }
                    }

                    _registerStateVM.value = registerStateVM.value.copy(isLoading = false)
                }
            }

            is RegisterEvent.LoginWithGoogleCredential -> {
                viewModelScope.launch {

                    _registerStateVM.value = registerStateVM.value.copy(isLoading = true)
                    val registerResult = registerUseCase.signInWithGoogleCredential(event.task)

                    when (registerResult) {
                        is ResultVM.Success -> {
                            _registerStateVM.value = registerStateVM.value.copy(errorException = null, isSuccessful = true)
                            Log.i("InfoTag", "Info on: RegisterEvent.LoginWithGoogleCredential, \n State: ${_registerStateVM.value} \n Result: ${registerResult.data.toDomain()}")

                            val userDetailsResult = userUseCase.createUserDetails(registerResult.data.toDomain().userDetails)
                            when (userDetailsResult) {
                                is ResultVM.Success -> {
                                    _registerStateVM.value = registerStateVM.value.copy(errorException = null, isSuccessful = true)
                                    Log.i("InfoTag", "Info on: RegisterEvent.LoginWithGoogleCredential.createUserDetails, \n State: ${_registerStateVM.value} \n Result: ${registerResult.data.toDomain()}")
                                }

                                is ResultVM.Error -> {
                                    _registerStateVM.value = registerStateVM.value.copy(errorException = userDetailsResult.exception, isSuccessful = false)
                                    Log.e("ErrorTag", "Error on: RegisterEvent.LoginWithGoogleCredential.createUserDetails, \n Message: ${_registerStateVM.value.errorException?.message}")
                                }
                            }

                        }

                        is ResultVM.Error -> {
                            _registerStateVM.value = registerStateVM.value.copy(errorException = registerResult.exception, isSuccessful = false)
                            Log.e("ErrorTag", "Error on: RegisterEvent.LoginWithGoogleCredential, \n Message: ${_registerStateVM.value.errorException?.message}")
                        }
                    }

                    _registerStateVM.value = registerStateVM.value.copy(isLoading = false)
                }
            }

            is RegisterEvent.SignInAnonymously -> {
                viewModelScope.launch {

                    _registerStateVM.value = registerStateVM.value.copy(isLoading = true)
                    val registerResult = registerUseCase.signInAnonymously()

                    when (registerResult) {
                        is ResultVM.Success -> {
                            _registerStateVM.value = registerStateVM.value.copy(errorException = null, isSuccessful = true)
                            Log.i("InfoTag", "Info on: RegisterEvent.SignInAnonymously, \n State: ${_registerStateVM.value} \n Result: ${registerResult.data.toDomain()}")

                            val userDetailsResult = userUseCase.createUserDetails(registerResult.data.toDomain().userDetails)
                            when (userDetailsResult) {
                                is ResultVM.Success -> {
                                    _registerStateVM.value = registerStateVM.value.copy(errorException = null, isSuccessful = true)
                                    Log.i("InfoTag", "Info on: RegisterEvent.SignInAnonymously.createUserDetails, \n State: ${_registerStateVM.value} \n Result: ${registerResult.data.toDomain()}")
                                }

                                is ResultVM.Error -> {
                                    _registerStateVM.value = registerStateVM.value.copy(errorException = userDetailsResult.exception, isSuccessful = false)
                                    Log.e("ErrorTag", "Error on: RegisterEvent.SignInAnonymously.createUserDetails, \n Message: ${_registerStateVM.value.errorException?.message}")
                                }
                            }

                        }

                        is ResultVM.Error -> {
                            _registerStateVM.value = registerStateVM.value.copy(errorException = registerResult.exception, isSuccessful = false)
                            Log.e("ErrorTag", "Error on: RegisterEvent.SignInAnonymously, \n Message: ${_registerStateVM.value.errorException?.message}")
                        }
                    }

                    _registerStateVM.value = registerStateVM.value.copy(isLoading = false)
                }
            }
        }
    }


}

