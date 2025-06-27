package com.fajarxfce.feature.login.data.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RepositoryModule {
//    @Binds
//    @Singleton
//    abstract fun bindLoginRepository(
//        loginRepositoryImpl: LoginRepositoryImpl
//    ) : LoginRepository
}