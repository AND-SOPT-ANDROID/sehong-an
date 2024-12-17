package org.sopt.and.data.repository

import org.sopt.and.data.datasource.UserDataSource
import org.sopt.and.data.network.model.request.LoginRequest
import org.sopt.and.data.network.model.request.SignUpRequest
import org.sopt.and.domain.repository.UserRepository
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

    override suspend fun getHobby() = userDataSource.getHobby()
}