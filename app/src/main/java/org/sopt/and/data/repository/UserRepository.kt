package org.sopt.and.data.repository

import kotlinx.coroutines.flow.Flow
import org.sopt.and.network.adapter.ApiResult
import org.sopt.and.network.model.BaseResponse
import org.sopt.and.network.model.request.LoginRequest
import org.sopt.and.network.model.request.SignUpRequest
import org.sopt.and.network.model.response.SignUpResponse
import org.sopt.and.network.model.response.UserHobbyResponse
import org.sopt.and.network.model.response.UserTokenResponse

interface UserRepository {
    suspend fun signUp(
        signUpRequest: SignUpRequest
    ): Flow<ApiResult<BaseResponse<SignUpResponse>>>

    suspend fun login(
        loginRequest: LoginRequest
    ): Flow<ApiResult<BaseResponse<UserTokenResponse>>>

    suspend fun getHobby(
        token: String
    ): Flow<ApiResult<BaseResponse<UserHobbyResponse>>>
}