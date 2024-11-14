package org.sopt.and.data.repository

import org.sopt.and.data.datasource.UserDataSource
import org.sopt.and.network.model.request.LoginRequest
import org.sopt.and.network.model.request.SignUpRequest
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDataSource: UserDataSource
) : UserRepository {

    override suspend fun signUp(
        signUpRequest: SignUpRequest,
    ) = userDataSource.signUp(signUpRequest)

    override suspend fun login(
        loginRequest: LoginRequest
    ) = userDataSource.login(loginRequest)
}