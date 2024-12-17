package org.sopt.and.data.repository

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.sopt.and.Constants
import org.sopt.and.data.datasource.DataStoreDataSource
import org.sopt.and.domain.repository.DataStoreRepository
import javax.inject.Inject

class DataStoreRepositoryImpl @Inject constructor(
    private val dataStoreDataSource: DataStoreDataSource
) : DataStoreRepository {
    override fun getAccessToken(): String = runBlocking {
        dataStoreDataSource.getStringValue(Constants.ACCESS_TOKEN).first()
    }

    override fun setAccessToken(accessToken: String) = runBlocking {
        dataStoreDataSource.setStringValue(Constants.ACCESS_TOKEN, accessToken)
    }

    override fun deleteTokens() = runBlocking {
        dataStoreDataSource.deleteStringValue(Constants.ACCESS_TOKEN)
    }
}