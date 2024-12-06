package org.sopt.and.domain.repository

interface DataStoreRepository {
    fun getAccessToken(): String
    fun setAccessToken(accessToken: String)
    fun deleteTokens()
}