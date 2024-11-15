package org.sopt.and.data.datasource

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.sopt.and.di.IoDispatcher
import org.sopt.and.network.adapter.ApiResult
import org.sopt.and.network.model.BaseResponse
import org.sopt.and.network.model.request.LoginRequest
import org.sopt.and.network.model.request.SignUpRequest
import org.sopt.and.network.model.response.SignUpResponse
import org.sopt.and.network.model.response.UserHobbyResponse
import org.sopt.and.network.model.response.UserTokenResponse
import org.sopt.and.network.service.UserService
import javax.inject.Inject

class UserDataSourceImpl @Inject constructor(
    private val userService: UserService,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : UserDataSource {

    override suspend fun signUp(
        signUpRequest: SignUpRequest
    ): Flow<ApiResult<BaseResponse<SignUpResponse>>> = flow {
        emit(userService.signUp(signUpRequest))
    }.flowOn(ioDispatcher)

    override suspend fun login(
        loginRequest: LoginRequest
    ): Flow<ApiResult<BaseResponse<UserTokenResponse>>> = flow {
        emit(userService.login(loginRequest))
    }.flowOn(ioDispatcher)

    override suspend fun getHobby(
        token: String
    ): Flow<ApiResult<BaseResponse<UserHobbyResponse>>> = flow {
        emit(userService.getUserHobby(token))
    }.flowOn(ioDispatcher)
}