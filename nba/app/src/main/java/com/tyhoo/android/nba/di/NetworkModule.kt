package com.tyhoo.android.nba.di

import com.tyhoo.android.nba.api.AppService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideAppService(): AppService {
        return AppService.create()
    }
}