package org.sopt.and.ui.screen.myPage.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.sopt.and.data.UserManager
import javax.inject.Inject

@HiltViewModel
class MyScreenViewModel @Inject constructor(
    private val userManager: UserManager
) : ViewModel() {
    private val _profileName = MutableStateFlow(userManager.getUserEmail() ?: "프로필1")
    val profileName: StateFlow<String> = _profileName

    fun logout() {
        userManager.logoutUser()
        userManager.setLoggedIn(false)
    }
}