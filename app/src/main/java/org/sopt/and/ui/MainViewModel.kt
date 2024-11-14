package org.sopt.and.ui

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.sopt.and.data.UserManager
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val userManager: UserManager
) : ViewModel() {

}