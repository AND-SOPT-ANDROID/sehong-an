package org.sopt.and.data.datasource

import kotlinx.coroutines.flow.Flow
import org.sopt.and.network.adapter.ApiResult
import org.sopt.and.network.model.BaseResponse
import org.sopt.and.network.model.request.SignUpRequest
import org.sopt.and.network.model.response.SignUpResponse

interface UserDataSource {
    suspend fun signUp(
        signUpRequest: SignUpRequest
    ): Flow<ApiResult<BaseResponse<SignUpResponse>>>
}