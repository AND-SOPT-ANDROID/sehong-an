package org.sopt.and.data.datasource

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.sopt.and.di.IoDispatcher
import org.sopt.and.network.adapter.ApiResult
import org.sopt.and.network.model.BaseResponse
import org.sopt.and.network.model.request.SignUpRequest
import org.sopt.and.network.model.response.SignUpResponse
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
}