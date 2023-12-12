package com.example.gestordepeliculas.ui.state.profile

import androidx.lifecycle.ViewModel
import com.example.gestordepeliculas.ui.state.StateVM
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(

): ViewModel() {


    private var _profileStateVM = MutableStateFlow<StateVM>(StateVM())
    val profileStateVM: StateFlow<StateVM> = _profileStateVM.asStateFlow()

    private val _uiState = MutableStateFlow<ProfileUiState>(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()


    fun onEvent(/*event: ProfileEvent*/) {
        /* TODO */
    }

}