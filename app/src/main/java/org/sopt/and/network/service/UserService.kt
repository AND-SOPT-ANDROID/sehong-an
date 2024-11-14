package org.sopt.and.network.service

import org.sopt.and.network.adapter.ApiResult
import org.sopt.and.network.model.BaseResponse
import org.sopt.and.network.model.request.SignUpRequest
import org.sopt.and.network.model.response.SignUpResponse
import org.sopt.and.network.model.response.UserTokenResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {
    @POST("/user")
    suspend fun signUp(
        @Body signUpRequest: SignUpRequest
    ): ApiResult<BaseResponse<SignUpResponse>>

    @POST("/login")
    suspend fun login(): Response<BaseResponse<UserTokenResponse>>

}