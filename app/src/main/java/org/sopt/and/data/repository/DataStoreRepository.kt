package org.sopt.and.data.repository

interface DataStoreRepository {
    fun getAccessToken(): String
    fun setAccessToken(accessToken: String)
    fun deleteTokens()
}