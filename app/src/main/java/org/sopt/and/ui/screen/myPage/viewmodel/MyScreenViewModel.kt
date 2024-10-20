package org.sopt.and.ui.screen.myPage.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.sopt.and.data.UserManager
import javax.inject.Inject

@HiltViewModel
class MyScreenViewModel @Inject constructor(
    private val userManager: UserManager
) : ViewModel() {
    private val _profileName = MutableStateFlow("프로필1")
    val profileName: StateFlow<String> = _profileName

    init {
        loadUserProfile()
    }

    private fun loadUserProfile() {
        viewModelScope.launch {
            _profileName.value = userManager.getUserEmail() ?: "프로필1"
        }
    }

    fun logout() {
        viewModelScope.launch {
            userManager.logoutUser()
            userManager.setLoggedIn(false)
        }
    }
}