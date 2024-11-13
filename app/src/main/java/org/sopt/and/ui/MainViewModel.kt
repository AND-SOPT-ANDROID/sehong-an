package org.sopt.and.ui

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.sopt.and.data.ServicePool
import org.sopt.and.data.UserManager
import org.sopt.and.data.dto.ResponseSingleUserDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val userManager: UserManager
) : ViewModel() {
    private val userService by lazy { ServicePool.userService }

    private val _userState = mutableStateOf<ResponseSingleUserDto?>(null)
    val userState: State<ResponseSingleUserDto?> get() = _userState

    fun getSingleUser(userId: Int) {
        userService.getSingleUser(userId = userId).enqueue(object :
            Callback<ResponseSingleUserDto> {
            override fun onResponse(
                call: Call<ResponseSingleUserDto>,
                response: Response<ResponseSingleUserDto>,
            ) {
                if (response.isSuccessful) {
                    _userState.value = response.body()
                } else {
                    val error = response.message()
                    Log.e("error", error.toString())
                }
            }

            override fun onFailure(call: Call<ResponseSingleUserDto>, t: Throwable) {
                Log.e("failure", t.message.toString())
            }
        })
    }
}