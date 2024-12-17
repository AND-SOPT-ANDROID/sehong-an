package org.sopt.and.data.network.service

import org.sopt.and.data.network.adapter.ApiResult
import org.sopt.and.data.network.model.BaseResponse
import org.sopt.and.data.network.model.request.LoginRequest
import org.sopt.and.data.network.model.request.SignUpRequest
import org.sopt.and.data.network.model.response.SignUpResponse
import org.sopt.and.data.network.model.response.UserHobbyResponse
import org.sopt.and.data.network.model.response.UserTokenResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserService {
    @POST("/user")
    suspend fun signUp(
        @Body signUpRequest: SignUpRequest
    ): ApiResult<BaseResponse<SignUpResponse>>

    @POST("/login")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): ApiResult<BaseResponse<UserTokenResponse>>

    @GET("/user/my-hobby")
    suspend fun getUserHobby(): ApiResult<BaseResponse<UserHobbyResponse>>

}