package org.sopt.and.data.datasource

import kotlinx.coroutines.flow.Flow
import org.sopt.and.ui.network.adapter.ApiResult
import org.sopt.and.ui.network.model.BaseResponse
import org.sopt.and.ui.network.model.request.LoginRequest
import org.sopt.and.ui.network.model.request.SignUpRequest
import org.sopt.and.ui.network.model.response.SignUpResponse
import org.sopt.and.ui.network.model.response.UserHobbyResponse
import org.sopt.and.ui.network.model.response.UserTokenResponse

interface UserDataSource {
    suspend fun signUp(
        signUpRequest: SignUpRequest
    ): Flow<ApiResult<BaseResponse<SignUpResponse>>>

    suspend fun login(
        loginRequest: LoginRequest
    ): Flow<ApiResult<BaseResponse<UserTokenResponse>>>

    suspend fun getHobby(): Flow<ApiResult<BaseResponse<UserHobbyResponse>>>
}