package org.sopt.and.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.and.data.datasource.DataStoreDataSource
import org.sopt.and.data.datasource.DataStoreDataSourceImpl
import org.sopt.and.data.datasource.UserDataSource
import org.sopt.and.data.datasource.UserDataSourceImpl
import org.sopt.and.domain.repository.DataStoreRepository
import org.sopt.and.domain.repository.DataStoreRepositoryImpl
import org.sopt.and.domain.repository.UserRepository
import org.sopt.and.domain.repository.UserRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindsDataStoreDataSource(
        dataStoreDataSource: DataStoreDataSourceImpl
    ): DataStoreDataSource

    @Binds
    abstract fun bindsDataStoreRepository(
        dataStoreRepository: DataStoreRepositoryImpl
    ): DataStoreRepository

    @Binds
    abstract fun bindsUserDataSource(
        authDataSource: UserDataSourceImpl
    ): UserDataSource

    @Binds
    abstract fun bindsUserRepository(
        authRepository: UserRepositoryImpl
    ): UserRepository


}